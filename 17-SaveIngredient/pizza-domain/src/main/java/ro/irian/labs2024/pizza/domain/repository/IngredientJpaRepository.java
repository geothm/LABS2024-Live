package ro.irian.labs2024.pizza.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.irian.labs2024.pizza.domain.entity.Ingredient;
import ro.irian.labs2024.pizza.domain.vo.IngredientProjection;

import java.util.List;

public interface IngredientJpaRepository extends JpaRepository<Ingredient, Long> {

    List<IngredientProjection> findAllProjectedBy();

}
