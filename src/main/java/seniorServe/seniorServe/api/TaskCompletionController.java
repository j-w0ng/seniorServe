package seniorServe.seniorServe.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import seniorServe.seniorServe.model.TaskCompletion;
import seniorServe.seniorServe.model.TaskCompletionRecord;
import seniorServe.seniorServe.model.TaskLocation;
import seniorServe.seniorServe.model.TaskLocationCompletion;
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
     * Basic functionality of adding a single TaskCompletion, and updating the corresponding task_id to have
     * status = "completed"
     * @param taskCompletion
     * @return
     */
    @PostMapping
    public int addTaskCompletion(@Valid @NonNull @RequestBody TaskCompletion taskCompletion) {
        return taskCompletionService.createTaskCompletion(taskCompletion);
    }

    /**
     * This is the full type that adds volunteerTimeRecords with an entry of TaskCompletionRecord
     * Adds a record that the specified task is complete.
     *  Sample TaskCompletionRecord:
     *  {
     *         "complete_ID": 1,
     *         "date": "2020-01-31",
     *         "task_ID": 1,
     *         "monetaryAmount": 0.0,
     *         "username": "OldMac6",
     *         "hours": 2,
     *         "volunteerTime": "13:15:00"
     *     }
     *
     * Additional Steps to Database:
     * 1. Updates the corresponding task_ID to have "status":"Completed"
     * 2. Adds VolunteerTimeRecordEntry for each username who has volunteered for specified task_ID
     *      (Uses the specified hours, volunteerTime to INSERT VolunteerTimeRecordEntry)
     * 3. Removes any TaskRequests that are associated with given task_ID
     *
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

    /**
     * @return List of all completed tasks in the database
     */
    @GetMapping(path = "allTask")
    public List<TaskLocation> getAllCompletedTasks() {
        return taskCompletionService.getAllCompletedTasks();
    }

    /**
     * @param username
     * @return list of completed tasks for given username (Senior)
     *
     * Note: This does not return the associated completion date, only the original Task+Location.
     * For associated completion date, use "api/v1/taskcompletion/allTaskCDate/username={username}"
     */
    @GetMapping(path = "allTask/username={username}")
    public List<TaskLocation> getAllCompletedTaskByUsername(@PathVariable("username") String username) {
        return taskCompletionService.getAllCompletedTasksByUsername(username);
    }

    /**
     * @param username
     * @return Returns the Task Object along with Location and a Completion date for all completed tasks
     *  for given username.
     */
    @GetMapping(path = "allTaskCDate/username={username}")
    public List<TaskLocationCompletion> getAllCompletedTaskLocationRequestByUsername(@PathVariable("username") String username) {
        return taskCompletionService.getAllCompletedTaskLocationRequestByUsername(username);
    }
}
