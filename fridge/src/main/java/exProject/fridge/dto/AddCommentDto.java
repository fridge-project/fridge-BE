package exProject.fridge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddCommentDto {
    private int recipeId;
    private int userId;
    private String detail;
    private int grade;
    private String imageURL;
}
