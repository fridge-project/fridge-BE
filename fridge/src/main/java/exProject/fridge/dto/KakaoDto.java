package exProject.fridge.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class KakaoDto {

    private Long id;
    private String name;
    private String email;

}
