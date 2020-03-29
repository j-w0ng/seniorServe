package seniorServe.seniorServe.dao;

import seniorServe.seniorServe.model.TaskCompletion;
import seniorServe.seniorServe.model.TaskLocation;

import java.util.List;

public interface TaskCompletionDao {
    public int addTaskCompletion(TaskCompletion taskCompletion);

    public int deleteTaskCompletionByTask(int task_ID);

    public int deleteTaskCompletionByCompleteID(int complete_ID);

    public List<TaskCompletion> getAllTaskCompletion();

    public List<TaskCompletion> getAllTaskCompletionObjByUsername(String username);

    public List<TaskLocation> getAllCompletedTasks();

    public List<TaskLocation> getAllCompletedTaskLocationByUsername(String username);
}
