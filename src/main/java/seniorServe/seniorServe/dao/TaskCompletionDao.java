package seniorServe.seniorServe.dao;

import seniorServe.seniorServe.model.TaskCompletion;
import seniorServe.seniorServe.model.TaskCompletionRecord;
import seniorServe.seniorServe.model.TaskLocation;
import seniorServe.seniorServe.model.TaskLocationCompletion;

import java.util.List;

public interface TaskCompletionDao {
    public int addTaskCompletion(TaskCompletion taskCompletion);

    public int addTaskCompletionRecord(TaskCompletionRecord taskCompletionRecord);

    public int deleteTaskCompletionByTask(int task_ID);

    public int deleteTaskCompletionByCompleteID(int complete_ID);

    public List<TaskCompletion> getAllTaskCompletion();

    public List<TaskCompletion> getAllTaskCompletionObjByUsername(String username);

    public List<TaskLocation> getAllCompletedTasks();

    public List<TaskLocation> getAllCompletedTaskLocationByUsername(String username);

    public List<TaskLocationCompletion> getAllCompletedTaskLocationRequestByUsername(String username);
}
