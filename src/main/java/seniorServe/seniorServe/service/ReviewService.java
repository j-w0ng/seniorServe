package seniorServe.seniorServe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import seniorServe.seniorServe.dao.ReviewDao;
import seniorServe.seniorServe.model.Review;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
        return reviewDao.insertReview(ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE), review);
    }

    public List<Review> getAllReviews()
    {
        return reviewDao.selectAllReviews();
    }

    public Review getReviewByReviewID(int reviewID)
    {
        return reviewDao.selectReviewByReivewID(reviewID);
    }

    public List<Review> getReviewByTaskID(int taskID) {
        return reviewDao.selectReviewByTaskID(taskID);
    }

    public double getAverageRatingByTaskID(int taskID) {
        return reviewDao.getAverageRatingByTaskID(taskID);
    }

    public double getAverageRatingByVolunteer(String VUsername) {
        return reviewDao.getAverageRatingByVolunteer(VUsername);
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
