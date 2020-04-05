package seniorServe.seniorServe.dao;

import seniorServe.seniorServe.model.User;
import seniorServe.seniorServe.model.UserJoin;
import seniorServe.seniorServe.model.UserWithLocation;

import java.util.List;

public interface UserDao
{

    int insertUser(String username, User user);

    List<User> selectAllUsers();

    UserWithLocation selectUserByUsername(String username);

    int deleteUser(String username);

    int updateUser(String username, User user);

    int insertSenior(User user);

    int deleteSenior(String username);

    int insertVolunteer(User user);

    int deleteVolunteer(String username);

    int isSenior(String username);

    int isVolunteer(String username);

    List<User> selectSenior();

    List<String> selectSeniorUsername();

    List<User> selectVolunteer();

    List<User> getVolunteersThatVolunteeredForAllSeniors();

    List<User> getVolunteersThatHaveVolunteeredForAllPreferences();

    List<User> getVolunteersThatHaveBeenReviewedForAllTheirCompletedTasks();

    List<UserJoin> getUserJoins(String conditionAttribute, String symbol);
}
