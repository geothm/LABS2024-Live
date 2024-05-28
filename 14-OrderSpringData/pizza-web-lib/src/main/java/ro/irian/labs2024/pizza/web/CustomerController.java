package ro.irian.labs2024.pizza.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.irian.labs2024.order.domain.vo.CustomerVO;
import ro.irian.labs2024.pizza.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    public final OrderService orderService;

    public CustomerController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/vo")
    public List<CustomerVO> getCustomerVos() {
        return orderService.getCustomerVOs();
    }
}
