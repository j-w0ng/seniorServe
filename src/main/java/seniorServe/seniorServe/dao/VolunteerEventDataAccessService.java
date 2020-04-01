package seniorServe.seniorServe.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import seniorServe.seniorServe.model.TaskLocation;
import seniorServe.seniorServe.model.VolunteerEvent;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository("volunteerEventPostgres")
public class VolunteerEventDataAccessService implements VolunteerEventDao
{
    private final JdbcTemplate jdbcTemplate;

    public VolunteerEventDataAccessService(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertVolunteerEvent(VolunteerEvent volunteerEvent)
    {
        String query = QueryHelper.insertQuery("volunteervolunteers",
                Arrays.asList("Username", "Task_ID", "Date"),
                Arrays.asList(volunteerEvent.getUsername(), Integer.toString(volunteerEvent.getTaskID()), volunteerEvent.getDate().toString()));

        String queryDeleteTaskRequest =
                "DELETE FROM TaskRequestPlace WHERE task_id = " + volunteerEvent.getTaskID() + " " +
                        "AND request_id IN " +
                        "(SELECT t.Request_ID FROM TaskRequestPlace t , VolunteerPlaceRequest v " +
                        " WHERE t.request_id = v.request_id AND " +
                        " username ='" + volunteerEvent.getUsername() + "')";
        return jdbcTemplate.update(query) + jdbcTemplate.update(queryDeleteTaskRequest);
    }

    @Override
    public List<VolunteerEvent> selectAllVolunteerEvents() {
        String query =
                "SELECT username, task_id, date FROM VolunteerVolunteers ORDER BY date DESC";
        return jdbcTemplate.query(query, CustomRowMapper::VolunteerEventRowMapper);
    }

    @Override
    public VolunteerEvent selectVolunteerEvent(String username, int task_id) {
        return null;
    }

    @Override
    public int deleteVolunteerEvent(String username, int task_id) {
        String query =
                "DELETE FROM VolunteerVolunteers v WHERE v.username = '" + username + "' " +
                        " AND v.task_id = " + task_id;
        return jdbcTemplate.update(query);
    }

    @Override
    public int updateVolunteerEvent(String username, int task_id, Date date) {
        return 0;
    }

    @Override
    public List<TaskLocation> getAllAcceptedTasks(String username) {
        String query =  "SELECT Task.Task_ID, Task.Date, Description, Num_Volunteer, Task.PostalCode, Status, Address, Task.Username, CreateTime, City, Province " +
                        "FROM PostalCode, Task, VolunteerVolunteers vv " +
                        "WHERE PostalCode.PostalCode = Task.PostalCode AND Task.Task_ID = vv.Task_ID AND vv.username ='" + username + "' " +
                        "ORDER BY Task.Date";
        return jdbcTemplate.query(query, CustomRowMapper::TaskLocationRowMapper);
    }
}
