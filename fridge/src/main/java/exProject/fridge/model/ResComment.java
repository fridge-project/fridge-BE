package exProject.fridge.model;

public interface ResComment {
    User getUser(); // 유저 정보(수정해야 함)
    int getGrade(); // 평점
    String getDetail(); // 댓글 내용
    String getImageURL(); // 이미지url
}