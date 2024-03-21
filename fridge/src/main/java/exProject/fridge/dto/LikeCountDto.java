package exProject.fridge.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeCountDto {
    private long recipe_id;
    private long likeCount;

}
