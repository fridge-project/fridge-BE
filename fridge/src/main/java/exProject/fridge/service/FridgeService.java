package exProject.fridge.service;

import exProject.fridge.model.*;
import exProject.fridge.repository.FridgeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class FridgeService {
    @Autowired
    private final FridgeRepository fridgeRepository;

    @Transactional // 재료 존재 여부
    public boolean isExist(int user_id, int ingre_id) { // 오류
         if(fridgeRepository.findIngredient(user_id, ingre_id) != null) return true;

         return false;
    }

    @Transactional // 재료 추가
    public void addIngredient(Fridge fridge) {
        fridgeRepository.save(fridge);
    }

    @Transactional
    public List<ResFridge> getIngredients(User user) {

        List<ResFridge> res = fridgeRepository.findByUserId(user.getId());

        return fridgeRepository.findByUserId(user.getId());
    }

    @Transactional(readOnly = true)
    public Fridge getIngredient(int user_id, int ingre_id) {
        return fridgeRepository.findIngredient(user_id, ingre_id);
    }

    @Transactional
    public void delIngredient(int user_id, int ingre_id) {
//        fridgeRepository.delIngredients(user_id, ingre_id);
        fridgeRepository.deleteById(new FridgeId(user_id, ingre_id));
    }
}