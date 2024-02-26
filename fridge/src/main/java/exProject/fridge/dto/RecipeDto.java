package exProject.fridge.dto;

import exProject.fridge.model.Recipe;
import exProject.fridge.model.RecipeProcess;
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
}
