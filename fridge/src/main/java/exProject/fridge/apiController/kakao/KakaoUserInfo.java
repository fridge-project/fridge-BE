package exProject.fridge.apiController.kakao;

import org.springframework.http.*;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

public class KakaoUserInfo {

    private static final String USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";
    private final RestTemplate restTemplate;

    public KakaoUserInfo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private KakaoToken kakaoToken;

    public void fetchDataWithHeaders() {
        // HttpHeaders 객체 생성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + kakaoToken.getToken());  // 예시: 인증 토큰을 설정하는 경우

        // RequestCallback을 사용하여 요청 전에 헤더 설정
        RequestCallback requestCallback = restTemplate.httpEntityCallback(new HttpEntity<>(headers));

        // ResponseExtractor를 사용하여 응답 처리
        ResponseExtractor<ResponseEntity<String>> responseExtractor = restTemplate.responseEntityExtractor(String.class);

        // exchange 메소드를 사용하여 요청 보내기 (GET 방식 예시)
        ResponseEntity<String> response = restTemplate.execute(USER_INFO_URI, HttpMethod.GET, requestCallback, responseExtractor);

    }
}
