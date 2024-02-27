package exProject.fridge.service;

import exProject.fridge.model.Recipe;
import exProject.fridge.model.RecipeProcess;
import exProject.fridge.repository.RecipeProcessRepository;
import exProject.fridge.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RecipeService {

    @Autowired
    private final RecipeRepository recipeRepository;

    @Autowired
    private final RecipeProcessRepository recipeProcessRepository;

    @Transactional(readOnly = true)
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Recipe getOneRecipe(int recipe_code) {
        log.info("recipe: {}", recipeRepository.findById(recipe_code));
        return recipeRepository.findById(recipe_code);
    }

    @Transactional(readOnly = true)
    public List<RecipeProcess> getRecipeProcess(Recipe recipe) {
        return recipeProcessRepository.findByRecipe(recipe);
    }

}
