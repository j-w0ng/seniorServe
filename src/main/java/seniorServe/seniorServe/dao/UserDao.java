package seniorServe.seniorServe.dao;

import seniorServe.seniorServe.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao
{

    int insertUser(String username, User user);

    List<User> selectAllUsers();

    Optional<User> selectUserByUsername(String username);

    int deleteUser(String username);

    int updateUser(String username, User user);
}
