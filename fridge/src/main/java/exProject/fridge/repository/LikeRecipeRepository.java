package exProject.fridge.repository;

import exProject.fridge.model.LikeRecipe;
import exProject.fridge.model.Recipe;
import exProject.fridge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LikeRecipeRepository extends JpaRepository<LikeRecipe, Integer> {

    Optional<LikeRecipe> findByUserAndRecipe(User user, Recipe recipe);

    @Query("SELECT lr.recipe.id, COUNT(lr) AS like_count FROM LikeRecipe lr WHERE lr.like = true GROUP BY lr.recipe.id ORDER BY like_count DESC")
    List<Object[]> countLikesByRecipeId();
}
