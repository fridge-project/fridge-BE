//package exProject.fridge.service;
//
//import exProject.fridge.model.AccountType;
//import exProject.fridge.model.User;
//import exProject.fridge.service.UserService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class UserServiceTest {
//
//    @Autowired
//    UserService userService;
//
//    @Test
//    public void 회원가입() throws Exception {
//        // given
//        User user = new User();
//        user.setEmail("Shin");
//        user.setPassword("qwer");
//        user.setAccount(AccountType.SELF);
//
//        // when
//        boolean signup = userService.signup(user);
//
//        // then
//        assertTrue(signup);
//    }
//
//    @Test
//    public void 로그인() throws Exception {
//        // given
//
//        // when
//
//        // then
//
//    }
//
//}
