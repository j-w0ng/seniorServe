package seniorServe.seniorServe.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import seniorServe.seniorServe.model.Task;
import seniorServe.seniorServe.service.TaskService;

import java.util.List;

@RequestMapping("api/v1/task")
@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public void addTask(@RequestBody Task task) {
        taskService.addTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping(path = "task_ID={taskID}")
    public Task getTaskByID(@PathVariable("taskID") int task_ID) {
        return taskService.getTaskByID(task_ID);
    }

    @DeleteMapping(path = "{taskID}")
    public int deleteTask(@PathVariable("taskID") int task_ID) {
        return taskService.deleteTask(task_ID);
    }
}
