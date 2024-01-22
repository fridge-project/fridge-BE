package exProject.fridge.apiController;

import exProject.fridge.dto.ResponseDto;
import exProject.fridge.model.Recipe;
import exProject.fridge.model.User;
import exProject.fridge.service.RecipeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/recipe")
public class RecipeApiController {

    @Autowired
    private final RecipeService recipeService;

    @ResponseBody
    @GetMapping
    public ResponseDto<List<Recipe>> getAllRecipes() {
        List<Recipe> allRecipes = recipeService.getAllRecipes();

        return new ResponseDto(HttpStatus.OK.value(), allRecipes);
    }

    @ResponseBody
    @GetMapping("/{recipe_code}")
    public ResponseDto<Recipe> getOneRecipe(@PathVariable int recipe_code) {
        Recipe oneRecipe = recipeService.getOneRecipe(recipe_code);

        return new ResponseDto(HttpStatus.OK.value(), oneRecipe);
    }

    @PostMapping("/{recipe_code}")
    public String addFavorites(@PathVariable int recipe_code) {
        // 사용자 정보를 가져와서

        // 사용자 정보와 레시피 코드를 넣는다.

        return "redirect:/recipe";
    }



}
