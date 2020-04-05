package seniorServe.seniorServe.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.sql.Time;

public class VolunteerTimeEntryRecordTaskInfo extends VolunteerTimeEntryRecord{

    private String seniorUsername;
    private String taskDescription;

    public VolunteerTimeEntryRecordTaskInfo(@JsonProperty("record_ID") int record_ID,
                                            @JsonProperty("date") Date date,
                                            @JsonProperty("timeOfDay") Time timeOfDay,
                                            @JsonProperty("hours") int hours,
                                            @JsonProperty("username") String username,
                                            @JsonProperty("task_id") int task_id,
                                            @JsonProperty("senior") String seniorUsername,
                                            @JsonProperty("description") String taskDescription)
    {
        super(record_ID, date, timeOfDay, hours, username, task_id);
        this.seniorUsername = seniorUsername;
        this.taskDescription = taskDescription;
    }

    public VolunteerTimeEntryRecordTaskInfo() {
        super();
        this.seniorUsername = null;
        this.taskDescription = null;
    }

    public String getSeniorUsername()
    {
        return seniorUsername;
    }

    public String getTaskDescription()
    {
        return taskDescription;
    }

    public void setSeniorUsername(String seniorUsername) {
        this.seniorUsername = seniorUsername;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
