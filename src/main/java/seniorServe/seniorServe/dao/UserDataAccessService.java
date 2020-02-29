package seniorServe.seniorServe.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import seniorServe.seniorServe.model.User;

import java.util.List;
import java.util.Optional;

@Repository("userDaoPostgres")
public class UserDataAccessService implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertUser(String username, User user) {
        User u = new User(username, user.getFirstName(), user.getLastName(), user.getPostalCode(), user.getAddress());
        String sql = "INSERT INTO Users (Username, First_Name, Last_Name, PostalCode, Address) VALUES('"
                + u.getUsername() + "', '" + u.getFirstName() + "', '" + u.getLastName() + "', '" + u.getPostalCode() + "', " +  "'" + u.getAddress() + "'" + ");";
        return jdbcTemplate.update(sql);
    }

    @Override
    public List<User> selectAllUsers() {
        String query = "SELECT * FROM users;";
        return jdbcTemplate.query(query, (resultSet, i) ->
                new User(resultSet.getString("Username"),
                        resultSet.getString("First_Name"),
                        resultSet.getString("Last_Name"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Address")));
    }

    @Override
    public Optional<User> selectUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = '" + username + "';";
        User user = jdbcTemplate.queryForObject(query, new Object[]{}, (resultSet, i) ->
                new User(resultSet.getString("Username"),
                        resultSet.getString("First_Name"),
                        resultSet.getString("Last_Name"),
                        resultSet.getString("PostalCode"),
                        resultSet.getString("Address")));
        return Optional.ofNullable(user);
    }

    @Override
    public int deleteUser(String username) {
        return 0;
    }

    @Override
    public int updateUser(String username, User user) {
        return 0;
    }
}
