package seniorServe.seniorServe.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import seniorServe.seniorServe.model.UserRatingHours;
import seniorServe.seniorServe.model.VolunteerRecordString;
import seniorServe.seniorServe.model.VolunteerTimeEntryRecord;
import seniorServe.seniorServe.model.VolunteerTimeEntryRecordTaskInfo;
import seniorServe.seniorServe.service.VolunteerRecordService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/volunteerRecord")
@RestController
public class VolunteerRecordController {
    private final VolunteerRecordService volunteerRecordService;

    @Autowired

    public VolunteerRecordController(VolunteerRecordService volunteerRecordService) {
        this.volunteerRecordService = volunteerRecordService;
    }

    @PostMapping
    public int addVolunteerRecord(@Valid @NonNull @RequestBody VolunteerTimeEntryRecord record) {
        return volunteerRecordService.createVolunteerRecord(record);
    }

    @DeleteMapping
    public int deleteVolunteerRecord(@Valid @NonNull @RequestBody VolunteerTimeEntryRecord record) {
        return volunteerRecordService.removeVolunteerRecord(record);
    }

    @DeleteMapping(path = "/username={user}")
    public int deleteVolunteerRecordByUser(@PathVariable("user") String username) {
        return volunteerRecordService.removeVolunteerRecordByUser(username);
    }

    @PutMapping
    public int updateVolunteerRecord(@Valid @NonNull @RequestBody VolunteerTimeEntryRecord record) {
        return volunteerRecordService.updateVolunteerRecord(record);
    }

    @GetMapping
    public List<VolunteerTimeEntryRecord> getAllVolunteerRecords() {
        return volunteerRecordService.getAllVolunteerRecord();
    }

    /**
     *
     * @param username
     * @return the total number of hours volunteered by given username. If no records exist, return 0
     */
    @GetMapping(path = "/totalHours/username={user}")
    public int getTotalHoursByUser(@PathVariable("user") String username) {
        return volunteerRecordService.getTotalHoursByUsername(username);
    }

    /**
     *
     * @param username
     * @return Gets all the volunteer records for the given username (Sorted by date descending, timeOfDay desc)
     */
    @GetMapping(path = "/records/username={user}")
    public List<VolunteerTimeEntryRecordTaskInfo> getAllVolunteerRecordsByUser(@PathVariable("user") String username) {
        return volunteerRecordService.getAllVolunteerRecordByUser(username);
    }

    /**
     * Projection requirement, returns list of strings chosen to be projected
     */
    @GetMapping(path = "/records/username={user}/projection={projection}")
    public List<String> getAllProjectedVolunteerRecordsByUser(@PathVariable("user") String username,
                                                              @PathVariable("projection") String projectionProp)
    {
        return volunteerRecordService.getAllProjectedVolunteerRecordsByUser(username, projectionProp);
    }

    /**
     * @return A list of {Username, Rating, Hours} for each user (Limit 5).
     * If there are no ratings, Rating = -1
     * If there are no volunteerRecords, Hours = 0
     */
    @GetMapping(path = "/allUserRatingHours")
    public List<UserRatingHours> getRatingHoursForAllUsers() {
        return volunteerRecordService.getRatingHoursForAllUsers();
    }

    /**
     *
     * @param username - username for the tasks
     * @param projections - Should be separated by |
     *                    and be one of the following
     *                    ["record_ID", "date", "timeOfDay", "hours", "username","task_id", "seniorUsername", "description"]
     *
     * @return Returns all values as strings. Any value not specified in projection has a null value.
     *      e.g. localhost:8080/api/v1/volunteerRecord/records/username=Ann34/record_ID|date|hours
     *      {
     *         "record_ID": "123",
     *         "date": "2020-01-31",
     *         "timeOfDay": null,
     *         "hours": "3",
     *         "username": null,
     *         "task_id": null,
     *         "seniorUsername": null,
     *         "taskDescription": null
     *     }
     *
     */
    @GetMapping(path = "/records/username={username}/{projections}")
    public List<VolunteerRecordString> getProjections(@PathVariable("username") String username,
                                                      @PathVariable("projections") String projections) {
        return volunteerRecordService.getProjectionRecord(username, projections);
    }
}
