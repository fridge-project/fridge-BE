package exProject.fridge.repository;

import exProject.fridge.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, CommentId> {

    @Query(value = "SELECT f FROM Comment f WHERE f.user_id = :user_id AND f.recipe_id = :recipe_id", nativeQuery = true)
    Comment findComments(User user, Recipe recipe);

    Comment findByUserIdAndRecipeId(int userId, int recipeId);

    List<ResComment> findByRecipeId(int recipeCode);
}
