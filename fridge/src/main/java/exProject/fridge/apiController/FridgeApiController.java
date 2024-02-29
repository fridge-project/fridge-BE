package exProject.fridge.apiController;

import exProject.fridge.dto.AddIngredientDto;
import exProject.fridge.dto.RequestWithUseridDto;
import exProject.fridge.dto.ResponseDto;
import exProject.fridge.model.*;
import exProject.fridge.service.FridgeService;
import exProject.fridge.service.IngredientService;
import exProject.fridge.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class FridgeApiController {
    @Autowired
    private final FridgeService fridgeService;

    @Autowired
    private final IngredientService ingredientService;

    @Autowired
    private final UserService userService;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    static class RequestIngre {
        private String name;
        private String exp;
        private String memo;
        private String storage;
    }

    @PostMapping("/fridge") // 재료 등록
    public ResponseDto<Integer> addIngredients(@RequestBody AddIngredientDto addIngredientDto) {
        Fridge fridge = new Fridge();
        fridge.setExp(addIngredientDto.getExp());
        fridge.setMemo(addIngredientDto.getMemo());
        fridge.setStorage(StorageType.valueOf(addIngredientDto.getStorage()));
        fridge.setAddDate(addIngredientDto.getAddDate());

        Ingredient ingredient = ingredientService.getIngredient(addIngredientDto.getName());
        fridge.setIngredient(ingredient);

        User user = userService.getUser(addIngredientDto.getUserId());

        // 1. 재료가 있나?
//        boolean exist = fridgeService.isExist(user.getId(), ingredient.getId());

        // 2-1. 동일 재료가 이미 있으면 실패 return
//        if(exist) return new ResponseDto<>(HttpStatus.UNAUTHORIZED.value(), 0);

        // 2-2. 동일 재료가 없으면 등록 후 성공 return

        fridge.setUser(user);

        fridgeService.addIngredient(fridge);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/getFridge") // 보유 재료 확인
    public ResponseDto<List<ResFridge>> getIngredients(@RequestBody RequestWithUseridDto request) {
        User user = userService.getUser(request.getUserId());

        List<ResFridge> data = fridgeService.getIngredient(user);

        return new ResponseDto<List<ResFridge>>(HttpStatus.OK.value(), data);
    }

    @PostMapping("/delFridge") // 보유 재료 삭제
    public ResponseDto<Integer> delIngredients(@RequestBody RequestWithUseridDto<Integer> request) {
        fridgeService.delIngredient(request.getUserId(), request.getData());

        return new ResponseDto<Integer>(
                HttpStatus.OK.value(), 1);
    }

}