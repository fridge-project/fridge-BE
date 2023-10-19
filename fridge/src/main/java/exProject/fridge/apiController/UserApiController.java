package exProject.fridge.apiController;

import exProject.fridge.dto.ResponseDto;
import exProject.fridge.model.User;
import exProject.fridge.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @PostMapping("/signup") // 회원가입
    public ResponseDto<Integer> signup(@RequestBody User user) {

        boolean result = userService.signup(user);
        if(result) return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 회원가입 성공(200)
        return new ResponseDto<Integer>(HttpStatus.UNAUTHORIZED.value(), 0); // 회원가입 실패(402)
    }

    @PostMapping("/login") // 로그인
    public ResponseDto<Integer> login(@RequestBody User user) {
        User principal = userService.login(user);
        if(principal != null) {
            session.setAttribute("principal", principal);
            return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 로그인 성공(200)
        }
        return new ResponseDto<Integer>(HttpStatus.UNAUTHORIZED.value(), 0); // 로그인 실패(402)
    }

}