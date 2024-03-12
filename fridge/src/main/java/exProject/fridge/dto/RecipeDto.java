package exProject.fridge.dto;

import exProject.fridge.model.Comment;
import exProject.fridge.model.Recipe;
import exProject.fridge.model.RecipeProcess;
import exProject.fridge.model.ResComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {

    private Recipe recipe;
    private List<RecipeProcess> recipeProcess;
    private List<ResComment> comments;
    private GradeDto gradeDto;
    private boolean favorite;
    private boolean like;
}
