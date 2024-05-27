package ro.irian.labs2024.pizza.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ro.irian.labs2024.pizza.domain.entity.Pizza;

public interface PizzaJpaRepository extends JpaRepository<Pizza, Long> {

    Page<Pizza> findAllByNameEqualsIgnoreCase(String name, Pageable pageable);
}
