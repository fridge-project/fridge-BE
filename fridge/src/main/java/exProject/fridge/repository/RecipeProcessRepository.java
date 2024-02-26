package exProject.fridge.repository;

import exProject.fridge.model.Recipe;
import exProject.fridge.model.RecipeProcess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeProcessRepository extends JpaRepository<RecipeProcess, Integer> {

    List<RecipeProcess> findByRecipe(Recipe recipe);
}
