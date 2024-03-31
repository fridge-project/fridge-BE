package exProject.fridge.service;

import exProject.fridge.model.LikeRecipe;
import exProject.fridge.model.Recipe;
import exProject.fridge.model.User;
import exProject.fridge.model.UserRecipeFavorite;
import exProject.fridge.repository.LikeRecipeRepository;
import exProject.fridge.repository.RecipeRepository;
import exProject.fridge.repository.UserRecipeFavoriteRepository;
import exProject.fridge.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserRecipeFavoriteRepository userRecipeFavoriteRepository;

    @Autowired
    private final RecipeRepository recipeRepository;

    @Autowired
    private final LikeRecipeRepository likeRecipeRepository;

    public User findUser(int userId) {
        return userRepository.findById(userId);
    }

    @Transactional // 회원가입
    public boolean signup(User user) {
        if (!idCheck(user)) {
            userRepository.save(user); // Email 중복 아니면 가입
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true) // 로그인
    public int login(User user) {
        User cur = userRepository.findByEmailAndAccountAndPassword(user.getEmail(), user.getAccount(), user.getPassword());
        if (cur != null) return cur.getId();
        else return -1;
    }

    @Transactional(readOnly = true) // id 존재 여부
    public boolean idCheck(User user) {
        if (userRepository.findByEmailAndAccount(user.getEmail(), user.getAccount()) != null) return true;
        else return false;
    }

    @Transactional(readOnly = true) // user정보 가져오기
    public User getUser(int userId) {
        return userRepository.findById(userId);
    }

    @Transactional(readOnly = true) // 즐겨찾기 목록 가져오기
    public List<UserRecipeFavorite> getFavoriteRecipes(User user) {
        return userRecipeFavoriteRepository.findByUser(user);
    }

    @Transactional(readOnly = true) // 좋아요 목록 가져오기
    public List<LikeRecipe> getLikeRecipes(User user) {
        return likeRecipeRepository.findByUser(user);
    }

    @Transactional(readOnly = true) // 즐겨찾기를 누른 레시피 조회
    public Optional<UserRecipeFavorite> getFavorite(User user, Recipe recipe) {
        return userRecipeFavoriteRepository.findByUserAndRecipe(user, recipe);
    }

    @Transactional // 레시피 즐겨찾기 업데이트
    public void updateFavoriteRecipe(UserRecipeFavorite userRecipeFavorite) {
        userRecipeFavoriteRepository.save(userRecipeFavorite);
    }

    @Transactional(readOnly = true) // 좋아요를 누른 레시피 조회
    public Optional<LikeRecipe> getLikeRecipe(User user, Recipe recipe) {
        return likeRecipeRepository.findByUserAndRecipe(user, recipe);
    }

    @Transactional // 레시피 좋아요 업데이트
    public void updateLikeRecipe(LikeRecipe likeRecipe) {
        likeRecipeRepository.save(likeRecipe);
    }

    @Transactional // 유저 정보 저장
    public void updateProfile(User user) {
        userRepository.save(user);
    }
}