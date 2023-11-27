package exProject.fridge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class IngredientDto {
    private String name;
    private String exp;
    private String memo;
    private String storage;
    private String imageURL;
}
