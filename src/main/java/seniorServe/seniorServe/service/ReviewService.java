package seniorServe.seniorServe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import seniorServe.seniorServe.dao.ReviewDao;
import seniorServe.seniorServe.model.Review;

import java.util.List;

@Service
public class ReviewService
{
    private final ReviewDao reviewDao;

    @Autowired
    public ReviewService(@Qualifier("reviewDaoPostgres") ReviewDao reviewDao)
    {
        this.reviewDao = reviewDao;
    }

    public int addReview(Review review)
    {
        return reviewDao.insertReview(review.getReviewID(), review);
    }

    public List<Review> getAllReviews()
    {
        return reviewDao.selectAllReviews();
    }

    public Review getReviewByReviewID(int reviewID)
    {
        return reviewDao.selectReviewByReivewID(reviewID);
    }

    public void deleteReview(int reviewID)
    {
        reviewDao.deleteReview(reviewID);
    }

    public void updateReview(int reviewID, Review review)
    {
        reviewDao.updateReview(reviewID, review);
    }
}
