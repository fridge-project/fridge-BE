package exProject.fridge.apiController;

import exProject.fridge.dto.GradeDto;
import exProject.fridge.dto.RecipeDto;
import exProject.fridge.dto.RequestWithUseridDto;
import exProject.fridge.dto.ResponseDto;
import exProject.fridge.model.*;
import exProject.fridge.service.CommentService;
import exProject.fridge.service.RecipeService;
import exProject.fridge.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    private final CommentService commentService;

    @GetMapping // 레시피 목록
    public ResponseDto<List<Recipe>> getAllRecipes() {
        List<Recipe> allRecipes = recipeService.getAllRecipes();

        return new ResponseDto(HttpStatus.OK.value(), allRecipes);
    }

    @PostMapping("/{id}") // 레시피 상세페이지
    public ResponseDto<RecipeDto> getOneRecipe(@PathVariable int id,
                                               @RequestBody RequestWithUseridDto request) {

        User user = userService.getUser(request.getUserId());

        Recipe oneRecipe = recipeService.getOneRecipe(id);
        List<RecipeProcess> recipeProcess = recipeService.getRecipeProcess(oneRecipe);

        List<ResComment> resComments = commentService.getComment(id);
        GradeDto gradeDto = commentService.calGrade(resComments);

        RecipeDto recipeDto = new RecipeDto(oneRecipe, recipeProcess, resComments, gradeDto, false, false);

        Optional<UserRecipeFavorite> userRecipeFavorite = userService.getFavorite(user, oneRecipe);
        Optional<LikeRecipe> likeRecipe = userService.getLikeRecipe(user, oneRecipe);

        // null 검사
        if (!userRecipeFavorite.isEmpty()) {
            boolean favorite = userRecipeFavorite.get().isFavorite();
            recipeDto.setFavorite(favorite);
        }

        if (!likeRecipe.isEmpty()) {
            boolean like = likeRecipe.get().isLike();
            recipeDto.setLike(like);
        }

        log.info("recipeDto = {}", recipeDto);

        return new ResponseDto(HttpStatus.OK.value(), recipeDto);

    }

    @PostMapping("/{id}/favorite") // 즐겨찾기 추가
    public ResponseDto<UserRecipeFavorite> addFavorites(@PathVariable int id,
                                                        @RequestBody RequestWithUseridDto request) {
        // 사용자 정보와 레시피 코드를 가져오고
        User user = userService.getUser(request.getUserId());
        Recipe recipe = recipeService.getOneRecipe(id);

        // 한번이라도 즐겨찾기를 누른 레시피인지 확인 -> null일 수도 있기 때문에 Optional사용
        Optional<UserRecipeFavorite> favoriteRecipeOrNull = userService.getFavorite(user, recipe);

        // 한번도 즐겨찾기를 안눌렀다면 (데이터가 없다면)
        if (favoriteRecipeOrNull.isEmpty()) {
            // 데이터를 만들고 true로 바꾼다.
            UserRecipeFavorite userRecipeFavorite = new UserRecipeFavorite();
            userRecipeFavorite.setUser(user);
            userRecipeFavorite.setRecipe(recipe);
            userRecipeFavorite.setFavorite(true);

            // DB에 저장하고
            userService.updateFavoriteRecipe(userRecipeFavorite);

            return new ResponseDto(HttpStatus.OK.value(), userRecipeFavorite);

        } else { // 수정의 경우 -> 즐겨찾기를 가져와서 boolean값을 뒤집어준다.
            UserRecipeFavorite userRecipeFavorite = favoriteRecipeOrNull.get();
            userRecipeFavorite.setFavorite(!userRecipeFavorite.isFavorite());

            // DB에 저장하고
            userService.updateFavoriteRecipe(userRecipeFavorite);

            return new ResponseDto(HttpStatus.OK.value(), userRecipeFavorite);
        }

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
