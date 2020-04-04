package seniorServe.seniorServe.dao;

import seniorServe.seniorServe.model.Review;
import seniorServe.seniorServe.model.UserRating;

import java.util.List;

public interface ReviewDao
{
    int insertReview(int reviewID, Review review);

    List<Review> selectAllReviews();

    Review selectReviewByReivewID(int reviewID);

    List<Review> selectReviewByTaskID(int taskID);

    List<Review> selectReviewsByVolunteer(String VUsername);

    List<Review> selectReviewsBySenior(String SUsername);

    List<UserRating> getAllAverageReviews();

    List<String> getAllUsers();

    double getAverageRatingByTaskID(int taskID);

    double getAverageRatingBySenior(String SUsername);

    double getAverageRatingByVolunteer(String VUsername);

    int deleteReview(int reviewID);

    int updateReview(int reviewID, Review review);
}
