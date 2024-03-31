package exProject.fridge.apiController;

import exProject.fridge.StorageManager;
import exProject.fridge.dto.AddIngredientDto;
import exProject.fridge.dto.RequestWithUseridDto;
import exProject.fridge.dto.ResponseDto;
import exProject.fridge.dto.UserLoginResponseDto;
import exProject.fridge.jwt.JwtTokenizer;
import exProject.fridge.model.LikeRecipe;
import exProject.fridge.model.User;
import exProject.fridge.model.UserRecipeFavorite;
import exProject.fridge.service.UserService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static exProject.fridge.model.AccountType.SELF;

@Slf4j
@RestController
@AllArgsConstructor
public class UserApiController {
    @Autowired
    private final UserService userService;

    @Autowired
    private final JwtTokenizer jwtTokenizer; // jwt

    @Autowired
    private final StorageManager storageManager;

    public static final String DEFAULT_PROFILE = "https://fridgeproject.s3.ap-northeast-2.amazonaws.com/defaultProfile.png";

    @PostMapping("/signup") // 회원가입
    public ResponseDto<Integer> signup(@RequestBody User user) {
        user.setAccount(SELF); // 자체 로그인

        boolean result = userService.signup(user);
        if(result) return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 회원가입 성공(200)
        return new ResponseDto<Integer>(HttpStatus.UNAUTHORIZED.value(), 0); // 회원가입 실패(401)
    }

    @PostMapping("/login") // jwt 로그인
    public ResponseDto login(@RequestBody User user) {

        UserLoginResponseDto loginResponse = new UserLoginResponseDto();

        user.setAccount(SELF);
        if(!userService.idCheck(user)) return new ResponseDto<UserLoginResponseDto>(HttpStatus.UNAUTHORIZED.value(), loginResponse); // id 존재x

        user.setId(userService.login(user));
        if(user.getId() == -1) return new ResponseDto<UserLoginResponseDto>(HttpStatus.UNAUTHORIZED.value(), loginResponse); // id/pw 오류

        String accessToken = jwtTokenizer.createAccessToken(user.getId(), user.getEmail());
        String refreshToken = jwtTokenizer.createRefreshToken(user.getId(), user.getEmail());

        loginResponse = UserLoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .build();

        return new ResponseDto<UserLoginResponseDto>(HttpStatus.OK.value(), loginResponse);
    }

    @PostMapping("/logout") // 로그아웃
    public ResponseDto<Integer> logout(@RequestBody User user) {
        return new ResponseDto<>(HttpStatus.OK.value(), 1); // 미완
    }

    @PostMapping("/favorites") // 즐겨찾기 목록
    public ResponseDto<List<UserRecipeFavorite>> getFavoriteRecipes(@RequestBody User user) {
        List<UserRecipeFavorite> favoriteRecipes = userService.getFavoriteRecipes(user);
        return new ResponseDto<>(HttpStatus.OK.value(), favoriteRecipes);
    }

    @PostMapping("/likes") // 좋아요 목록
    public ResponseDto<List<LikeRecipe>> getLikeRecipes(@RequestBody User user) {
        List<LikeRecipe> likeRecipes = userService.getLikeRecipes(user);
        return new ResponseDto<>(HttpStatus.OK.value(), likeRecipes);
    }

    @PostMapping("/profile") // 프로필 수정 불러오기
    public ResponseDto<User> getProfile(@RequestBody RequestWithUseridDto request) {
        User user = userService.getUser(request.getUserId());
        return new ResponseDto<>(HttpStatus.OK.value(), user);
    }

    @PostMapping("/userImage") // 사용자 이미지 넣기
    public ResponseDto<Integer> userImage(@RequestPart int userId,
                                          @RequestPart(value = "file") @Nullable MultipartFile file) throws IOException {
        User user = userService.findUser(userId);

        if (file.isEmpty()) { // 이미지를 선택하지 않으면 기본이미지로 설정해줌

            user.setImage(DEFAULT_PROFILE);

        } else {

            String imageId = user.getName() + "_" + "profile"; // 이미지Id를 만들어준다.
            storageManager.saveImage(imageId, file); // 이미지 저장
            String imageUrl = storageManager.getImage(imageId);// 이미지 경로를 유저 정보에 등록
            user.setImage(imageUrl);
        }

        userService.updateProfile(user);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
