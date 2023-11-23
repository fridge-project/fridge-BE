package exProject.fridge.repository;

import exProject.fridge.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    Ingredient findByName(String name);
}
