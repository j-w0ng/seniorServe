package seniorServe.seniorServe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import seniorServe.seniorServe.dao.VolunteerRecordDao;
import seniorServe.seniorServe.model.UserRatingHours;
import seniorServe.seniorServe.model.VolunteerTimeEntryRecord;
import seniorServe.seniorServe.model.VolunteerTimeEntryRecordTaskInfo;

import java.util.List;

@Service
public class VolunteerRecordService {

    private final VolunteerRecordDao volunteerRecordDao;

    @Autowired
    public VolunteerRecordService(@Qualifier("volunteerRecordPostgres") VolunteerRecordDao volunteerRecordDao) {
        this.volunteerRecordDao = volunteerRecordDao;
    }

    public int createVolunteerRecord(VolunteerTimeEntryRecord record) {
        return volunteerRecordDao.addVolunteerRecord(record);
    }

    public int removeVolunteerRecord(VolunteerTimeEntryRecord record) {
        return volunteerRecordDao.deleteVolunteerRecord(record);
    }

    public int removeVolunteerRecordByUser(String username) {
        return volunteerRecordDao.deleteVolunteerRecordByUser(username);
    }

    public int updateVolunteerRecord(VolunteerTimeEntryRecord record) {
        return volunteerRecordDao.updateVolunteerRecord(record);
    }

    public int getTotalHoursByUsername(String username) {
        return volunteerRecordDao.getSumHoursByUser(username);
    }

    public List<VolunteerTimeEntryRecordTaskInfo> getAllVolunteerRecordByUser(String username) {
        return volunteerRecordDao.getVolunteerRecordByUser(username);
    }

    public List<String> getAllProjectedVolunteerRecordsByUser(String username, String projectionProp)
    {
        return volunteerRecordDao.getAllProjectedVolunteerRecordsByUser(username, projectionProp);
    }

    public List<VolunteerTimeEntryRecord> getAllVolunteerRecord() {
        return volunteerRecordDao.getAllVolunteerRecords();
    }

    public List<UserRatingHours> getRatingHoursForAllUsers() {
        return volunteerRecordDao.getRatingHoursForAllUsers();
    }
}
