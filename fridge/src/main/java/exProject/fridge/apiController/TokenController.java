package exProject.fridge.apiController;

import exProject.fridge.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/token") // 안드로이드에서 넘어온 토큰
public class TokenController {

    @PostMapping
    public ResponseDto<Integer> processToken(@RequestBody TokenRequest tokenRequest) {
        // 받아온 토큰을 처리하는 로직을 작성합니다.
        String receivedToken = tokenRequest.getToken();

        // 여기에 토큰 처리 로직을 추가하세요.
        // 예를 들어, 토큰을 검증하고 유효한지 확인하는 등의 작업을 수행합니다.

        // 처리 결과를 반환합니다. 예제에서는 간단하게 받은 토큰을 그대로 반환합니다.
        if(!receivedToken.isEmpty()) return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 정상적으로 토큰을 받았다면 성공
        return new ResponseDto<Integer>(HttpStatus.UNAUTHORIZED.value(), 0); // 회원가입 실패(401)
    }
}

// 토큰을 받기 위한 요청 객체
class TokenRequest {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}