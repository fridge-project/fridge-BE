package exProject.fridge.repository;

import exProject.fridge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FridgeRepository extends JpaRepository<User, Long> {
}
