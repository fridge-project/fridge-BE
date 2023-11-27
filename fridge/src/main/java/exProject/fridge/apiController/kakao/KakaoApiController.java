package exProject.fridge.apiController.kakao;

import exProject.fridge.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KakaoApiController {

    private KakaoUserInfo kakaoUserInfo;

    @PostMapping("/kakaologin")
    public ResponseDto<Integer> processToken(@RequestBody KakaoToken kakaoToken) {
        // 받아온 토큰을 처리하는 로직을 작성합니다.
        String receivedToken = kakaoToken.getToken();
        if(!receivedToken.isEmpty())

        if(!receivedToken.isEmpty()) return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 정상적으로 토큰을 받았다면 성공
        return new ResponseDto<Integer>(HttpStatus.UNAUTHORIZED.value(), 0); // 회원가입 실패(401)
    }
}
