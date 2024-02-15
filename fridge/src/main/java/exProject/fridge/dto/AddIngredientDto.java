package exProject.fridge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddIngredientDto {
    private int userId;
    private String name;
    private String exp;
    private String addDate;
    private String memo;
    private String storage;
}
