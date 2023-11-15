package exProject.fridge.apiController;

import exProject.fridge.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FridgeApiController {

    @GetMapping("/fridge") // 보유 재료 확인
    public ResponseDto<String> getIngredients() {


        return new ResponseDto<>(HttpStatus.OK.value(), "미완");
    }

    @PostMapping("/fridge") // 재료 등록
    public ResponseDto<Integer> addIngredients() {

        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

}
