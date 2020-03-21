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
    public void addVolunteerEvent(@RequestBody VolunteerEvent volunteerEvent)
    {
        volunteerEventService.addVolunteerEvent(volunteerEvent);
    }

    @GetMapping
    public List<VolunteerEvent> getAllVolunteerEvents()
    {
        return volunteerEventService.getAllVolunteerEvents();
    }

    // primaryKey to be delimited by &
    // {username}&{task_ID)
    @GetMapping(path = "{primaryKey}")
    public VolunteerEvent getVolunteerEventByPK(@PathVariable("primaryKey") String primaryKey)
    {
        return volunteerEventService.getVolunteerEventByPK(primaryKey);
    }

    // primaryKey to be delimited by &
    // {username}&{task_ID)
    @DeleteMapping
    public int deleteVolunteerEvent(@PathVariable("primaryKey") String primaryKey)
    {
        return volunteerEventService.deleteVolunteerEvent(primaryKey);
    }
}
