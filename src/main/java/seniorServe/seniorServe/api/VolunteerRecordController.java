package seniorServe.seniorServe.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import seniorServe.seniorServe.model.UserRatingHours;
import seniorServe.seniorServe.model.VolunteerTimeEntryRecord;
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

    @GetMapping(path = "/totalHours/username={user}")
    public int getTotalHoursByUser(@PathVariable("user") String username) {
        return volunteerRecordService.getTotalHoursByUsername(username);
    }

    @GetMapping(path = "/records/username={user}")
    public List<VolunteerTimeEntryRecord> getAllVolunteerRecordsByUser(@PathVariable("user") String username) {
        return volunteerRecordService.getAllVolunteerRecordByUser(username);
    }

    @GetMapping(path = "/allUserRatingHours")
    public List<UserRatingHours> getRatingHoursForAllUsers() {
        return volunteerRecordService.getRatingHoursForAllUsers();
    }
}
