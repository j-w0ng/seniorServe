package seniorServe.seniorServe.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import seniorServe.seniorServe.model.Review;

import java.util.Arrays;
import java.util.List;

@Repository("reviewDaoPostgres")
public class ReviewDataAccessService implements ReviewDao
{
    private final JdbcTemplate jdbcTemplate;

    public ReviewDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertReview(String username, Review review)
    {
        String query = QueryHelper.insertQuery("makereview",
                Arrays.asList("Review_ID", "Description", "Rating", "Task_ID", "VUsername"),
                Arrays.asList(review.getReviewID(), review.getDescription(), Integer.toString(review.getRating()), Integer.toString(review.getTaskID()), review.getVolunteerUserName()));
        return jdbcTemplate.update(query);
    }

    @Override
    public List<Review> selectAllReviews()
    {
        String query = "SELECT * FROM makereview";
        return jdbcTemplate.query(query, (resultSet, i) ->
                new Review(resultSet.getString("Review_ID"),
                        resultSet.getString("Description"),
                        resultSet.getInt("Rating"),
                        resultSet.getInt("Task_ID"),
                        resultSet.getString("VUsername")));
    }

    @Override
    public Review selectReviewByReivewID(int reviewID)
    {
        String query = "SELECT * FROM makereview WHERE Review_ID = ?";
        try
        {
            return jdbcTemplate.queryForObject(query, new Object[]{reviewID}, (resultSet, i) ->
                    new Review(resultSet.getString("Review_ID"),
                            resultSet.getString("Description"),
                            resultSet.getInt("Rating"),
                            resultSet.getInt("Task_ID"),
                            resultSet.getString("VUsername")));
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public int deleteReview(int reviewID)
    {
        String query = "DELETE FROM makereview WHERE Review_ID = '" + reviewID + "'";
        return jdbcTemplate.update(query);
    }

    @Override
    public int updateReview(int reviewID, Review review)
    {
        String query = QueryHelper.updateQuery("makereview",
                Arrays.asList("Review_ID", "Description", "Rating", "Task_ID", "VUsername"),
                Arrays.asList(review.getReviewID(), review.getDescription(), Integer.toString(review.getRating()), Integer.toString(review.getTaskID()), review.getVolunteerUserName()),
                "Review_ID = '" + reviewID + "'");
        return jdbcTemplate.update(query);
    }
}
