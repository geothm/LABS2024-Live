package ro.irian.labs2024.pizza.service;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.irian.lab2024.pizza.api.PizzaService;
import ro.irian.labs2024.pizza.domain.entity.Currency;
import ro.irian.labs2024.pizza.domain.entity.CurrencyValue;
import ro.irian.labs2024.pizza.domain.entity.Ingredient;
import ro.irian.labs2024.pizza.domain.entity.Pizza;
import ro.irian.labs2024.pizza.domain.repository.IngredientRepository;
import ro.irian.labs2024.pizza.domain.repository.PizzaJpaRepository;
import ro.irian.labs2024.pizza.domain.repository.PizzaRepository;
import ro.irian.labs2024.pizza.domain.vo.PizzaVO;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Primary
public class ItalianPizzaService implements PizzaService {

    private final PizzaRepository pizzaRepository;
    private final IngredientRepository ingredientRepository;
    private final PizzaJpaRepository pizzaJpaRepository;

    public ItalianPizzaService(PizzaRepository pizzaRepository,
                               IngredientRepository ingredientRepository,
                               PizzaJpaRepository pizzaJpaRepository) {
        this.pizzaRepository = pizzaRepository;
        this.ingredientRepository = ingredientRepository;
        this.pizzaJpaRepository = pizzaJpaRepository;
    }

    @Transactional
    @Override
    public void savePizzas(){

        Ingredient ingredient = new Ingredient(null, "tomato", new CurrencyValue(Currency.EUR, 5.0));
        Ingredient ingredient1 = new Ingredient(null, "mozzarella",new CurrencyValue(Currency.EUR, 6.0));
        Ingredient ingredient2 = new Ingredient(null, "salami", new CurrencyValue(Currency.EUR,8.0));
        ingredientRepository.save(ingredient);
        ingredientRepository.save(ingredient1);
        ingredientRepository.save(ingredient2);


        Pizza pizza = new Pizza(null, "Salami", 10.0, LocalDateTime.now(), List.of("milk", "eggs", "gluten"));
        List<Ingredient> salamiIngredients = List.of(ingredient, ingredient1, ingredient2);
        pizza.setIngredients(salamiIngredients);
        pizzaRepository.save(pizza);


        Pizza pizza1 = new Pizza(null, "Prosciutto", 12.0, LocalDateTime.now(), List.of("eggs", "gluten"));

        List<Ingredient> prosciuttoIngredients = List.of(ingredient, ingredient1);
        pizza1.setIngredients(prosciuttoIngredients);
        pizzaRepository.save(pizza1);


        Pizza pizza2 = new Pizza(null, "Capriciosa", 15.0, LocalDateTime.now(), List.of("gluten"));
        pizzaJpaRepository.save(pizza2);
    }

    @Override
    public List<Pizza> getAllPizzas(){
        return pizzaRepository.findAll();
    }

    @Override
    public Pizza getPizzaById(Long id){
        return pizzaRepository.findById(id);
    }

    @Override
    public List<Pizza> getPizzaByNameContains(String nameContainsString){
        return pizzaRepository.findNameContains(nameContainsString);
    }

    @Override
    public List<Pizza> getPizzaByIngredientName(String ingredientName) {
        return pizzaRepository.findByIngredientName(ingredientName);
    }

    public List<PizzaVO> getPizzaVOs(){
        return pizzaRepository.getPizzaVOs();
    }

    public List<Pizza> getAllDataPizzas(){
        return pizzaJpaRepository.findAll();
    }

    public Page<Pizza> getPagedPizzas(Pageable pageable){
        return pizzaJpaRepository.findAll(pageable);
    }

    public Page<Pizza> getPagedPizzasByName(String name, Pageable pageable){
        return pizzaJpaRepository.findAllByNameEqualsIgnoreCase(name, pageable);
    }
}
