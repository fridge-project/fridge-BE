package exProject.fridge.repository;

import exProject.fridge.model.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FridgeRepository extends JpaRepository<Fridge, Integer> {
}
