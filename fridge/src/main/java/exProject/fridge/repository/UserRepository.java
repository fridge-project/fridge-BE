package exProject.fridge.repository;

import exProject.fridge.model.AccountType;
import exProject.fridge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmailAndAccountAndPassword(String email, AccountType accountType, String password);

    User findByEmailAndAccount(String email, AccountType accountType);
}
