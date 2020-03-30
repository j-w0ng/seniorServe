package seniorServe.seniorServe.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import seniorServe.seniorServe.model.VolunteerEvent;
import seniorServe.seniorServe.service.VolunteerEventService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/volunteer")
@RestController
public class VolunteerEventController
{
    private final VolunteerEventService volunteerEventService;

    @Autowired
    public VolunteerEventController(VolunteerEventService volunteerEventService)
    {
        this.volunteerEventService = volunteerEventService;
    }

    @PostMapping
    public int addVolunteerEvent(@RequestBody VolunteerEvent volunteerEvent) {
        return volunteerEventService.addVolunteerEvent(volunteerEvent);
    }

    @GetMapping
    public List<VolunteerEvent> getAllVolunteerEvents()
    {
        return volunteerEventService.getAllVolunteerEvents();
    }

    // primaryKey to be delimited by &
    // {username}&{task_ID}
    @GetMapping(path = "{username}&{task_ID}")
    public VolunteerEvent getVolunteerEventByPK(@PathVariable("username") String username, @PathVariable("task_ID") int task_id)
    {
        return volunteerEventService.getVolunteerEventByPK(username, task_id);
    }

    // primaryKey to be delimited by &
    // {username}&{task_ID)
    @DeleteMapping(path = "{username}&{task_ID}")
    public int deleteVolunteerEvent(@PathVariable("username") String username, @PathVariable("task_ID") int task_id)
    {
        return volunteerEventService.deleteVolunteerEvent(username, task_id);
    }
}
