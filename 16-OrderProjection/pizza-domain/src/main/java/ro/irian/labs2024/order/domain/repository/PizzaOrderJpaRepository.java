package ro.irian.labs2024.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.irian.labs2024.order.domain.entity.PizzaOrder;
import ro.irian.labs2024.order.domain.vo.OrderProjection;

import java.util.List;

public interface PizzaOrderJpaRepository extends JpaRepository<PizzaOrder, Long> {

    List<OrderProjection> findAllProjectedBy();
}
