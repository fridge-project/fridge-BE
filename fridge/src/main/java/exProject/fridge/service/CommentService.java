package exProject.fridge.service;

import exProject.fridge.model.Comment;
import exProject.fridge.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Transactional // 댓글 존재 여부
    public boolean isExist(Comment comment) { // 오류
        if(commentRepository.findComments(comment.getUser().getId(), comment.getRecipe().getId()) != null) return true;

        return false;
    }

    @Transactional // 댓글 등록
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Transactional // 댓글 삭제
    public void delComment(int userId, int recipeId) {
        commentRepository.delete(commentRepository.findByUserIdAndRecipeId(userId, recipeId));

    }
}
