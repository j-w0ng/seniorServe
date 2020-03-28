package seniorServe.seniorServe.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import seniorServe.seniorServe.model.Task;
import seniorServe.seniorServe.service.TaskService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/task")
@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public void addTask(@Valid @NotNull @RequestBody Task task) {
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

    @GetMapping(path = "username={username}")
    public List<Task> getTaskByUsernameNoOrder(@PathVariable("username") String username) {
        return taskService.getTaskByUsername(username, "");
    }

    @GetMapping(path = "username={username}/{orderConditions}")
    public List<Task> getTaskByUsernameNoOrder(@PathVariable("username") String username,
                                               @PathVariable("orderConditions") String orders) {
        return taskService.getTaskByUsername(username, orders);
    }

    @GetMapping(path = "filter/{filterConditions}")
    public List<Task> getTaskByFilter(@PathVariable("filterConditions") String filterConditions) {
        return taskService.getTaskByFilter(filterConditions, "");
    }

    @GetMapping(path = "filter/{filterConditions}/{orderConditions}")
    public List<Task> getTaskByFilter(@PathVariable("filterConditions") String filterConditions,
                                      @PathVariable("orderConditions") String orderConditions) {
        return taskService.getTaskByFilter(filterConditions, orderConditions);
    }

    @PutMapping(path = "task_ID={taskID}/{updates}")
    public int updateTask(@PathVariable("taskID") int task_ID, @PathVariable("updates") String taskUpdates) {
        return taskService.updateTask(task_ID, taskUpdates);
    }
    /**
     * Can only delete a task using associated TaskID
     * @param task_ID
     * @return number of lines modified (1 = deleted, 0 = nothing deleted)
     */
    @DeleteMapping(path = "task_ID={taskID}")
    public int deleteTask(@PathVariable("taskID") int task_ID) {
        return taskService.deleteTask(task_ID);
    }
}
