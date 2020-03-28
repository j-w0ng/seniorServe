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

    @GetMapping(path = "/task_ID={TaskID}")
    public List<Review> getReviewByTaskID(@PathVariable("TaskID") int taskID) {
        return reviewService.getReviewByTaskID(taskID);
    }

    @GetMapping(path = "/volunteer={VUsername}")
    public List<Review> getReviewByUsername(@PathVariable("VUsername") String VUsername) {
        return reviewService.getReviewsByVolunteer(VUsername);
    }

    @GetMapping(path = "/averageRating/task_ID={TaskID}")
    public double getAverageRatingByTaskID(@PathVariable("TaskID") int taskID) {
        return reviewService.getAverageRatingByTaskID(taskID);
    }

    @GetMapping(path = "/averageRating/volunteer={VUser}")
    public double getAverageRatingByVUsername(@PathVariable("VUser") String VUsername) {
        return reviewService.getAverageRatingByVolunteer(VUsername);
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
