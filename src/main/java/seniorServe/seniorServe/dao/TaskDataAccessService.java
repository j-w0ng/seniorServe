package seniorServe.seniorServe.dao;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import seniorServe.seniorServe.model.Task;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

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
        return jdbcTemplate.query(sqlQuery, CustomRowMapper::TaskRowMapper);
    }

    @Override
    public Task selectTaskByID(int task_ID) {
        try {
            String sqlQuery = "SELECT * FROM Task WHERE Task_ID = " + task_ID + ";";
            return jdbcTemplate.queryForObject(sqlQuery, new Object[]{}, CustomRowMapper::TaskRowMapper);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
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
