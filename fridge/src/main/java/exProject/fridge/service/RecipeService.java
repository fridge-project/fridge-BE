package exProject.fridge.service;

import exProject.fridge.dto.LikeCountDto;
import exProject.fridge.model.Recipe;
import exProject.fridge.model.RecipeProcess;
import exProject.fridge.repository.LikeRecipeRepository;
import exProject.fridge.repository.RecipeProcessRepository;
import exProject.fridge.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RecipeService {

    @Autowired
    private final RecipeRepository recipeRepository;

    @Autowired
    private final RecipeProcessRepository recipeProcessRepository;

    @Autowired
    private final LikeRecipeRepository likeRecipeRepository;

    @Transactional(readOnly = true)
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Recipe getOneRecipe(int recipe_code) {

        log.info("recipe: {}", recipeRepository.findByid(recipe_code));
        return recipeRepository.findByid(recipe_code);
    }

    @Transactional(readOnly = true)
    public List<RecipeProcess> getRecipeProcess(Recipe recipe) {
        return recipeProcessRepository.findByRecipe(recipe);
    }

    @Transactional(readOnly = true)
    public List<LikeCountDto> getLikeCountList() {
        List<Object[]> objects = likeRecipeRepository.countLikesByRecipeId();
        List<LikeCountDto> likeCount = new ArrayList<>();
        for (Object[] object : objects) {
            log.info("object[0] = {}",  object[0]);
            log.info("object[1] = {}",  object[1]);
            LikeCountDto dto = new LikeCountDto();
            dto.setRecipe_id(Long.valueOf(object[0].toString()));
            dto.setLikeCount(Long.valueOf(object[1].toString()));
            likeCount.add(dto);
        }

        return likeCount;
    }

}
