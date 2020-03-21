package seniorServe.seniorServe.dao;

import seniorServe.seniorServe.model.VolunteerEvent;

import java.util.Date;
import java.util.List;

public interface VolunteerEventDao
{
    int insertVolunteerEvent(VolunteerEvent volunteerEvent);

    List<VolunteerEvent> selectAllVolunteerEvents();

    VolunteerEvent selectVolunteerEvent(String primaryKey);

    int deleteVolunteerEvent(String primaryKey);

    int updateVolunteerEvent(String primaryKey, Date date);
}
