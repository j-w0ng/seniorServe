package seniorServe.seniorServe.dao;

import seniorServe.seniorServe.model.UserRatingHours;
import seniorServe.seniorServe.model.VolunteerTimeEntryRecord;
import seniorServe.seniorServe.model.VolunteerTimeEntryRecordTaskInfo;

import java.util.List;

public interface VolunteerRecordDao {
    int addVolunteerRecord(VolunteerTimeEntryRecord record);

    int deleteVolunteerRecord(VolunteerTimeEntryRecord record);

    int deleteVolunteerRecordByUser(String username);

    int updateVolunteerRecord(VolunteerTimeEntryRecord record);

    int getSumHoursByUser(String username);

    List<VolunteerTimeEntryRecordTaskInfo> getVolunteerRecordByUser(String username);

    List<VolunteerTimeEntryRecord> getAllVolunteerRecords();

    List<UserRatingHours> getRatingHoursForAllUsers();
}
