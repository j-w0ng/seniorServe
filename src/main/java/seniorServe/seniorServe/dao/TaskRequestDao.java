package seniorServe.seniorServe.dao;

import seniorServe.seniorServe.model.TaskLocationRequest;
import seniorServe.seniorServe.model.TaskRequest;
import seniorServe.seniorServe.model.UserRatingHoursDate;

import java.util.List;

public interface TaskRequestDao {
    public int addTaskRequest(TaskRequest taskRequest);

    public int deleteTaskRequest(int request_ID);

    public int deleteTaskRequestByTask(int task_ID);

    public int hasRequestedTask(int task_ID, String username);

    public List<TaskRequest> getAllTaskRequests();

    public List<TaskRequest> getAllTaskRequestObjByUsername(String username);

    public List<UserRatingHoursDate> getAllTaskRequestObjBySeniorTaskID(int task_id);

    public List<TaskLocationRequest> getAllRequestedTasks();

    public List<TaskLocationRequest> getAllRequestTaskLocationByUsername(String username);

}
