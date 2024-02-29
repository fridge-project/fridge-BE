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
public class Recipe {

    @Id
    private int id; // 레시피 코드

    @Column(nullable = false, length = 50)
    private String name; // 음식명

    @Column(nullable = false, length = 200)
    private String introduce; // 간략 소개

    @Column(nullable = false, length = 20)
    private String category; // 유형 분류

    @Column(nullable = false, length = 20)
    private String classification; // 음식 분류

    @Column(nullable = false, length = 10)
    private int time; // 조리 시간

    @Column(nullable = false, length = 20)
    private String serving; // 분량

    @Column(nullable = false, length = 20)
    private String difficulty; // 난이도

    @Column(nullable = false, length = 100)
    private String imageURL; // 이미지url

    @Column(nullable = false, length = 10)
    private int calorie; // 칼로리

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)

    private Set<UserRecipeFavorite> favoritedByUser;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<LikeRecipe> likedByUser;
}
