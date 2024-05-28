package ro.irian.labs2024.order.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import ro.irian.labs2024.order.domain.entity.Review;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByPizzaNameEqualsIgnoreCaseOrderByDateDesc(String name);
}
