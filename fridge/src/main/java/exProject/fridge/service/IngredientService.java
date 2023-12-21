package exProject.fridge.service;

import exProject.fridge.model.Ingredient;
import exProject.fridge.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    @Transactional
    public Ingredient getIngredient(String name) {
        Ingredient ingredient = ingredientRepository.findByName(name);

        return ingredient;
    }

}

