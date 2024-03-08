package exProject.fridge.service;

import exProject.fridge.dto.GradeDto;
import exProject.fridge.model.Comment;
import exProject.fridge.model.ResComment;
import exProject.fridge.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Transactional // 댓글 존재 여부
    public boolean isExist(Comment comment) { // 오류
        if(commentRepository.findComments(comment.getUser(), comment.getRecipe()) != null) return true;

        return false;
    }

    @Transactional // 댓글 등록
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Transactional // 댓글 조회
    public List<ResComment> getComment(int recipeId) {
        return commentRepository.findByRecipeId(recipeId);
    }

    @Transactional // 평점 계산
    public GradeDto calGrade(List<ResComment> resComments) {
        int one = 0, two = 0, three = 0, four = 0, five = 0;

        for(ResComment res : resComments) {
            switch (res.getGrade()) {
                case 1: one++; break;
                case 2: two++; break;
                case 3: three++; break;
                case 4: four++; break;
                case 5: five++; break;
            }
        }

        double avg = (double) (one + two * 2 + three * 3 + four * 4 + five * 5) / resComments.size();

        return new GradeDto(avg, new int[] {one, two, three, four, five});
    }

    @Transactional // 댓글 삭제
    public void delComment(int userId, int recipeId) {
        commentRepository.delete(commentRepository.findByUserIdAndRecipeId(userId, recipeId));
    }
}