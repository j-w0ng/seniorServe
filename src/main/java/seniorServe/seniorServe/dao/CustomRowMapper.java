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

    public static TaskLocationRequest TaskLocationRequestRowMapper(ResultSet rs, int i) throws SQLException {
        return new TaskLocationRequest(rs.getInt("Task_ID"),
                rs.getDate("Date"),
                rs.getString("Description"),
                rs.getInt("Num_Volunteer"),
                rs.getString("Status"),
                rs.getString("PostalCode"),
                rs.getString("Address"),
                rs.getString("Username"),
                rs.getDate("CreateTime"),
                rs.getString("City"),
                rs.getString("Province"),
                rs.getDate("RequestDate"));
    }

    public static TaskLocationCompletion TaskLocationCompletionRowMapper(ResultSet rs, int i) throws SQLException {
        return new TaskLocationCompletion(rs.getInt("Task_ID"),
                rs.getDate("Date"),
                rs.getString("Description"),
                rs.getInt("Num_Volunteer"),
                rs.getString("Status"),
                rs.getString("PostalCode"),
                rs.getString("Address"),
                rs.getString("Username"),
                rs.getDate("CreateTime"),
                rs.getString("City"),
                rs.getString("Province"),
                rs.getDate("CompletionDate"));
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
                rs.getString("first_name"),
                rs.getString("last_name"),
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

    public static TaskRequest TaskRequestRowMapper(ResultSet rs, int i) throws SQLException {
        return new TaskRequest(rs.getInt("request_ID"),
                rs.getDate("date"),
                rs.getInt("task_ID"),
                rs.getString("username"));
    }

    public static VolunteerTimeEntryRecord VolunteerRecordRowMapper(ResultSet rs, int i) throws SQLException {
        return new VolunteerTimeEntryRecord(rs.getInt("record_ID"),
                rs.getDate("date"),
                rs.getTime("timeOfDay"),
                rs.getInt("hours"),
                rs.getString("username"),
                rs.getInt("task_id"));
    }

    public static UserRatingHours UserRatingHoursRowMapper(ResultSet rs, int i) throws SQLException {
        return new UserRatingHours(rs.getString("username"),
                rs.getDouble("rating"),
                rs.getInt("totalHours"));
    }

    public static UserRatingHoursDate UserRatingHoursDateRowMapper(ResultSet rs, int i) throws SQLException {
        return new UserRatingHoursDate(rs.getString("username"),
                rs.getDouble("rating"),
                rs.getInt("totalHours"),
                rs.getDate("recentDate"));
    }

    public static String SeniorVolunteerRowMapper(ResultSet rs, int i) throws SQLException {
        return rs.getString("username");
    }

    public static VolunteerEvent VolunteerEventRowMapper(ResultSet rs, int i) throws SQLException {
        return new VolunteerEvent(rs.getString("username"),
                rs.getInt("task_id"),
                rs.getDate("date"));
    }
}
