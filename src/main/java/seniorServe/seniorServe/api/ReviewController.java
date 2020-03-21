package seniorServe.seniorServe.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import seniorServe.seniorServe.model.Review;
import seniorServe.seniorServe.service.ReviewService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/review")
@RestController
public class ReviewController
{
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService)
    {
        this.reviewService = reviewService;
    }

    @PostMapping
    public void addReview(@Valid @NonNull @RequestBody Review review)
    {
        reviewService.addReview(review);
    }

    @GetMapping
    public List<Review> getReviews()
    {
        return reviewService.getAllReviews();
    }

    @GetMapping(path = "{reviewID}")
    public Review getReviewByReviewID(@PathVariable("reviewID") int reviewID)
    {
        return reviewService.getReviewByReviewID(reviewID);
    }

    @DeleteMapping(path = "{reviewID}")
    public void deleteReview(@PathVariable("reviewID") int reviewID)
    {
        reviewService.deleteReview(reviewID);
    }

    @PutMapping(path = "{reviewID}")
    public void updateReview(@PathVariable("reviewID") int reviewID, @Valid @NonNull @RequestBody Review review)
    {
        reviewService.updateReview(reviewID, review);
    }
}
