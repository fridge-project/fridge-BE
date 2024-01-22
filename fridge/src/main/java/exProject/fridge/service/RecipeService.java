package exProject.fridge.service;

import exProject.fridge.model.Recipe;
import exProject.fridge.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class RecipeService {

    @Autowired
    private final RecipeRepository recipeRepository;

    @Transactional
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Transactional
    public Recipe getOneRecipe(int recipe_code) {
        return recipeRepository.findByRecipeCode(recipe_code);
    }
}
