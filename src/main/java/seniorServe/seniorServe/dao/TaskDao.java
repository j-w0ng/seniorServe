package seniorServe.seniorServe.dao;

import seniorServe.seniorServe.model.Task;
import seniorServe.seniorServe.model.TaskLocation;

import java.util.List;

public interface TaskDao {

    int insertTask(Task task);

    int deleteTask(int task_ID);

    int updateTask(int task_ID, String updateString);

    int updateTaskBody(Task task);

    List<TaskLocation> selectAllTask();

    List<TaskLocation> selectTaskByUsername(String username, String order);

    List<TaskLocation> selectTaskByUsernameNoCompletedTask(String username);

    List<Task> selectTaskByUsernameOrdered(String username, String order);

    List<TaskLocation> selectTaskByFilter(String filterConditions, String order);

    Task selectTaskByID(int task_ID);
}
