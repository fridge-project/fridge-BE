package exProject.fridge.apiController.kakao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.*;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Getter @Setter
public class KakaoUserInfo {

    private static final String USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";
    private final RestTemplate restTemplate;

    private KakaoToken kakaoToken;

    public KakaoUserInfo(RestTemplate restTemplate, KakaoToken kakaoToken) {
        this.restTemplate = restTemplate;
        this.kakaoToken = kakaoToken;
    }

    public String fetchDataWithHeaders() {
        System.out.println(kakaoToken.getToken());
        // HttpHeaders 객체 생성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + kakaoToken);
        System.out.println("headers = " + headers);

        // GET 요청
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(USER_INFO_URI, String.class, headers);

        // 요청에 대한 응답 처리
        String responseBody = responseEntity.getBody();

        try {
            // jackson objectmapper 객체 생성
            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, Object> jsonMap = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {
            });

            Map<String, Object> kakao_account = (Map<String, Object>) jsonMap.get("kakao_account");
            System.out.println("kakao_account = " + kakao_account);

            String email = kakao_account.get("email").toString();
            return email;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
