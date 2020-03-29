package seniorServe.seniorServe.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import seniorServe.seniorServe.model.TaskCompletion;
import seniorServe.seniorServe.model.TaskLocation;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Repository("taskcompletionDaoPostgres")
public class TaskCompletionDataAccessService implements TaskCompletionDao {

    private final JdbcTemplate jdbcTemplate;
    public TaskCompletionDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * This needs to have a valid Task_ID and username. Check that they exist. Also update the task.status to complete
     * This will change the completeID to be random (And not in current database - hopefully)
     *
     * Insert into
     * TaskCompletePlaced (Complete_ID, Date, Task_ID, MonetaryAmount) then
     * SeniorPlaceCompleteTask (Username, Complete_ID)
     *
     * Note: There may be an issue with multiple task completion insertions (Edited the table to simplify key to only CompleteID)
     *
     * @param tc
     * @return
     */
    @Override
    public int addTaskCompletion(TaskCompletion tc) {
        int randomTaskCompletionID = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
        String sqlQueryTaskComplete =
                "INSERT INTO TaskCompletePlaced (Complete_ID, Date, Task_ID, MonetaryAmount) " +
                        "VALUES (" + randomTaskCompletionID + ", '" + tc.getDate() + "'," + tc.getTask_ID() +
                        ", " + tc.getMonetaryAmount() + ");";
        String sqlQuerySeniorComplete =
                " INSERT INTO SeniorPlaceCompleteTask (Username, Complete_ID) " +
                        "VALUES ('" + tc.getUsername() + "', " +
                        "(SELECT DISTINCT Complete_ID FROM TaskCompletePlaced WHERE Complete_ID = " + randomTaskCompletionID + "))";

        String updateTaskIDStatus =
                "UPDATE Task SET Status = 'Completed' WHERE Task_ID = " + tc.getTask_ID() + "";
        return jdbcTemplate.update(sqlQueryTaskComplete) + jdbcTemplate.update(updateTaskIDStatus) +
            jdbcTemplate.update(sqlQuerySeniorComplete);
    }

    /**
     * This will on delete cascade with SeniorPlaceCompleteTask
     * @param task_ID
     * @return
     */
    @Override
    public int deleteTaskCompletionByTask(int task_ID) {
        String queryTaskCompletePlaced =
                "DELETE FROM TaskCompletePlaced WHERE Task_ID = '" + task_ID + "'";
        return jdbcTemplate.update(queryTaskCompletePlaced) ;
    }

    /**
     * This will on delete cascade with SeniorPlaceCompleteTask
     * @param complete_ID
     * @return
     */
    @Override
    public int deleteTaskCompletionByCompleteID(int complete_ID) {
        String queryTaskCompletePlaced =
                "DELETE FROM TaskCompletePlaced WHERE Complete_ID = '" + complete_ID + "'";
        return jdbcTemplate.update(queryTaskCompletePlaced) ;
    }

    @Override
    public List<TaskCompletion> getAllTaskCompletion() {
        String sqlQuery =
                "SELECT tc.complete_id, tc.date, tc.task_id, tc.monetaryAmount, s.username " +
                "FROM SeniorPlaceCompleteTask s, TaskCompletePlaced tc " +
                "WHERE s.Complete_ID = tc.Complete_ID ;";
        return jdbcTemplate.query(sqlQuery, CustomRowMapper::TaskCompletionRowMapper);
    }

    @Override
    public List<TaskCompletion> getAllTaskCompletionObjByUsername(String username) {
        String sqlQuery =
                "SELECT tc.complete_id, tc.date, tc.task_id, tc.monetaryAmount, s.username " +
                        "FROM SeniorPlaceCompleteTask s, TaskCompletePlaced tc " +
                        "WHERE s.Complete_ID = tc.Complete_ID AND s.Username = '" + username + "'";
        return jdbcTemplate.query(sqlQuery, CustomRowMapper::TaskCompletionRowMapper);
    }

    @Override
    public List<TaskLocation> getAllCompletedTaskLocationByUsername(String username) {
        String sqlQuery =
                " SELECT t.Task_ID, tc.Date, t.Description, t.Num_Volunteer, t.PostalCode, t.Status, t.Address, " +
                        "s.Username, t.CreateTime, City, Province  " +
                " FROM SeniorPlaceCompleteTask s, TaskCompletePlaced tc, Task t, PostalCode pc " +
                " WHERE s.Complete_ID = tc.Complete_ID AND s.Username = '" + username + "'" +
                " AND pc.PostalCode = t.PostalCode AND tc.task_id = t.task_id";
        return jdbcTemplate.query(sqlQuery, CustomRowMapper::TaskLocationRowMapper);
    }

    @Override
    public List<TaskLocation> getAllCompletedTasks() {
        String sqlQuery =
                " SELECT t.Task_ID, tc.Date, t.Description, t.Num_Volunteer, t.PostalCode, t.Status, t.Address, " +
                        "s.Username, t.CreateTime, City, Province  " +
                        " FROM SeniorPlaceCompleteTask s, TaskCompletePlaced tc, Task t, PostalCode pc " +
                        " WHERE s.Complete_ID = tc.Complete_ID " +
                        " AND pc.PostalCode = t.PostalCode AND tc.task_id = t.task_id";
        return jdbcTemplate.query(sqlQuery, CustomRowMapper::TaskLocationRowMapper);
    }
}
