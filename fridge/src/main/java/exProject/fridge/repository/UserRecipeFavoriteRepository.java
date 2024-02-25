package exProject.fridge.repository;
import exProject.fridge.model.Recipe;
import exProject.fridge.model.User;
import exProject.fridge.model.UserRecipeFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRecipeFavoriteRepository extends JpaRepository<UserRecipeFavorite, Integer> {

    List<UserRecipeFavorite> findByUser(User user);
}
