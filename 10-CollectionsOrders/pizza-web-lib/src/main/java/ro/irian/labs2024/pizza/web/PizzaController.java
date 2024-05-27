package ro.irian.labs2024.pizza.web;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;
import ro.irian.labs2024.pizza.domain.Pizza;
import ro.irian.lab2024.pizza.api.PizzaService;

import java.util.List;

@RestController
@RequestMapping("pizza")
public class PizzaController {

    public final List<PizzaService> pizzaService;

    public final PizzaService fastPizzaService;

    public PizzaController(List<PizzaService> pizzaService,
                           PizzaService fastPizzaService) {
        this.pizzaService = pizzaService;
        this.fastPizzaService = fastPizzaService;
    }

    @PostConstruct
    private void init() {
        fastPizzaService.savePizzas();
    }

    @GetMapping()
    public List<Pizza> getAllPizza() {
        return pizzaService.stream()
                .flatMap(pizzaServiceVar -> pizzaServiceVar.getAllPizzas().stream())
                .toList();
    }

    @GetMapping("/{pizzaId}")
    public Pizza getPizzaById(@PathVariable Long pizzaId) {
        return  pizzaService.stream()
                .flatMap(pizzaServiceVar -> pizzaServiceVar.getAllPizzas().stream())
                .filter(pizza -> pizza.getId().equals(pizzaId))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/filter")
    public List<Pizza> getPizzaNameContains(@RequestParam String nameContainsString) {
        return pizzaService.stream()
                .flatMap(pizzaServiceVar -> pizzaServiceVar.getPizzaByNameContains(nameContainsString).stream())
                .toList();
    }

    @GetMapping("/fast/{pizzaId}")
    public Pizza getFastPizzaById(@PathVariable Long pizzaId) {
        return fastPizzaService.getPizzaById(pizzaId);
    }

    @GetMapping("/filter/ingredient")
    public List<Pizza> getPizzaByIngredient(@RequestParam String ingredientName) {
        return fastPizzaService.getPizzaByIngredientName(ingredientName);
    }
}
