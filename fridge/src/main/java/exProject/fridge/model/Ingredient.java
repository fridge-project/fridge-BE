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
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // PK_auto

    @Column(nullable = false, length = 20, unique = true)
    private String name; // 재료명

    @Column(nullable = false, length = 100)
    private String imageURL; // 이미지url
}