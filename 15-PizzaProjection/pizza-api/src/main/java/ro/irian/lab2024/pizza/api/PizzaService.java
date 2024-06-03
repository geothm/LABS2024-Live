package ro.irian.lab2024.pizza.api;

import ro.irian.labs2024.pizza.domain.entity.Pizza;

import java.util.List;

public interface PizzaService {
    void savePizzas();

    List<Pizza> getAllPizzas();

    Pizza getPizzaById(Long id);

    List<Pizza> getPizzaByNameContains(String nameContainsString);

    List<Pizza> getPizzaByIngredientName(String ingredientName);
}
