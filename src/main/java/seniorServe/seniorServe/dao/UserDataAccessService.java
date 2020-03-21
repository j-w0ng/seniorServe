package seniorServe.seniorServe.dao;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import seniorServe.seniorServe.model.User;

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
    public User selectUserByUsername(String username)
    {
        String query = "SELECT * FROM users WHERE username = '" + username + "';";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{}, (resultSet, i) ->
                    new User(resultSet.getString("Username"),
                            resultSet.getString("First_Name"),
                            resultSet.getString("Last_Name"),
                            resultSet.getString("PostalCode"),
                            resultSet.getString("Address")));
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
}
