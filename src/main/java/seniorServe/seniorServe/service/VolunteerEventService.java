package seniorServe.seniorServe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import seniorServe.seniorServe.dao.VolunteerEventDao;
import seniorServe.seniorServe.model.TaskLocation;
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

    public VolunteerEvent getVolunteerEventByPK(String username, int task_id)
    {
        return volunteerEventDao.selectVolunteerEvent(username, task_id);
    }

    public int deleteVolunteerEvent(String username, int task_id)
    {
        return volunteerEventDao.deleteVolunteerEvent(username, task_id);
    }

    public int updateVolunteerEvent(String username, int task_id, Date date)
    {
        return volunteerEventDao.updateVolunteerEvent(username, task_id, date);
    }

    public List<TaskLocation> getAllAcceptedTasks(String username) {
        return volunteerEventDao.getAllAcceptedTasks(username);
    }
}
