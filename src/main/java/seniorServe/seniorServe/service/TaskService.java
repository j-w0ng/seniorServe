package seniorServe.seniorServe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import seniorServe.seniorServe.dao.TaskDao;
import seniorServe.seniorServe.model.Task;

import java.util.List;
import java.util.Optional;

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

    public List<Task> getAllTasks() {
        return taskDao.selectAllTask();
    }

    public Optional<Task> getTaskByID(int task_ID) {
        return taskDao.selectTaskByID(task_ID);
    }

    public int deleteTask(int task_ID) {
        return taskDao.deleteTask(task_ID);
    }
}
