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
@IdClass(CommentId.class)
public class Comment {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user; // 사용자 id - 외래키

    @Id
    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipeCode")
    Recipe recipe; // 재료코드 - 외래키

    @Column
    private int grade; // 평점

    @Column(length = 200)
    private String detail; // 댓글 내용

    @Column(length = 100)
    private String imageURL; // 이미지url

}
