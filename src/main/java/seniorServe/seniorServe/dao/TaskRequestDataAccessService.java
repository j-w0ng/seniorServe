package seniorServe.seniorServe.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import seniorServe.seniorServe.model.TaskLocationRequest;
import seniorServe.seniorServe.model.TaskRequest;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Repository("taskRequestDaoPostgres")
public class TaskRequestDataAccessService implements TaskRequestDao {

    private final JdbcTemplate jdbcTemplate;
    public TaskRequestDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Insert TaskRequestPlace (Request_ID, Date, Task_ID)
     * then VolunteerPlaceRequest (Request_ID, Username)
     *
     * @param tr
     * @return
     */
    @Override
    public int addTaskRequest(TaskRequest tr) {
        int randomTaskRequestID = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
        String sqlQueryTaskRequest =
                "INSERT INTO TaskRequestPlace (Request_ID, Date, Task_ID) " +
                        "VALUES (" + randomTaskRequestID + ", '" + tr.getDate() + "'," + tr.getTask_ID() + ");";
        String sqlQueryVolunteerReq =
                " INSERT INTO VolunteerPlaceRequest (Username, Request_ID) " +
                        "VALUES ('" + tr.getUsername() + "', " +
                        "(SELECT DISTINCT t.Request_ID FROM TaskRequestPlace t WHERE t.Request_ID = " + randomTaskRequestID + "))";

        return jdbcTemplate.update(sqlQueryTaskRequest) + jdbcTemplate.update(sqlQueryVolunteerReq);
    }

    @Override
    public int deleteTaskRequest(int request_ID) {
        String queryTaskRequestPlaced =
                "DELETE FROM TaskRequestPlace WHERE request_ID = '" + request_ID + "'";
        return jdbcTemplate.update(queryTaskRequestPlaced) ;
    }

    @Override
    public int deleteTaskRequestByTask(int task_ID) {
        String queryTaskRequestDeleted =
                "DELETE FROM TaskRequestPlace WHERE Task_ID = '" + task_ID + "'";
        return jdbcTemplate.update(queryTaskRequestDeleted) ;
    }

    @Override
    public int hasRequestedTask(int task_ID, String username) {
        String sqlQuery =
                "SELECT * FROM VolunteerPlaceRequest v, TaskRequestPlace tr " +
                        "WHERE tr.task_ID = " + task_ID + " AND v.username = '" + username + "' AND " +
                        "v.Request_ID = tr.Request_ID";

        List<TaskRequest> requestedTasks = jdbcTemplate.query(sqlQuery, CustomRowMapper::TaskRequestRowMapper);
        if (requestedTasks.size() > 0)
            return 1;
        else
            return 0;
    }

    @Override
    public List<TaskRequest> getAllTaskRequests() {
        String sqlQuery =
                "SELECT tr.request_id, tr.date, tr.task_id, v.username " +
                        "FROM VolunteerPlaceRequest v, TaskRequestPlace tr " +
                        "WHERE v.Request_ID = tr.Request_ID ;";
        return jdbcTemplate.query(sqlQuery, CustomRowMapper::TaskRequestRowMapper);
    }

    @Override
    public List<TaskRequest> getAllTaskRequestObjByUsername(String username) {
        String sqlQuery =
                "SELECT tr.request_id, tr.date, tr.task_id, v.username " +
                        "FROM VolunteerPlaceRequest v, TaskRequestPlace tr " +
                        "WHERE v.Request_ID = tr.Request_ID AND v.Username = '" + username + "' " +
                        "ORDER BY tr.date DESC;";
        return jdbcTemplate.query(sqlQuery, CustomRowMapper::TaskRequestRowMapper);
    }

    /**
     * Returns the referred Task, Location
     *
     * Also includes RequestDate (The day the task was requested by the someone)
     * @return
     */
    @Override
    public List<TaskLocationRequest> getAllRequestedTasks() {
        String sqlQuery =
                " SELECT t.Task_ID, t.Date, t.Description, t.Num_Volunteer, t.PostalCode, t.Status, t.Address, " +
                        "t.Username, t.CreateTime, City, Province, tr.Date as RequestDate" +
                        " FROM VolunteerPlaceRequest v, TaskRequestPlace tr, Task t, PostalCode pc " +
                        " WHERE v.Request_ID = tr.Request_ID " +
                        " AND pc.PostalCode = t.PostalCode AND tr.task_id = t.task_id";
        return jdbcTemplate.query(sqlQuery, CustomRowMapper::TaskLocationRequestRowMapper);
    }

    /**
     * Returns the referred Task, Location
     *
     * Also includes Most recent RequestDate (The day the task was requested by the given username)
     * @return
     */
    @Override
    public List<TaskLocationRequest> getAllRequestTaskLocationByUsername(String username) {
        String sqlQuery =
                " SELECT t.Task_ID, t.Date, t.Description, t.Num_Volunteer, t.PostalCode, t.Status, t.Address, " +
                        "t.Username, t.CreateTime, City, Province, MAX(tr.Date) as RequestDate " +
                        " FROM VolunteerPlaceRequest v, TaskRequestPlace tr, Task t, PostalCode pc " +
                        " WHERE v.Request_ID= tr.Request_ID AND v.Username = '" + username + "'" +
                        " AND pc.PostalCode = t.PostalCode AND tr.task_id = t.task_id " +
                        " GROUP BY t.Task_ID, t.Date, t.Description, t.Num_Volunteer, t.PostalCode, t.Status, t.Address, " +
                        " t.Username, t.CreateTime, City, Province" +
                        " ORDER BY RequestDate ASC";
        return jdbcTemplate.query(sqlQuery, CustomRowMapper::TaskLocationRequestRowMapper);
    }

}
