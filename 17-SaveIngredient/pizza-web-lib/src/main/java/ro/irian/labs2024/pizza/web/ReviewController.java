package ro.irian.labs2024.pizza.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.irian.labs2024.order.domain.entity.Review;
import ro.irian.labs2024.order.domain.vo.PizzaReviewVO;
import ro.irian.labs2024.pizza.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping()
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/pizza")
    public List<Review> getAllReviewsByPizzaName(@RequestParam String name) {
        return reviewService.getAllReviewsByPizzaName(name);
    }

    @GetMapping("/paged")
    public Page<Review> getAllReviewsPaged(@RequestParam Pageable pageable) {
        return reviewService.getAllReviewsPaged(pageable);
    }

    @GetMapping("/avg")
    public List<PizzaReviewVO> getAllReviewsAvgForPizzas() {
        return reviewService.getAllReviewsAvgForPizzas();
    }
}
