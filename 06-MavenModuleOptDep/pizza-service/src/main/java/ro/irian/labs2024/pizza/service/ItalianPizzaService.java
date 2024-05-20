package ro.irian.labs2024.pizza.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.irian.labs2024.pizza.domain.Pizza;
import ro.irian.labs2024.pizza.domain.PizzaRepository;
import ro.irian.labs2024.pizza.domain.PizzaService;

import java.util.List;

@Service
@Primary
public class ItalianPizzaService implements PizzaService {

    private final PizzaRepository pizzaRepository;

    private List<Pizza> pizzas;

    public ItalianPizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Transactional
    @Override
    public void savePizzas(){
        pizzaRepository.save(new Pizza(null, "Salami"));
        pizzaRepository.save(new Pizza(null, "Prosciutto"));
        pizzaRepository.save(new Pizza(null, "Capriciosa"));
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
}
