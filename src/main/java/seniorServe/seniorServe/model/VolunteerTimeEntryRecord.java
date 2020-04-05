package seniorServe.seniorServe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.sql.Time;

public class VolunteerTimeEntryRecord {
    private int record_ID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Time timeOfDay;
    private int hours;
    private String username;
    private int task_id;

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

    public VolunteerTimeEntryRecord() {
        this.record_ID = -1;
        this.date = null;
        this.timeOfDay = null;
        this.hours = -1;
        this.username = null;
        this.task_id = -1;
    }

    public int getRecord_ID() {
        return record_ID;
    }


    public Date getDate() {
        return date;
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

    public void setRecord_ID(int record_ID) {
        this.record_ID = record_ID;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTimeOfDay(Time timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }
}
