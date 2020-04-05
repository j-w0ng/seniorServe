package seniorServe.seniorServe.model;

import com.fasterxml.jackson.annotation.JsonFormat;

public class VolunteerRecordString {
    private String record_ID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String date;
    private String timeOfDay;
    private String hours;
    private String username;
    private String task_id;
    private String seniorUsername;
    private String taskDescription;

    public VolunteerRecordString(String record_ID, String date, String timeOfDay, String hours, String username,
                                 String task_id, String seniorUsername, String taskDescription) {
        this.record_ID = record_ID;
        this.date = date;
        this.timeOfDay = timeOfDay;
        this.hours = hours;
        this.username = username;
        this.task_id = task_id;
        this.seniorUsername = seniorUsername;
        this.taskDescription = taskDescription;
    }

    public VolunteerRecordString() {
        this.record_ID = null;
        this.date = null;
        this.timeOfDay = null;
        this.hours = null;
        this.username = null;
        this.task_id = null;
        this.seniorUsername = null;
        this.taskDescription = null;
    }

    public String getRecord_ID() {
        return record_ID;
    }

    public void setRecord_ID(String record_ID) {
        this.record_ID = record_ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getSeniorUsername() {
        return seniorUsername;
    }

    public void setSeniorUsername(String seniorUsername) {
        this.seniorUsername = seniorUsername;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
