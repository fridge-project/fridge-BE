package exProject.fridge.repository;

import exProject.fridge.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findAll();

    Recipe findByRecipeCode(int recipe_code);

}
