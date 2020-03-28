package seniorServe.seniorServe.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
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
    public int insertReview(int reviewID, Review review)
    {
        String query = QueryHelper.insertQuery("makereview",
                Arrays.asList("Review_ID", "Description", "Rating", "Task_ID", "VUsername"),
                Arrays.asList(Integer.toString(reviewID), review.getDescription(), Integer.toString(review.getRating()), Integer.toString(review.getTaskID()), review.getVolunteerUserName()));
        return jdbcTemplate.update(query);
    }

    @Override
    public List<Review> selectAllReviews()
    {
        String query = "SELECT * FROM makereview";
        return jdbcTemplate.query(query, (resultSet, i) ->
                new Review(resultSet.getInt("Review_ID"),
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
                    new Review(resultSet.getInt("Review_ID"),
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
                Arrays.asList(Integer.toString(review.getReviewID()), review.getDescription(), Integer.toString(review.getRating()), Integer.toString(review.getTaskID()), review.getVolunteerUserName()),
                "Review_ID = '" + reviewID + "'");
        return jdbcTemplate.update(query);
    }

    @Override
    public List<Review> selectReviewByTaskID(int taskID) {
        String sqlQuery = "SELECT Review_ID, Description, Rating, Task_ID, VUsername " +
                "FROM MakeReview WHERE Task_ID = " + taskID;
        return jdbcTemplate.query(sqlQuery, CustomRowMapper::ReviewRowMapper);
    }


    /**
     *
     * @param taskID: The taskID of a review
     * @return The average rating of all the reviews related to the taskID
     *      or -1 is no reviews exist for that taskID
     */
    @Override
    public double getAverageRatingByTaskID(int taskID) {
        String sqlQuery = "SELECT to_char(AVG (rating),'99D99')" +
                " FROM MakeReview " +
                " WHERE Task_ID = " + taskID +
                " GROUP BY VUsername ";
        try {
            Object result = jdbcTemplate.queryForObject(sqlQuery, Double.class);
            return (double) result;
        } catch (IncorrectResultSizeDataAccessException e) {
            return -1;
        }
    }

    @Override
    public double getAverageRatingByVolunteer(String VUsername) {
        String sqlQuery = "SELECT to_char(AVG (rating),'99D99')" +
                " FROM MakeReview " +
                " WHERE VUsername = '" + VUsername + "'" +
                " GROUP BY VUsername ";
        try {
            Object result = jdbcTemplate.queryForObject(sqlQuery, Double.class);
            return (double) result;
        } catch (IncorrectResultSizeDataAccessException e) {
            return -1;
        }
    }
}
