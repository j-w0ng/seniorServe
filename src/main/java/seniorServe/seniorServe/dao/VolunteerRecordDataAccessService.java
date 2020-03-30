package seniorServe.seniorServe.dao;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import seniorServe.seniorServe.model.UserRatingHours;
import seniorServe.seniorServe.model.VolunteerTimeEntryRecord;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

// PRIMARY Data Access Class for TABLE VolunteersTimeEntryRecord (Date, TimeOfDay, Hours, Username, Task_ID)
@Repository("volunteerRecordPostgres")
public class VolunteerRecordDataAccessService implements VolunteerRecordDao {
    private final JdbcTemplate jdbcTemplate;


    public VolunteerRecordDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addVolunteerRecord(VolunteerTimeEntryRecord r) {
        int recordID = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
        String query = QueryHelper.insertQuery("VolunteersTimeEntryRecord",
                Arrays.asList("Record_ID", "Date", "TimeOfDay", "Hours", "Username", "Task_ID"),
                Arrays.asList(Integer.toString(recordID), r.getDate().toString(), r.getTimeOfDay().toString(),
                        Integer.toString(r.getHours()), r.getUsername(), Integer.toString(r.getTask_id())));
        return jdbcTemplate.update(query);
    }

    @Override
    public int deleteVolunteerRecord(VolunteerTimeEntryRecord r) {
        String query = "DELETE FROM VolunteersTimeEntryRecord WHERE record_ID = " + r.getRecord_ID();
        return jdbcTemplate.update(query);
    }

    @Override
    public int deleteVolunteerRecordByUser(String username) {
        String query = "DELETE FROM VolunteersTimeEntryRecord WHERE "+
                "Username = '" + username + "'";
        return jdbcTemplate.update(query);
    }


    /**
     * @param r
     * @return 2 - entry was updated
     *         1 - entry was added (didn't exist before)
     */
    @Override
    public int updateVolunteerRecord(VolunteerTimeEntryRecord r) {
        String query = QueryHelper.insertQuery("VolunteersTimeEntryRecord",
                Arrays.asList("Record_ID", "Date", "TimeOfDay", "Hours", "Username", "Task_ID"),
                Arrays.asList(Integer.toString(r.getRecord_ID()), r.getDate().toString(), r.getTimeOfDay().toString(),
                        Integer.toString(r.getHours()), r.getUsername(), Integer.toString(r.getTask_id())));
        return deleteVolunteerRecord(r) + jdbcTemplate.update(query);
    }

    // VolunteersTimeEntryRecord (Date, TimeOfDay, Hours, Username, Task_ID)

    /**
     * Returns the sum of hours by user. If no hours exist, return 0
     * @param username
     * @return
     */
    @Override
    public int getSumHoursByUser(String username) {
        String sqlQuery =
                "SELECT to_char(SUM(Hours),'999999') FROM VolunteersTimeEntryRecord WHERE Username = '" + username +"'";
        try {
            Object result = jdbcTemplate.queryForObject(sqlQuery, Integer.class);
            if (result == null) {
                return 0;
            }
            return (int) result;
        } catch (IncorrectResultSizeDataAccessException e) {
            return 0;
        }
    }

    @Override
    public List<VolunteerTimeEntryRecord> getVolunteerRecordByUser(String username) {
        String sqlQuery =
                " SELECT r.record_ID, r.Date, r.TimeOfDay, r.Hours, r.Username, r.Task_ID FROM VolunteersTimeEntryRecord r " +
                " WHERE r.Username = '" + username + "' ORDER BY r.Date DESC, r.TimeOfDay DESC";
        return jdbcTemplate.query(sqlQuery, CustomRowMapper::VolunteerRecordRowMapper);
    }

    @Override
    public List<UserRatingHours> getRatingHoursForAllUsers() {
        String sqlQuery =
                "SELECT * FROM userRatingHours ORDER BY rating DESC, totalHours DESC limit 5";

        return jdbcTemplate.query(sqlQuery, CustomRowMapper::UserRatingHoursRowMapper);
    }

    @Override
    public List<VolunteerTimeEntryRecord> getAllVolunteerRecords() {
        String sqlQuery =
                "SELECT * FROM VolunteersTimeEntryRecord ORDER BY Username";
        return jdbcTemplate.query(sqlQuery, CustomRowMapper::VolunteerRecordRowMapper);
    }
}
