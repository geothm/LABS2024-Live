package ro.irian.labs2024.pizza.web;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;
import ro.irian.labs2024.order.domain.entity.PizzaOrder;
import ro.irian.labs2024.order.domain.vo.OrderProjection;
import ro.irian.labs2024.order.domain.vo.OrderVO;
import ro.irian.labs2024.pizza.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    public final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostConstruct
    private void init() {
        orderService.saveOrders();
    }

    @GetMapping()
    public List<PizzaOrder> getAllPizza() {
        return orderService.getAllOrders();
    }

    @GetMapping("/vo")
    public List<OrderVO> getOrderVos() {
        return orderService.getOrderVOs();
    }

    @GetMapping("/projection")
    public List<OrderProjection> getAllOrdersProjected() {
        return orderService.getOrderProjections();
    }
}