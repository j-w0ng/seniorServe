package seniorServe.seniorServe.dao;

import seniorServe.seniorServe.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomRowMapper {
    public static Task TaskRowMapper(ResultSet rs, int i) throws SQLException {
        return new Task(rs.getInt("Task_ID"),
                rs.getDate("Date"),
                rs.getString("Description"),
                rs.getInt("Num_Volunteer"),
                rs.getString("Status"),
                rs.getString("PostalCode"),
                rs.getString("Address"),
                rs.getString("Username"),
                rs.getDate("CreateTime"));
    }

    public static TaskLocation TaskLocationRowMapper(ResultSet rs, int i) throws SQLException {
        return new TaskLocation(rs.getInt("Task_ID"),
                rs.getDate("Date"),
                rs.getString("Description"),
                rs.getInt("Num_Volunteer"),
                rs.getString("Status"),
                rs.getString("PostalCode"),
                rs.getString("Address"),
                rs.getString("Username"),
                rs.getDate("CreateTime"),
                rs.getString("City"),
                rs.getString("Province"));
    }

    public static Review ReviewRowMapper(ResultSet rs, int i) throws SQLException {
        return new Review(rs.getInt("Review_ID"),
                rs.getString("Description"),
                rs.getInt("Rating"),
                rs.getInt("Task_ID"),
                rs.getString("VUsername"));
    }

    public static Location LocationRowMapper(ResultSet rs, int i) throws SQLException {
        return new Location(rs.getString("PostalCode"),
                rs.getString("Address"),
                rs.getString("City"),
                rs.getString("Province"));
    }

    public static UserRating UserRatingRowMapper(ResultSet rs, int i) throws SQLException {
        return new UserRating(rs.getString("Username"),
                rs.getDouble("Rating"));
    }

    public static User UserRowMapper(ResultSet rs, int i) throws SQLException {
        return new User(rs.getString("username"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("postalCode"),
                rs.getString("address"));
    }

    public static String UsernameRowMapper(ResultSet rs, int i) throws SQLException {
        return rs.getString("username");
    }

    public static TaskCompletion TaskCompletionRowMapper(ResultSet rs, int i) throws SQLException {
        return new TaskCompletion(rs.getInt("complete_ID"),
                rs.getDate("date"),
                rs.getInt("task_ID"),
                rs.getDouble("monetaryAmount"),
                rs.getString("username"));
    }
}
