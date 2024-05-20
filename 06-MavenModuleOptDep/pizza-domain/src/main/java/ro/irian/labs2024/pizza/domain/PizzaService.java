package ro.irian.labs2024.pizza.domain;

import java.util.List;

public interface PizzaService {
    void savePizzas();

    List<Pizza> getAllPizzas();

    Pizza getPizzaById(Long id);

    List<Pizza> getPizzaByNameContains(String nameContainsString);
}
