package exProject.fridge.apiController;

import exProject.fridge.dto.ResponseDto;
import exProject.fridge.model.Recipe;
import exProject.fridge.service.RecipeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RecipeApiController {

    @Autowired
    private final RecipeService recipeService;

    @GetMapping("/recipe")
    public ResponseDto getRecipe() {
        List<Recipe> recipe = recipeService.getRecipe();

        return new ResponseDto(HttpStatus.OK.value(), recipe);
    }

}
