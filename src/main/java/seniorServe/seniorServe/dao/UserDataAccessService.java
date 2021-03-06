package seniorServe.seniorServe.dao;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import seniorServe.seniorServe.model.User;
import seniorServe.seniorServe.model.UserJoin;
import seniorServe.seniorServe.model.UserWithLocation;

import java.util.Arrays;
import java.util.List;

@Repository("userDaoPostgres")
public class UserDataAccessService implements UserDao
{
    private final JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertUser(String username, User user)
    {
        String query = QueryHelper.insertQuery("users",
                Arrays.asList("Username", "First_Name", "Last_Name", "PostalCode", "Address"),
                Arrays.asList(user.getUsername(), user.getFirstName(), user.getLastName(), user.getPostalCode(), user.getAddress()));
        return jdbcTemplate.update(query);
    }

    @Override
    public List<User> selectAllUsers()
    {
        String query = "SELECT * FROM users;";
        return jdbcTemplate.query(query, (resultSet, i) ->
                new User(resultSet.getString("Username"),
                        resultSet.getString("First_Name"),
                        resultSet.getString("Last_Name"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Address")));
    }

    @Override
    public UserWithLocation selectUserByUsername(String username)
    {
        String query =  "SELECT * " +
                        "FROM users u, postalcode pc " +
                        "WHERE username = '" + username + "' AND u.postalcode = pc.postalcode;";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{}, (resultSet, i) ->
                    new UserWithLocation(resultSet.getString("Username"),
                            resultSet.getString("First_Name"),
                            resultSet.getString("Last_Name"),
                            resultSet.getString("PostalCode"),
                            resultSet.getString("Address"),
                            resultSet.getString("Province"),
                            resultSet.getString("City")));
        }
        catch (EmptyResultDataAccessException e)
        {
            return null;
        }
    }

    @Override
    public int deleteUser(String username)
    {
        String query = "DELETE FROM users WHERE username = '" + username + "';";
        return jdbcTemplate.update(query);
    }

    @Override
    public int updateUser(String username, User user)
    {
        String query = QueryHelper.updateQuery("users",
                Arrays.asList("First_Name", "Last_Name", "PostalCode", "Address"),
                Arrays.asList(user.getFirstName(), user.getLastName(), user.getPostalCode(), user.getAddress()),
                "username = '" + username + "'");
        return jdbcTemplate.update(query);
    }

    /**
     * Inserts into user then into Senior. Do not use insertUser and insertSenior together!
     * @param user
     * @return
     */
    @Override
    public int insertSenior(User user) {
        insertUser(user.getUsername(), user);
        String query = QueryHelper.insertQuery("Senior", Arrays.asList("Username"), Arrays.asList(user.getUsername()));
        return jdbcTemplate.update(query);
    }

    /**
     * This deletes the username from Senior and User. If username is not found in Senior, don't delete from User
     * @param username
     * @return
     */
    @Override
    public int deleteSenior(String username) {
        String query = "DELETE FROM Senior WHERE username = '" + username + "';";
        if (jdbcTemplate.update(query) == 1) {
            return deleteUser(username);
        }
        return 0;
    }

    @Override
    public int insertVolunteer(User user) {
        insertUser(user.getUsername(), user);
        String query = QueryHelper.insertQuery("Volunteer", Arrays.asList("Username"), Arrays.asList(user.getUsername()));
        return jdbcTemplate.update(query);
    }

    @Override
    public int deleteVolunteer(String username) {
        String query = "DELETE FROM Volunteer WHERE username = '" + username + "';";
        if (jdbcTemplate.update(query) == 1) {
            return deleteUser(username);
        }
        return 0;
    }

    /**
     * @param username
     * @return  If 0 - Is not a senior. If > 0 - Is a senior
     */
    @Override
    public int isSenior(String username) {
        String query =
                "SELECT * FROM Senior WHERE username = '" + username + "';";
        List<String> result = jdbcTemplate.query(query, CustomRowMapper::SeniorVolunteerRowMapper);
        return result.size();
    }

    /**
     * @param username
     * @return  If 0 - Is not a Volunteer. If > 0 - Is a Volunteer
     */
    @Override
    public int isVolunteer(String username) {
        String query =
                "SELECT * FROM Volunteer WHERE username = '" + username + "';";
        List<String> result = jdbcTemplate.query(query, CustomRowMapper::SeniorVolunteerRowMapper);
        return result.size();
    }

    @Override
    public List<User> selectSenior() {
        String query =
                "SELECT * FROM Senior, Users WHERE Senior.username = Users.username";
        return jdbcTemplate.query(query, CustomRowMapper::UserRowMapper);
    }

    @Override
    public List<String> selectSeniorUsername() {
        String query = "SELECT username FROM Senior";
        return jdbcTemplate.query(query, (resultSet, i) ->
                resultSet.getString("username"));
    }

    @Override
    public List<User> selectVolunteer() {
        String query =
                "SELECT * FROM Volunteer, Users u WHERE Volunteer.username = u.username";
        return jdbcTemplate.query(query, CustomRowMapper::UserRowMapper);
    }

    /**
     * This is the division example.
     * @return All volunteers than have volunteered for all seniors
     */
    @Override
    public List<User> getVolunteersThatVolunteeredForAllSeniors() {
        String query =
                "SELECT u.username, u.first_name, u.last_name, u.postalcode, u.address FROM Volunteer v, Users u " +
                " WHERE v.username = u.username AND NOT EXISTS" +
                " ((SELECT username FROM Senior) " +
                " EXCEPT " +
                " (SELECT t.username FROM VolunteerVolunteers vv, Task t " +
                " WHERE vv.task_id = t.task_id AND vv.username = v.username))";
        return jdbcTemplate.query(query, CustomRowMapper::UserRowMapper);
    }

    /**
     * Another division - Get the volunteers that have volunteered for tasks that cover ALL preferences in database
     * @return
     */
    @Override
    public List<User> getVolunteersThatHaveVolunteeredForAllPreferences() {
        String query =
                "SELECT u.username, u.first_name, u.last_name, u.postalcode, u.address FROM Volunteer v, Users u " +
                        " WHERE v.username = u.username AND NOT EXISTS" +
                        " ((SELECT Pref_ID FROM Preference) " +
                        " EXCEPT " +
                        " (SELECT DISTINCT tt.Pref_ID FROM VolunteerVolunteers vv, Task t, TasksHasPreference tt " +
                        " WHERE vv.task_id = t.task_id AND vv.username = v.username AND tt.task_id = t.task_id))";
        return jdbcTemplate.query(query, CustomRowMapper::UserRowMapper);
    }

    /**
     * Another division - Get the volunteers that have reviews on all the completed tasks they have volunteered for.
     * (Note: This does not count tasks that have not been completed, but accepted volunteer request)
     * @return
     */
    @Override
    public List<User> getVolunteersThatHaveBeenReviewedForAllTheirCompletedTasks() {
        String query =
                "SELECT u.username, u.first_name, u.last_name, u.postalcode, u.address FROM Volunteer v, Users u " +
                        " WHERE v.username = u.username AND NOT EXISTS" +
                        " ((SELECT DISTINCT vv.task_id FROM VolunteerVolunteers vv, TaskCompletePlaced tc " +
                        " WHERE vv.username = v.username AND tc.task_id = vv.task_id ) " +
                        " EXCEPT " +
                        " (SELECT DISTINCT mr.task_id FROM MakeReview mr " +
                        " WHERE v.username = mr.vusername))";
        return jdbcTemplate.query(query, CustomRowMapper::UserRowMapper);
    }

    @Override
    public List<UserJoin> getUserJoins(String conditionAttribute, String symbol) {
        String condition1 = " u1." + conditionAttribute + " ";
        String condition2 = " u2." + conditionAttribute + " ";
        String query =
                "SELECT u1.username as username1, " + condition1 + " as attribute1 , u2.username as username2, "
                        + condition2 + " as attribute2 " +
                        " FROM UserFull u1 JOIN UserFull u2 ON ("+ condition1 + symbol + condition2 + ")" +
                        " ORDER BY u1.username, "+ condition2;
        return jdbcTemplate.query(query, CustomRowMapper::UserJoinRowMapper);
    }
}
