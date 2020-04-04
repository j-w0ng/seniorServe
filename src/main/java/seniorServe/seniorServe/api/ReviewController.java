package seniorServe.seniorServe.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import seniorServe.seniorServe.model.Review;
import seniorServe.seniorServe.model.UserRating;
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
    public Review getReviewByReviewID(@PathVariable("reviewID") int reviewID) {
        return reviewService.getReviewByReviewID(reviewID);
    }

    /**
     * Gets all reviews related to the given task_ID
     *
     * Example output
     * [
     *     {
     *         "reviewID": 10,
     *         "description": "Great Help! My lawn looks amazing!",
     *         "rating": 10,
     *         "taskID": 1,
     *         "volunteerUserName": "Ann34"
     *     }
     * ]
     * @param taskID
     * @return
     */
    @GetMapping(path = "/task_ID={TaskID}")
    public List<Review> getReviewByTaskID(@PathVariable("TaskID") int taskID) {
        return reviewService.getReviewByTaskID(taskID);
    }

    /**
     * Returns the list of reviews written ABOUT given username
     * @param VUsername
     * @return
     */
    @GetMapping(path = "/volunteer={VUsername}")
    public List<Review> getReviewByVUsername(@PathVariable("VUsername") String VUsername) {
        return reviewService.getReviewsByVolunteer(VUsername);
    }

    /**
     * Returns the list of reviews written BY given username
     * @param SUsername
     * @return
     */
    @GetMapping(path = "/senior={SUsername}")
    public List<Review> getReviewBySUsername(@PathVariable("SUsername") String SUsername) {
        return reviewService.getReviewsBySenior(SUsername);
    }


    @GetMapping(path = "/averageRating/task_ID={TaskID}")
    public double getAverageRatingByTaskID(@PathVariable("TaskID") int taskID) {
        return reviewService.getAverageRatingByTaskID(taskID);
    }

    @GetMapping(path = "/averageRating/volunteer={VUser}")
    public double getAverageRatingByVUsername(@PathVariable("VUser") String VUsername) {
        return reviewService.getAverageRatingByVolunteer(VUsername);
    }

    /**
     * Gets all volunteers who have at least one review.
     *
     * If you want to get all volunteers, use Volunteer endpoint
     * @return
     */
    @GetMapping(path = "/distinctUsersWithReviews")
    public List<String> getDistinctUsers() {
        return reviewService.getDistinctUsersInReview();
    }

    /**
     * Get all volunteer ratings for those with ratings.
     *
     * For a complete VolunteerHours and VolunteerRatings for each Volunteer, please consult
     *  "api/v1/volunteerRecord/allUserRatingHours"
     * @return
     */
    @GetMapping(path = "/allAverageRating")
    public List<UserRating> getAverageRatings() {
        return reviewService.getAllAverageRatings();
    }

    @DeleteMapping(path = "{reviewID}")
    public void deleteReview(@PathVariable("reviewID") int reviewID)
    {
        reviewService.deleteReview(reviewID);
    }

    @PutMapping(path = "{reviewID}")
    public void updateReview(@PathVariable("reviewID") int reviewID, @Valid @NonNull @RequestBody Review review) {
        reviewService.updateReview(reviewID, review);
    }
}
