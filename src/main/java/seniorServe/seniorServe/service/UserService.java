package seniorServe.seniorServe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import seniorServe.seniorServe.dao.UserDao;
import seniorServe.seniorServe.model.User;

import java.util.List;

@Service
public class UserService
{
    private final UserDao userDao;

    @Autowired //Qualifier corresponds to implementation inside Repository with same String
    public UserService(@Qualifier("userDaoPostgres") UserDao userDao)
    {
        this.userDao = userDao;
    }

    public int addUser(User user)
    {
        return userDao.insertUser(user.getUsername(), user);
    }

    public List<User> getAllUsers()
    {
        return userDao.selectAllUsers();
    }

    public User getUserByUsername(String username)
    {
        return userDao.selectUserByUsername(username);
    }

    public int deleteUser(String username)
    {
        return userDao.deleteUser(username);
    }

    public int updateUser(String username, User user) {
        return userDao.updateUser(username, user);
    }

    public int insertSenior(User user) {
        return userDao.insertSenior(user);
    }

    public int deleteSenior(String username) {
        return userDao.deleteSenior(username);
    }

    public int isSenior(String username) {
        return userDao.isSenior(username);
    }

    public int insertVolunteer(User user) {
        return userDao.insertVolunteer(user);
    }

    public int deleteVolunteer(String username) {
        return userDao.deleteVolunteer(username);
    }

    public int isVolunteer(String username) {
        return userDao.isVolunteer(username);
    }

    public List<User> selectSenior() {
        return userDao.selectSenior();
    }

    public List<User> selectVolunteer() {
        return userDao.selectVolunteer();
    }

    public List<User> getVolunteerVolunteeredForAllSeniors() {
        return userDao.getVolunteersThatVolunteeredForAllSeniors();
    }
}
