package exProject.fridge.repository;

import exProject.fridge.model.LikeRecipe;
import exProject.fridge.model.Recipe;
import exProject.fridge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRecipeRepository extends JpaRepository<LikeRecipe, Integer> {

    Optional<LikeRecipe> findByUserAndRecipe(User user, Recipe recipe);
}
