package exProject.fridge.service;


import exProject.fridge.model.Fridge;
import exProject.fridge.model.FridgeId;
import exProject.fridge.model.Ingredient;
import exProject.fridge.model.User;
import exProject.fridge.repository.FridgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FridgeService {
    @Autowired
    private FridgeRepository fridgeRepository;

    @Transactional // 재료 존재 여부
    public boolean isExist(int user_id, int ingre_id) { // 오류
         if(fridgeRepository.findIngredients(user_id, ingre_id) != null) return true;

         return false;
    }

    @Transactional // 재료 추가
    public void addIngredient(Fridge fridge) {
        fridgeRepository.save(fridge);
    }

    @Transactional
    public List<Fridge> getIngredient(User user) {

        List<Fridge> res = fridgeRepository.findByUserId(user.getId());

        return fridgeRepository.findByUserId(user.getId());
    }
}