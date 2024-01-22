package exProject.fridge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // PK_auto

    @Column(nullable = false, length = 100, unique = true)
    private String email; // 이메일

    @Column(nullable = false, length = 100)
    private String password; // 비밀번호

    @Column(nullable = false, length = 100)
    private String name; // 이름

    @Enumerated(EnumType.STRING)
    private AccountType account; // 로그인 유형

    @OneToMany(mappedBy = "user")
    private Set<UserRecipeFavorite> favoriteRecipes;

}