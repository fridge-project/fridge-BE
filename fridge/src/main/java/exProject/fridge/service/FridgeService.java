package exProject.fridge.service;

import exProject.fridge.model.Fridge;
import exProject.fridge.model.FridgeId;
import exProject.fridge.repository.FridgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FridgeService {

    @Autowired
    private FridgeRepository fridgeRepository;

    @Transactional // 재료 존재 여부
    public void isExist(int user_id, int ingre_id) {
         fridgeRepository.findIngredients(user_id, ingre_id);


    }

    @Transactional // 재료 추가
    public void addIngredient(Fridge fridge) {
        fridgeRepository.save(fridge);
    }


}