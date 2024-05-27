package ro.irian.lab2024.pizza.api;

import org.springframework.transaction.annotation.Transactional;
import ro.irian.labs2024.pizza.domain.Pizza;

import java.util.List;

public interface PizzaService {
    void savePizzas();

    List<Pizza> getAllPizzas();

    Pizza getPizzaById(Long id);

    List<Pizza> getPizzaByNameContains(String nameContainsString);

    List<Pizza> getPizzaByIngredientName(String ingredientName);
}
