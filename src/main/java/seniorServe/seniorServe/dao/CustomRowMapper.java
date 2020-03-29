package seniorServe.seniorServe.dao;

import seniorServe.seniorServe.model.Location;
import seniorServe.seniorServe.model.Review;
import seniorServe.seniorServe.model.Task;

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
}
