package exProject.fridge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RecipeProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // PK_auto

    @ManyToOne
    @JoinColumn(name = "recipe_code", referencedColumnName = "id")
    private Recipe recipe; // 레시피 코드

    @Column(nullable = false, length = 11)
    private int orderNum; // 설명 순서

    @Column(nullable = false, length = 300)
    private String detail; // 설명

}
