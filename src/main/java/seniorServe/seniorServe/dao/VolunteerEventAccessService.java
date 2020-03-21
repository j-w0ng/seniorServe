package seniorServe.seniorServe.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import seniorServe.seniorServe.model.VolunteerEvent;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository("volunteerEventPostgres")
public class VolunteerEventAccessService implements VolunteerEventDao
{
    private final JdbcTemplate jdbcTemplate;

    public VolunteerEventAccessService(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertVolunteerEvent(VolunteerEvent volunteerEvent)
    {
        String query = QueryHelper.insertQuery("volunteervolunteers",
                Arrays.asList("Username", "Task_ID", "Date"),
                Arrays.asList(volunteerEvent.getUsername(), Integer.toString(volunteerEvent.getTaskID()), volunteerEvent.getDate().toString()));
        return jdbcTemplate.update(query);
    }

    @Override
    public List<VolunteerEvent> selectAllVolunteerEvents() {
        return null;
    }

    @Override
    public VolunteerEvent selectVolunteerEvent(String primaryKey) {
        return null;
    }

    @Override
    public int deleteVolunteerEvent(String primaryKey) {
        return 0;
    }

    @Override
    public int updateVolunteerEvent(String primaryKey, Date date) {
        return 0;
    }
}
