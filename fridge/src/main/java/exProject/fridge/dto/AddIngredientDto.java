package exProject.fridge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
