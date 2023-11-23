package exProject.fridge.service;

import exProject.fridge.model.User;
import exProject.fridge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional // 회원가입
    public boolean signup(User user) { // 수정해야함!!!
        if(userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user); // Email 중복 아니면 가입
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true) // 로그인
    public User login(User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    @Transactional(readOnly = true) // id 존재 여부
    public boolean idCheck(User user) {
        if(userRepository.findByEmail(user.getEmail()) != null) return true;
        return false;
    }
}