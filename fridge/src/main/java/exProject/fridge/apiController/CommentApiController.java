package exProject.fridge.apiController;


import exProject.fridge.dto.AddCommentDto;
import exProject.fridge.dto.RequestWithUseridDto;
import exProject.fridge.dto.ResponseDto;
import exProject.fridge.model.Comment;
import exProject.fridge.model.Recipe;
import exProject.fridge.model.User;
import exProject.fridge.service.CommentService;
import exProject.fridge.service.RecipeService;
import exProject.fridge.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class CommentApiController {

    @Autowired
    private final CommentService commentService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final RecipeService recipeService;

    @PostMapping("/addComment") // 댓글 등록
    public ResponseDto<Integer> addComment(@RequestBody AddCommentDto addCommentDto) {
        Comment comment = new Comment();
        comment.setDetail(addCommentDto.getDetail());
        comment.setGrade(addCommentDto.getGrade());
        comment.setImageURL(addCommentDto.getImageURL());

        User user = userService.getUser(addCommentDto.getUserId());
        comment.setUser(user);

        Recipe recipe = recipeService.getOneRecipe(addCommentDto.getRecipeId());
        comment.setRecipe(recipe);
        System.out.println(comment);

//        if(commentService.isExist(comment)) return new ResponseDto<>(HttpStatus.UNAUTHORIZED.value(), 0);

        commentService.addComment(comment);


        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/delComment") // 댓글 삭제
    public ResponseDto<Integer> delComment(@RequestBody RequestWithUseridDto<Integer> request) {
        commentService.delComment(request.getUserId(), request.getData());

        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

}