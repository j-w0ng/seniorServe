package seniorServe.seniorServe.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import seniorServe.seniorServe.model.Task;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository("taskPostgres")
public class TaskDataAccessService implements TaskDao {
    private final JdbcTemplate jdbcTemplate;
    private static final List<String> taskAttributes = Arrays.asList("Task_ID", "Date", "Description",
            "Num_Volunteer", "Status", "PostalCode", "Address", "Username", "CreateTime");

    public TaskDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    // Note: May need to handle DataAccessException. update() returns number of rows affected.
    public int insertTask(Task task) {
        String updateQuery = QueryHelper.insertQuery("Task", taskAttributes, getTaskValues(task));
        return jdbcTemplate.update(updateQuery);
    }

    @Override
    public int deleteTask(int task_ID) {
        String query = "DELETE FROM Task WHERE Task_ID = '" + task_ID + "';";
        return jdbcTemplate.update(query);
    }

    /** TODO: Should we only allow update one field per task, or can update everything all at once (Not touching
     *   the fields that are not included)
     */
    @Override
    public int updateTask(int task_ID, Date date, String description, int num_Volunteer, String status,
                          String postalCode, String address, String username, Date createTime) {
        return 0;
    }

    @Override
    public List<Task> selectAllTask() {
        String sqlQuery = "SELECT * FROM Task;";
        return jdbcTemplate.query(sqlQuery, (resultSet, i) ->
                new Task(resultSet.getInt("Task_ID"),
                        resultSet.getDate("Date"),
                        resultSet.getString("Description"),
                        resultSet.getInt("Num_Volunteer"),
                        resultSet.getString("Status"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Address"),
                        resultSet.getString("Username"),
                        resultSet.getDate("CreateTime")));
    }

    @Override
    public Optional<Task> selectTaskByID(int task_ID) {
        String sqlQuery = "SELECT * FROM Task WHERE Task_ID = " + task_ID + ";";
        Task task = jdbcTemplate.queryForObject(sqlQuery, new Object[]{}, (resultSet, i) ->
                new Task(resultSet.getInt("Task_ID"),
                        resultSet.getDate("Date"),
                        resultSet.getString("Description"),
                        resultSet.getInt("Num_Volunteer"),
                        resultSet.getString("Status"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Address"),
                        resultSet.getString("Username"),
                        resultSet.getDate("CreateTime")));
        return Optional.ofNullable(task);
    }

    private List<String> getTaskValues(Task task) {
        String task_ID = Integer.toString(task.getTask_ID());
        String date = task.getDate().toString();
        String description = task.getDescription();
        String num_Volunteer = Integer.toString(task.getNum_Volunteer());
        String status = task.getStatus();
        String postalCode = task.getPostalCode();
        String address = task.getAddress();
        String username = task.getUsername();
        String createTime = task.getCreateTime().toString();

        return Arrays.asList(task_ID, date, description, num_Volunteer, status, postalCode, address, username, createTime);
    }
}
