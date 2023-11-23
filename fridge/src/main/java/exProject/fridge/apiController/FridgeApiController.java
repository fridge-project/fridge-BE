package exProject.fridge.apiController;

import exProject.fridge.dto.ResponseDto;
import exProject.fridge.model.Fridge;
import exProject.fridge.service.FridgeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FridgeApiController {
    @Autowired
    private FridgeService fridgeService;

    @Autowired
    private HttpSession session;


    @PostMapping("/fridge") // 재료 등록
    public ResponseDto<Integer> addIngredients(@RequestBody Fridge fridge) {

        // 1. 재료가 있나?
        boolean exist;
        // 2-1. 동일 재료가 이미 있으면 실패 return
        // 2-2. 동일 재료가 없으면 등록 후 성공 return
        try {
            addIngredients(fridge);
        } catch (Exception e) {
            return new ResponseDto<>(HttpStatus.UNAUTHORIZED.value(), 0);
        }

        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @GetMapping("/fridge") // 보유 재료 확인
    public ResponseDto<String> getIngredients() {


        return new ResponseDto<>(HttpStatus.OK.value(), "미완");
    }

}