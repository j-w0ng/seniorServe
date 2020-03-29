package seniorServe.seniorServe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import seniorServe.seniorServe.dao.TaskCompletionDao;
import seniorServe.seniorServe.model.TaskCompletion;
import seniorServe.seniorServe.model.TaskLocation;

import java.util.List;

@Service
public class TaskCompletionService {
    private final TaskCompletionDao taskCompletionDao;

    @Autowired
    public TaskCompletionService(@Qualifier("taskcompletionDaoPostgres") TaskCompletionDao taskCompletionDao) {
        this.taskCompletionDao = taskCompletionDao;
    }

    public int createTaskCompletion(TaskCompletion taskCompletion) {
        return taskCompletionDao.addTaskCompletion(taskCompletion);
    }

    public int deleteTaskCompletionByTaskID(int task_ID) {
        return taskCompletionDao.deleteTaskCompletionByTask(task_ID);
    }

    public int deleteTaskCompletionByCompleteID(int complete_ID) {
        return taskCompletionDao.deleteTaskCompletionByCompleteID(complete_ID);
    }


    public List<TaskCompletion> getAllTaskCompletion() {
        return taskCompletionDao.getAllTaskCompletion();
    }

    public List<TaskLocation> getAllCompletedTasksByUsername(String username) {
        return taskCompletionDao.getAllCompletedTaskLocationByUsername(username);
    }

    public List<TaskLocation> getAllCompletedTasks() {
        return taskCompletionDao.getAllCompletedTasks();
    }

    public List<TaskCompletion> getAllTaskCompletionObjByUsername(String username) {
        return taskCompletionDao.getAllTaskCompletionObjByUsername(username);
    }
}
