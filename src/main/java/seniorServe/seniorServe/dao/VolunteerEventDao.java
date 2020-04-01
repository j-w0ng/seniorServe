package seniorServe.seniorServe.dao;

import seniorServe.seniorServe.model.TaskLocation;
import seniorServe.seniorServe.model.VolunteerEvent;

import java.util.Date;
import java.util.List;

public interface VolunteerEventDao
{
    int insertVolunteerEvent(VolunteerEvent volunteerEvent);

    List<VolunteerEvent> selectAllVolunteerEvents();

    VolunteerEvent selectVolunteerEvent(String username, int task_id);

    int deleteVolunteerEvent(String username, int task_id);

    int updateVolunteerEvent(String username, int task_id, Date date);

    List<TaskLocation> getAllAcceptedTasks(String username);
}
