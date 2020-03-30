package seniorServe.seniorServe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.sql.Time;

public class VolunteerTimeEntryRecord {
    private final int record_ID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final Date date;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private final Time timeOfDay;
    private final int hours;
    private final String username;
    private final int task_id;

    public VolunteerTimeEntryRecord(
            @JsonProperty("record_ID") int record_ID,
            @JsonProperty("date") Date date,
            @JsonProperty("timeOfDay") Time timeOfDay,
            @JsonProperty("hours") int hours,
            @JsonProperty("username") String username,
            @JsonProperty("task_id") int task_id) {
        this.record_ID = record_ID;
        this.date = date;
        this.timeOfDay = timeOfDay;
        this.hours = hours;
        this.username = username;
        this.task_id = task_id;
    }

    public int getRecord_ID() {
        return record_ID;
    }

    public Time getTimeOfDay() {
        return timeOfDay;
    }

    public int getHours() {
        return hours;
    }

    public String getUsername() {
        return username;
    }

    public int getTask_id() {
        return task_id;
    }

    public Date getDate() {
        return date;
    }
}
