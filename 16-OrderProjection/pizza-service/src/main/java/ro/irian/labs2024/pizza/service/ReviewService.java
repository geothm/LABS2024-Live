package ro.irian.labs2024.pizza.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.irian.labs2024.order.domain.entity.Review;
import ro.irian.labs2024.order.domain.repository.ReviewJpaRepository;
import ro.irian.labs2024.order.domain.vo.PizzaReviewVO;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewJpaRepository reviewJpaRepository;

    public ReviewService(ReviewJpaRepository reviewJpaRepository) {
        this.reviewJpaRepository = reviewJpaRepository;
    }

    public List<Review> getAllReviews() {
        return reviewJpaRepository.findAll();
    }

    public List<Review> getAllReviewsByPizzaName(String name) {
        return reviewJpaRepository.findAllByPizzaNameEqualsIgnoreCaseOrderByDateDesc(name);
    }

    public Page<Review> getAllReviewsPaged(Pageable pageable) {
        return reviewJpaRepository.findAll(pageable);
    }

    public List<PizzaReviewVO> getAllReviewsAvgForPizzas() {
        return reviewJpaRepository.findAllPizzaAvgRating();
    }
}
