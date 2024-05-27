package ro.irian.labs2024.pizza.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.irian.lab2024.pizza.api.PizzaService;
import ro.irian.labs2024.order.domain.entity.Customer;
import ro.irian.labs2024.order.domain.entity.PizzaOrder;
import ro.irian.labs2024.order.repository.CustomerRepository;
import ro.irian.labs2024.order.repository.OrderRepository;
import ro.irian.labs2024.pizza.domain.entity.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final PizzaService pizzaService;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, PizzaService pizzaService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.pizzaService = pizzaService;
    }

    @Transactional
    public void saveOrders(){
        pizzaService.savePizzas();

        List<Pizza> pizzaList = pizzaService.getAllPizzas();

        Customer customer = new Customer("John Doe");
        Customer customer2 = new Customer("Andrei Popescu");

        customerRepository.save(customer);
        customerRepository.save(customer2);

        PizzaOrder pizzaOrder = new PizzaOrder();
        pizzaOrder.setCustomer(customer);
        pizzaOrder.setPizzaList(pizzaList);
        pizzaOrder.setPrice(new CurrencyValue(Currency.EUR, 37.0));
        pizzaOrder.setDate(LocalDateTime.now());

        orderRepository.save(pizzaOrder);

        PizzaOrder pizzaOrder2 = new PizzaOrder();
        pizzaOrder2.setCustomer(customer);
        pizzaOrder2.setPizzaList(List.of(pizzaList.get(0)));
        pizzaOrder2.setPrice(new CurrencyValue(Currency.EUR, 9.0));
        pizzaOrder.setDate(LocalDateTime.now());

        orderRepository.save(pizzaOrder2);


        PizzaOrder pizzaOrder3 = new PizzaOrder();
        pizzaOrder3.setCustomer(customer2);
        pizzaOrder3.setPizzaList(List.of(pizzaList.get(1), pizzaList.get(2)));
        pizzaOrder3.setPrice(new CurrencyValue(Currency.EUR, 20.5));
        pizzaOrder.setDate(LocalDateTime.now());

        orderRepository.save(pizzaOrder3);
    }

    public List<PizzaOrder> getAllOrders(){
        return orderRepository.findAll();
    }
}
