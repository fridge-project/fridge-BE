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
public class RecipeIngredient {

    @Id
    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    Recipe recipe; // 레시피 코드 - 외래키

    @Id
    @ManyToOne
    @JoinColumn(name = "ingre_id", referencedColumnName = "id")
    Ingredient ingredient; // 재료코드 - 외래키

    @Column(nullable = false, length = 20)
    private String amount; // 설명
}

