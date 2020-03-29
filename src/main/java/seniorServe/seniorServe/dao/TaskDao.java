package seniorServe.seniorServe.dao;

import seniorServe.seniorServe.model.Task;
import seniorServe.seniorServe.model.TaskLocation;

import java.util.List;

public interface TaskDao {

    int insertTask(Task task);

    int deleteTask(int task_ID);

    int updateTask(int task_ID, String updateString);

    List<Task> selectAllTask();

    List<Task> selectTaskByUsername(String username, String order);

    List<TaskLocation> selectTaskByFilter(String filterConditions, String order);

    Task selectTaskByID(int task_ID);
}
