package exProject.fridge.repository;

import exProject.fridge.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface FridgeRepository extends JpaRepository<Fridge, FridgeId> {

    @Query(value = "SELECT f FROM Fridge f WHERE f.user_id = :user_id AND f.ingre_id = :ingre_id", nativeQuery = true)
    Fridge findIngredients(int user_id, int ingre_id);

    List<ResFridge> findByUserId(int user_id);

//    Fridge findByingreId(int ingre_id);
}
