package exProject.fridge.apiController;

import exProject.fridge.dto.KakaoDto;
import exProject.fridge.dto.ResponseDto;
import exProject.fridge.model.User;
import exProject.fridge.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static exProject.fridge.model.AccountType.KAKAO;
import static exProject.fridge.model.AccountType.SELF;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class KakaoController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @PostMapping("/kakaoSignUp")
    public ResponseDto<Integer> kakaoSignUp(@RequestBody KakaoDto kakaoDto) {

        User kakaoUser = new User();

        kakaoUser.setPassword("test"); // 이러면 안될거 같은데 일단 넣어줌.
        kakaoUser.setName(kakaoDto.getName());
        kakaoUser.setEmail(kakaoDto.getEmail());
        kakaoUser.setAccount(KAKAO); // 자체 로그인

        boolean result = userService.signup(kakaoUser);
        if(result) return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 회원가입 성공(200)
        return new ResponseDto<Integer>(HttpStatus.UNAUTHORIZED.value(), 0); // 회원가입 실패(401)
    }

    @PostMapping("/kakaoLogin")
    public ResponseDto<Integer> kakaoLogin(@RequestBody KakaoDto kakaoDto) {
        User kakaoUser = new User();
        kakaoUser.setPassword("test"); // 이러면 안될거 같은데 일단 넣어줌.
        kakaoUser.setName(kakaoDto.getName());
        kakaoUser.setEmail(kakaoDto.getEmail());
        kakaoUser.setAccount(KAKAO); // 자체 로그인
        userService.login(kakaoUser);

        int principal = userService.login(kakaoUser);
        if(principal != -1) {
            session.setAttribute("principal", principal);
            return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 로그인 성공(200)
        }
        else {
            boolean idExist = userService.idCheck(kakaoUser);
            if(idExist) return new ResponseDto<Integer>(HttpStatus.UNAUTHORIZED.value(), 10); // id/pw 오류
            else return new ResponseDto<Integer>(HttpStatus.UNAUTHORIZED.value(), 0); // id 존재x
        }
    }
}
