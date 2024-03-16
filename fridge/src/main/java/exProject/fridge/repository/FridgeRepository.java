package exProject.fridge.repository;

import exProject.fridge.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface FridgeRepository extends JpaRepository<Fridge, FridgeId> {

    @Query(value = "SELECT f FROM Fridge f WHERE f.user.id = :user_id AND f.ingredient.id = :ingre_id")
    Fridge findIngredient(int user_id, int ingre_id);

    List<ResFridge> findByUserId(int user_id);

//    Fridge findByingreId(int ingre_id);
}
