package ro.irian.labs2024.pizza.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.irian.labs2024.pizza.domain.entity.Ingredient;
import ro.irian.labs2024.pizza.domain.repository.IngredientJpaRepository;
import ro.irian.labs2024.pizza.domain.vo.IngredientProjection;
import ro.irian.labs2024.pizza.dto.IngredientDTO;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientJpaRepository ingredientJpaRepository;

    public IngredientService(IngredientJpaRepository ingredientJpaRepository) {
        this.ingredientJpaRepository = ingredientJpaRepository;
    }

    @Transactional
    public Long saveIngredient(IngredientDTO ingredientDTO){
        Ingredient ingredient = new Ingredient(null, ingredientDTO.getName(), ingredientDTO.getPrice());
        ingredientJpaRepository.save(ingredient);

        return ingredient.getId();
    }

    @Transactional
    public Long updateIngredient(IngredientDTO ingredientDTO){
        Ingredient ingredient = ingredientJpaRepository.findById(ingredientDTO.getId()).orElseThrow();
        ingredient.setName(ingredientDTO.getName());
        ingredient.setPrice(ingredientDTO.getPrice());
        ingredientJpaRepository.save(ingredient);

        return ingredient.getId();
    }

    public List<IngredientProjection> findAllIngredients(){
        return ingredientJpaRepository.findAllProjectedBy();
    }

    public IngredientDTO getIngredientDTOById(Long id){
        Ingredient ingredient = ingredientJpaRepository.findById(id).orElseThrow();
        return new IngredientDTO(ingredient.getId(), ingredient.getName(), ingredient.getPrice());
    }
}
