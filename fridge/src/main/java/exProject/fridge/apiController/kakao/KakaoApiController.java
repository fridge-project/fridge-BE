package exProject.fridge.apiController.kakao;

import exProject.fridge.dto.ResponseDto;
import exProject.fridge.model.User;
import exProject.fridge.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import static exProject.fridge.model.AccountType.KAKAO;

@RestController
public class KakaoApiController {

    @Autowired
    private UserService userService;

    private KakaoUserInfo kakaoUserInfo;

    @PostMapping("/kakaologin") // 카카오 로그인
    public ResponseDto<Integer> kakaologin(@ModelAttribute("token") KakaoToken kakaoToken, User user) {
        kakaoUserInfo = new KakaoUserInfo(new RestTemplate(), kakaoToken);
        System.out.println(kakaoToken.getToken());
        user.setAccount(KAKAO); // 카카오 로그인
        user.setEmail(kakaoUserInfo.fetchDataWithHeaders()); // 카카오 이메일 넣어줌
        user.setPassword("testPassword"); // 일단 테스트 패스워드

        boolean result = userService.signup(user);
        if(result) return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 회원가입 성공(200)
        return new ResponseDto<Integer>(HttpStatus.UNAUTHORIZED.value(), 0); // 회원가입 실패(401)
    }

    @PostMapping("/getToken")
    public ModelAndView getToken(@RequestBody KakaoToken kakaoToken) {
        // 받아온 토큰을 처리하는 로직을 작성합니다.
        String receivedToken = kakaoToken.getToken();
        System.out.println("receivedToken = " + receivedToken);
        ModelAndView modelAndView = new ModelAndView("forward:/kakaologin");
        modelAndView.addObject("token", receivedToken);
        return modelAndView;
    }
}
