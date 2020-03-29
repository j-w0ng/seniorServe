package seniorServe.seniorServe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import seniorServe.seniorServe.dao.TaskRequestDao;
import seniorServe.seniorServe.model.TaskLocationRequest;
import seniorServe.seniorServe.model.TaskRequest;

import java.util.List;

@Service
public class TaskRequestService {
    private final TaskRequestDao taskRequestDao;

    @Autowired
    public TaskRequestService(@Qualifier("taskRequestDaoPostgres") TaskRequestDao taskRequestDao) {
        this.taskRequestDao = taskRequestDao;
    }

    public int createTaskRequest(TaskRequest taskRequest) {
        return taskRequestDao.addTaskRequest(taskRequest);
    }

    public int deleteTaskRequestByTaskID(int task_ID) {
        return taskRequestDao.deleteTaskRequestByTask(task_ID);
    }

    public int deleteTaskRequest(int request_ID) {
        return taskRequestDao.deleteTaskRequest(request_ID);
    }

    public int hasRequestedTask(int task_ID, String username) {
        return taskRequestDao.hasRequestedTask(task_ID, username);
    }

    public List<TaskRequest> getAllTaskRequest() {
        return taskRequestDao.getAllTaskRequests();
    }

    public List<TaskLocationRequest> getAllRequestedTasksByUsername(String username) {
        return taskRequestDao.getAllRequestTaskLocationByUsername(username);
    }

    public List<TaskLocationRequest> getAllRequestedTasks() {
        return taskRequestDao.getAllRequestedTasks();
    }

    public List<TaskRequest> getAllTaskRequestObjByUsername(String username) {
        return taskRequestDao.getAllTaskRequestObjByUsername(username);
    }
}
