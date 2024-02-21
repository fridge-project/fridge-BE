package exProject.fridge.repository;

import exProject.fridge.model.Comment;
import exProject.fridge.model.CommentId;
import exProject.fridge.model.Recipe;
import exProject.fridge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, CommentId> {

    @Query(value = "SELECT f FROM Comment f WHERE f.user_id = :user_id AND f.recipe_id = :recipe_id", nativeQuery = true)
    Comment findComments(User user, Recipe recipe);

    Comment findByUserId(int userId);
}
