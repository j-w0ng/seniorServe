package seniorServe.seniorServe.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import seniorServe.seniorServe.model.TaskCompletion;
import seniorServe.seniorServe.model.TaskCompletionRecord;
import seniorServe.seniorServe.model.TaskLocation;
import seniorServe.seniorServe.service.TaskCompletionService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/taskcompletion")
@RestController
public class TaskCompletionController {
    private final TaskCompletionService taskCompletionService;

    @Autowired
    public TaskCompletionController(TaskCompletionService taskCompletionService) {
        this.taskCompletionService = taskCompletionService;
    }

    /**
     * Basic functionality of adding a single TaskCompletion
     * @param taskCompletion
     * @return
     */
    @PostMapping
    public int addTaskCompletion(@Valid @NonNull @RequestBody TaskCompletion taskCompletion) {
        return taskCompletionService.createTaskCompletion(taskCompletion);
    }

    /**
     * This is the full type that adds volunteerTimeRecords with an entry of TaskCompletion
     * @param tcr
     * @return
     */
    @PostMapping(path = "taskCompletionRecord")
    public int addTaskCompletionRecord(@Valid @NonNull @RequestBody TaskCompletionRecord tcr) {
        return taskCompletionService.createTaskCompletionRecord(tcr);
    }

    @DeleteMapping(path = "task_id={task_id}")
    public int deleteTaskCompletionByTask(@PathVariable("task_id") int task_id) {
        return taskCompletionService.deleteTaskCompletionByTaskID(task_id);
    }

    @DeleteMapping(path = "complete_id={complete_id}")
    public int deleteTaskCompletionByCompleteID(@PathVariable("complete_id") int complete_id) {
        return taskCompletionService.deleteTaskCompletionByCompleteID(complete_id);
    }

    @GetMapping(path = "allTaskCompletion")
    public List<TaskCompletion> getAllTaskCompletion() {
        return taskCompletionService.getAllTaskCompletion();
    }

    @GetMapping(path = "allTaskCompletion/username={username}")
    public List<TaskCompletion> getAllTaskCompletionObjByUsername(@PathVariable("username") String username) {
        return taskCompletionService.getAllTaskCompletionObjByUsername(username);
    }

    @GetMapping(path = "allTask")
    public List<TaskLocation> getAllCompletedTaskByUsername() {
        return taskCompletionService.getAllCompletedTasks();
    }

    @GetMapping(path = "allTask/username={username}")
    public List<TaskLocation> getAllCompletedTaskByUsername(@PathVariable("username") String username) {
        return taskCompletionService.getAllCompletedTasksByUsername(username);
    }
}
