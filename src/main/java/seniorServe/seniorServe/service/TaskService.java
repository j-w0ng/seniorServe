package seniorServe.seniorServe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import seniorServe.seniorServe.dao.TaskDao;
import seniorServe.seniorServe.model.Task;
import seniorServe.seniorServe.model.TaskLocation;

import java.util.List;

@Service
public class TaskService {

    private final TaskDao taskDao;

    @Autowired
    public TaskService(@Qualifier("taskPostgres") TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public int addTask(Task task) {
        return taskDao.insertTask(task);
    }

    public List<TaskLocation> getAllTasks() {
        return taskDao.selectAllTask();
    }

    public Task getTaskByID(int task_ID) {
        return taskDao.selectTaskByID(task_ID);
    }

    public List<Task> getTaskByUsername(String username, String order) {
        return taskDao.selectTaskByUsername(username, order);
    }

    public List<TaskLocation> getTaskByFilter(String filterConditions, String order) {
        return taskDao.selectTaskByFilter(filterConditions, order);
    }

    public int updateTask(int task_ID, String taskUpdate) {
        return taskDao.updateTask(task_ID, taskUpdate);
    }

    public int deleteTask(int task_ID) {
        return taskDao.deleteTask(task_ID);
    }
}
