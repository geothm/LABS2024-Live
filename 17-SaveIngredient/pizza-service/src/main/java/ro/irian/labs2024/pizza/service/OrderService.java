package ro.irian.labs2024.pizza.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.irian.lab2024.pizza.api.PizzaService;
import ro.irian.labs2024.order.domain.entity.Customer;
import ro.irian.labs2024.order.domain.entity.PizzaOrder;
import ro.irian.labs2024.order.domain.entity.Review;
import ro.irian.labs2024.order.domain.repository.CustomerRepository;
import ro.irian.labs2024.order.domain.repository.OrderRepository;
import ro.irian.labs2024.order.domain.repository.PizzaOrderJpaRepository;
import ro.irian.labs2024.order.domain.repository.ReviewJpaRepository;
import ro.irian.labs2024.order.domain.vo.CustomerVO;
import ro.irian.labs2024.order.domain.vo.OrderProjection;
import ro.irian.labs2024.order.domain.vo.OrderVO;
import ro.irian.labs2024.pizza.domain.entity.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final PizzaService pizzaService;
    private final ReviewJpaRepository reviewJpaRepository;
    private final PizzaOrderJpaRepository pizzaOrderJpaRepository;

    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        PizzaService pizzaService,
                        ReviewJpaRepository reviewJpaRepository,
                        PizzaOrderJpaRepository pizzaOrderJpaRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.pizzaService = pizzaService;
        this.reviewJpaRepository = reviewJpaRepository;
        this.pizzaOrderJpaRepository = pizzaOrderJpaRepository;
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

        Review review = new Review(customer, pizzaList.get(0), 5, "Great pizza");
        Review review2 = new Review(customer, pizzaList.get(1), 4, "Good pizza");
        Review review3 = new Review(customer2, pizzaList.get(2), 3, "Ok pizza");
        Review review4 = new Review(customer2, pizzaList.get(0), 2, "Bad pizza");

        reviewJpaRepository.saveAll(List.of(review, review2, review3, review4));
    }

    public List<PizzaOrder> getAllOrders(){
        return orderRepository.findAll();
    }

    public List<OrderVO> getOrderVOs(){
        return orderRepository.getOrderVOs();
    }

    public List<CustomerVO> getCustomerVOs(){
        return customerRepository.getCustomerVOs();
    }

    public List<OrderProjection> getOrderProjections(){
        return pizzaOrderJpaRepository.findAllProjectedBy();
    }
}
