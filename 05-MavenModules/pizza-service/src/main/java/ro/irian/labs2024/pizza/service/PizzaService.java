package ro.irian.labs2024.pizza.service;

import ro.irian.labs2024.pizza.domain.Pizza;

import java.util.List;

public interface PizzaService {
    List<Pizza> getAllPizzas();

    Pizza getPizzaById(Long id);

    List<Pizza> getPizzaByNameContains(String nameContainsString);
}
