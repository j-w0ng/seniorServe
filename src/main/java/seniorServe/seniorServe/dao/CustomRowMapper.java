package seniorServe.seniorServe.dao;

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
}
