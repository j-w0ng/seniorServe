package seniorServe.seniorServe.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import seniorServe.seniorServe.model.TaskLocationRequest;
import seniorServe.seniorServe.model.TaskRequest;
import seniorServe.seniorServe.model.UserRatingHoursDate;
import seniorServe.seniorServe.service.TaskRequestService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/taskrequest")
@RestController
public class TaskRequestController {
    private final TaskRequestService taskRequestService;

    @Autowired
    public TaskRequestController(TaskRequestService taskRequestService) {
        this.taskRequestService = taskRequestService;
    }

    @PostMapping
    public int addTaskRequest(@Valid @NonNull @RequestBody TaskRequest taskRequest) {
        return taskRequestService.createTaskRequest(taskRequest);
    }

    @DeleteMapping(path = "task_id={task_id}")
    public int deleteTaskRequestByTask(@PathVariable("task_id") int task_id) {
        return taskRequestService.deleteTaskRequestByTaskID(task_id);
    }

    @DeleteMapping(path = "request_id={request_id}")
    public int deleteTaskRequestByRequestID(@PathVariable("request_id") int request_id) {
        return taskRequestService.deleteTaskRequest(request_id);
    }

    @GetMapping(path = "allTaskRequest")
    public List<TaskRequest> getAllTaskRequest() {
        return taskRequestService.getAllTaskRequest();
    }

    @GetMapping(path = "allTaskRequest/username={username}")
    public List<TaskRequest> getAllTaskRequestObjByUsername(@PathVariable("username") String username) {
        return taskRequestService.getAllTaskRequestObjByUsername(username);
    }

    /**
     * @param task_id
     * @return the users, rating, and hours that requested to volunteer for given task_id
     * If rating = -1, There are no ratings for that user
     * If totalHours = 0, There are no volunteer records for that user
     */
    @GetMapping(path = "allRequestUser/task_id={id}")
    public List<UserRatingHoursDate> getAllUserRatingsRequestSeniorTaskID(@PathVariable("id") int task_id) {
        return taskRequestService.getAllUserRatingsForRequestsOfSeniorTask(task_id);
    }

    /**
     * @return Returns a list of unique Task objects that been requested to volunteer for
     */
    @GetMapping(path = "allTask")
    public List<TaskLocationRequest> getAllRequestedTask() {
        return taskRequestService.getAllRequestedTasks();
    }

    /**
     *
     * @param username
     * @return  Returns a list of all Task objects where {username} has requested to volunteer for.
     */
    @GetMapping(path = "allTask/username={username}")
    public List<TaskLocationRequest> getAllRequestedTasksByUsername(@PathVariable("username") String username) {
        return taskRequestService.getAllRequestedTasksByUsername(username);
    }
}
