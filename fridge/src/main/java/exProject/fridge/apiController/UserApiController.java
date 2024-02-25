package exProject.fridge.apiController;

import exProject.fridge.dto.RequestWithUseridDto;
import exProject.fridge.dto.ResponseDto;
import exProject.fridge.dto.UserLoginResponseDto;
import exProject.fridge.jwt.JwtTokenizer;
import exProject.fridge.model.AccountType;
import exProject.fridge.model.Recipe;
import exProject.fridge.model.User;
import exProject.fridge.model.UserRecipeFavorite;
import exProject.fridge.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static exProject.fridge.model.AccountType.SELF;

@Slf4j
@RestController
@AllArgsConstructor
public class UserApiController {
    @Autowired
    private final UserService userService;

    @Autowired
    private HttpSession session;

    @Autowired
    private final JwtTokenizer jwtTokenizer; // jwt

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

    @GetMapping("/favorites") // 즐겨찾기 목록 // GET 요청인데 RequestBody가 있는게 좀 이상함. 나중에 수정해야할듯.
    public ResponseDto<List<UserRecipeFavorite>> getFavoriteRecipes(@RequestBody User user) {
        List<UserRecipeFavorite> favoriteRecipes = userService.getFavoriteRecipes(user);
        return new ResponseDto<>(HttpStatus.OK.value(), favoriteRecipes);
    }

    @PostMapping("/favorites/delete") // 즐겨찾기 삭제
    public ResponseDto<List<UserRecipeFavorite>> deleteFavorites(@RequestBody RequestWithUseridDto<UserRecipeFavorite> request) {
        // 유저 정보를 불러옴
        User user = userService.getUser(request.getUserId());
        log.info("user = {}", user);

        // 삭제할 즐겨찾기 레시피 불러옴
        UserRecipeFavorite favorite = request.getData();
        log.info("favorite = {}", favorite);

        // 레시피 삭제
        userService.deleteFavoriteRecipe(favorite);

        // 삭제하고 다시 즐겨찾기 목록을 반환
        List<UserRecipeFavorite> favoriteRecipes = userService.getFavoriteRecipes(user);
        log.info("favoriteRecipes = {}", favoriteRecipes);
        return new ResponseDto<>(HttpStatus.OK.value(), favoriteRecipes);
    }

}
