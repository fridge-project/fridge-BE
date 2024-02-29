package exProject.fridge.apiController;

import exProject.fridge.dto.RecipeDto;
import exProject.fridge.dto.RequestWithUseridDto;
import exProject.fridge.dto.ResponseDto;
import exProject.fridge.model.*;
import exProject.fridge.service.CommentService;
import exProject.fridge.service.RecipeService;
import exProject.fridge.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeApiController {

    private final RecipeService recipeService;
    private final UserService userService;

    @Autowired
    private final CommentService commentService;

    @GetMapping // 레시피 목록
    public ResponseDto<List<Recipe>> getAllRecipes() {
        List<Recipe> allRecipes = recipeService.getAllRecipes();

        return new ResponseDto(HttpStatus.OK.value(), allRecipes);
    }

    @GetMapping("/{id}") // 레시피 상세페이지
    public ResponseDto<RecipeDto> getOneRecipe(@PathVariable int id) {
        Recipe oneRecipe = recipeService.getOneRecipe(id);
        List<RecipeProcess> recipeProcess = recipeService.getRecipeProcess(oneRecipe);
        log.info("recipeProcess = {}", recipeProcess);

        RecipeDto recipeDto = new RecipeDto(oneRecipe, recipeProcess, commentService.getComment(id));
//        recipeProcess.stream()
//                .peek(process -> process.setRecipe(null))
//                .collect(Collectors.toList());
//        RecipeDto recipeDto = new RecipeDto(oneRecipe, recipeProcess);

        return new ResponseDto(HttpStatus.OK.value(), recipeDto);
    }

    @PostMapping("/{id}") // 즐겨찾기 추가
    public ResponseDto<UserRecipeFavorite> addFavorites(@PathVariable int id,
                                                        @RequestBody RequestWithUseridDto request) {
        // 사용자 정보를 가져오고
        User user = userService.getUser(request.getUserId());

        // 레시피 코드로 레시피 가져오고
        Recipe recipe = recipeService.getOneRecipe(id);

        // 즐겨찾기를 만든다.
        UserRecipeFavorite favorite = new UserRecipeFavorite();
        favorite.setUser(user);
        favorite.setRecipe(recipe);

        userService.addFavoriteRecipe(favorite);

        return new ResponseDto(HttpStatus.OK.value(), null);
    }

    @PostMapping("/{id}/like") // 레시피 좋아요 or 취소 (처음 좋아요를 눌렀을 때 데이터가 만들어짐)
    public ResponseDto<LikeRecipe> addlike(@PathVariable int id,
                                           @RequestBody RequestWithUseridDto request) {

        // 사용자 정보와 레시피 코드를 가져오고
        User user = userService.getUser(request.getUserId());
        Recipe recipe = recipeService.getOneRecipe(id);

        // 한번이라도 좋아요를 누른 레시피인지 확인 -> null일 수도 있기 때문에 Optional사용
        Optional<LikeRecipe> likeRecipeOrNull = userService.getLikeRecipe(user, recipe);

        // 한번도 좋아요를 안눌렀다면 (데이터가 없다면)
        if (likeRecipeOrNull.isEmpty()) {
            // 데이터를 만들고 true로 바꾼다.
            LikeRecipe likeRecipe = new LikeRecipe();
            likeRecipe.setUser(user);
            likeRecipe.setRecipe(recipe);
            likeRecipe.setLike(true);

            // DB에 저장하고
            userService.updateLikeRecipe(likeRecipe);

            return new ResponseDto(HttpStatus.OK.value(), likeRecipe);

        } else { // 수정의 경우 -> 좋아요 레시피를 가져와서 boolean값을 뒤집어준다.
            LikeRecipe likeRecipe = likeRecipeOrNull.get();
            likeRecipe.setLike(!likeRecipe.isLike());

            // DB에 저장하고
            userService.updateLikeRecipe(likeRecipe);

            return new ResponseDto(HttpStatus.OK.value(), likeRecipe);
        }

    }


}
