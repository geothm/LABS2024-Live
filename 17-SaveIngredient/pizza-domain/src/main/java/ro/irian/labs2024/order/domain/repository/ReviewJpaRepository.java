package ro.irian.labs2024.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.irian.labs2024.order.domain.entity.Review;
import ro.irian.labs2024.order.domain.vo.PizzaReviewVO;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByPizzaNameEqualsIgnoreCaseOrderByDateDesc(String name);

    @Query("""
            SELECT new ro.irian.labs2024.order.domain.vo.PizzaReviewVO(
                                                   p.id,
                                                   p.name,
                                                   avg(r.rating)
                                               )
                                               FROM Review r
                                               LEFT JOIN r.pizza p
                                               GROUP BY p.id, p.name                                                                                            
        """)
    List<PizzaReviewVO> findAllPizzaAvgRating();
}
