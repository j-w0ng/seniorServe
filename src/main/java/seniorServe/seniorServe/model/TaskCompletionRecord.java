package seniorServe.seniorServe.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.sql.Time;

public class TaskCompletionRecord extends TaskCompletion {
    private final int hours;
    private final Time volunteerTime;

    public TaskCompletionRecord(@JsonProperty("complete_ID") int complete_ID,
                @JsonProperty("date") Date date,
                @JsonProperty("task_ID") int task_ID,
                @JsonProperty("monetaryAmount") double monetaryAmount,
                @JsonProperty("username") String username,
                @JsonProperty("hours") int hours,
                @JsonProperty("volunteerTime")Time volunteerTime) {
        super(complete_ID, date, task_ID, monetaryAmount, username);
        this.hours = hours;
        this.volunteerTime = volunteerTime;
    }

    public int getHours() {
        return hours;
    }

    public Time getVolunteerTime() {
        return volunteerTime;
    }

    public TaskCompletion getTaskCompletion() {
        return new TaskCompletion(super.getComplete_ID(), super.getDate(), super.getTask_ID(),
                super.getMonetaryAmount(), super.getUsername());
    }
}
