package seniorServe.seniorServe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import seniorServe.seniorServe.dao.VolunteerEventDao;
import seniorServe.seniorServe.model.VolunteerEvent;

import java.util.Date;
import java.util.List;

@Service
public class VolunteerEventService
{
    private final VolunteerEventDao volunteerEventDao;

    @Autowired
    public VolunteerEventService(@Qualifier("volunteerEventPostgres") VolunteerEventDao volunteerEventDao)
    {
        this.volunteerEventDao = volunteerEventDao;
    }

    public int addVolunteerEvent(VolunteerEvent volunteerEvent)
    {
        return volunteerEventDao.insertVolunteerEvent(volunteerEvent);
    }

    public List<VolunteerEvent> getAllVolunteerEvents()
    {
        return volunteerEventDao.selectAllVolunteerEvents();
    }

    public VolunteerEvent getVolunteerEventByPK(String primaryKey)
    {
        return volunteerEventDao.selectVolunteerEvent(primaryKey);
    }

    public int deleteVolunteerEvent(String primaryKey)
    {
        return volunteerEventDao.deleteVolunteerEvent(primaryKey);
    }

    public int updateVolunteerEvent(String primaryKey, Date volunteerEvent)
    {
        return volunteerEventDao.updateVolunteerEvent(primaryKey, volunteerEvent);
    }
}
