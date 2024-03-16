package exProject.fridge.apiController;

import exProject.fridge.StorageManager;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class FridgeApiController {
    @Autowired
    private final FridgeService fridgeService;

    @Autowired
    private final IngredientService ingredientService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final StorageManager storageManager;

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
    public ResponseDto<Integer> addIngredients(@RequestPart AddIngredientDto addIngredientDto, @RequestPart(value = "file") MultipartFile file) throws IOException {
        Fridge fridge = new Fridge();
        fridge.setExp(addIngredientDto.getExp());
        fridge.setMemo(addIngredientDto.getMemo());
        fridge.setStorage(StorageType.valueOf(addIngredientDto.getStorage()));
        fridge.setAddDate(addIngredientDto.getAddDate());

        User user = userService.getUser(addIngredientDto.getUserId());
        fridge.setUser(user);

        Ingredient ingredient = ingredientService.getIngredient(addIngredientDto.getName());
        fridge.setIngredient(ingredient);

        // 이미지 등록
        String imageId = user.getName() + "_" + ingredient.getId(); // 이미지Id를 만들어준다.
        storageManager.saveImage(imageId, file); // 이미지 저장
        String imageUrl = storageManager.getImage(imageId);// 이미지 경로를 재료에 등록
        fridge.setImageUrl(imageUrl);

        fridgeService.addIngredient(fridge);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/getFridge") // 보유 재료 확인
    public ResponseDto<List<ResFridge>> getIngredients(@RequestBody RequestWithUseridDto request) {
        User user = userService.getUser(request.getUserId());

        List<ResFridge> data = fridgeService.getIngredients(user);

        return new ResponseDto<List<ResFridge>>(HttpStatus.OK.value(), data);
    }

    @PostMapping("/delFridge") // 보유 재료 삭제
    public ResponseDto<Integer> delIngredients(@RequestBody RequestWithUseridDto<Integer> request) throws IOException {
        // s3에 있는 이미지 삭제시켜줘야함
        User user = userService.getUser(request.getUserId());
        String imageId = user.getName() + "_" + request.getData();
        storageManager.deleteImage(imageId);

        fridgeService.delIngredient(request.getUserId(), request.getData());

        return new ResponseDto<Integer>(
                HttpStatus.OK.value(), 1);
    }

}