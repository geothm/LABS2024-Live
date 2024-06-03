package ro.irian.labs2024.pizza.web;

import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ro.irian.labs2024.pizza.domain.entity.Pizza;
import ro.irian.lab2024.pizza.api.PizzaService;
import ro.irian.labs2024.pizza.domain.vo.PizzaProjection;
import ro.irian.labs2024.pizza.domain.vo.PizzaVO;
import ro.irian.labs2024.pizza.service.ItalianPizzaService;

import java.util.List;

@RestController
@RequestMapping("pizza")
public class PizzaController {

    public final List<PizzaService> pizzaService;

    public final ItalianPizzaService fastPizzaService;

    public PizzaController(List<PizzaService> pizzaService,
                           ItalianPizzaService fastPizzaService) {
        this.pizzaService = pizzaService;
        this.fastPizzaService = fastPizzaService;
    }

    /*@PostConstruct
    private void init() {
        fastPizzaService.savePizzas();
    }
*/
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

    @GetMapping("/vo")
    public List<PizzaVO> getPizzaVOs() {
        return fastPizzaService.getPizzaVOs();
    }

    @GetMapping("/data")
    public List<Pizza> getDataPizzas() {
        return fastPizzaService.getAllDataPizzas();
    }

    @GetMapping("/data/paged")
    public Page<Pizza> getDataPizzas(Pageable pageable) {
        return fastPizzaService.getPagedPizzas(pageable);
    }

    @GetMapping("/data/name")
    public Page<Pizza> getDataPizzas(@RequestParam String name, Pageable pageable) {
        return fastPizzaService.getPagedPizzasByName(name, pageable);
    }

    @GetMapping("/data/vo")
    public List<PizzaVO> getDataPizzaVOs() {
        return fastPizzaService.getDataPizzaVOs();
    }

    @GetMapping("/data/vo/name")
    public List<PizzaVO> getDataPizzaVOsByName(@RequestParam String name) {
        return fastPizzaService.getPizzaVOsByName(name);
    }

    @GetMapping("/data/projection")
    public List<PizzaProjection> getDataPizzaProjection() {
        return fastPizzaService.getPizzaProjections();
    }

    @GetMapping("/data/projection/name")
    public List<PizzaProjection> getDataPizzaProjection(@RequestParam String name) {
        return fastPizzaService.getPizzaProjectionsByName(name);
    }

    @GetMapping("/data/projection/paged")
    public Page<PizzaProjection> getDataPizzaProjectionPaged(Pageable pageable) {
        return fastPizzaService.getPizzaProjectionsPaged(pageable);
    }
}
