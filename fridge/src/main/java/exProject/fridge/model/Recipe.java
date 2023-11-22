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
public class Recipe {

    @Id
    private int recipe_code;

    @Column(nullable = false, length = 50)
    private String name; // 음식명

    @Column(nullable = false, length = 200)
    private String introduce; // 간략 소개

    @Enumerated(EnumType.STRING)
    private CategoryType category;

    @Enumerated(EnumType.STRING)
    private ClassificationType classification;

    // 조리 시간, 분량, 난이도, 이미지url 추가해야 함.


}
