package exProject.fridge.service;

import exProject.fridge.model.Recipe;
import exProject.fridge.model.User;
import exProject.fridge.model.UserRecipeFavorite;
import exProject.fridge.repository.UserRecipeFavoriteRepository;
import exProject.fridge.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserRecipeFavoriteRepository userRecipeFavoriteRepository;

    @Transactional // 회원가입
    public boolean signup(User user) {
        if(!idCheck(user)) {
            userRepository.save(user); // Email 중복 아니면 가입
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true) // 로그인
    public int login(User user) {
        User cur = userRepository.findByEmailAndAccountAndPassword(user.getEmail(),user.getAccount(), user.getPassword());
        if(cur != null) return cur.getId();
        else return -1;
    }

    @Transactional(readOnly = true) // id 존재 여부
    public boolean idCheck(User user) {
        if(userRepository.findByEmailAndAccount(user.getEmail(), user.getAccount()) != null) return true;
        else return false;
    }

    @Transactional(readOnly = true) // user정보 가져오기
    public User getUser(int userId) {
        return userRepository.findById(userId);
    }

    @Transactional(readOnly = true)
    public List<UserRecipeFavorite> getFavoriteRecipes(User user) {
        return userRecipeFavoriteRepository.findByUser(user);
    }
}