package seniorServe.seniorServe.dao;

import seniorServe.seniorServe.model.Review;

import java.util.List;

public interface ReviewDao
{
    int insertReview(int reviewID, Review review);

    List<Review> selectAllReviews();

    Review selectReviewByReivewID(int reviewID);

    int deleteReview(int reviewID);

    int updateReview(int reviewID, Review review);
}
