package seniorServe.seniorServe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

public class VolunteerEvent
{
    @NotBlank @NotNull
    private final String username;
    @NotBlank @NotNull
    private final int task_ID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final Date date;

    public VolunteerEvent(@JsonProperty("username") String username,
                          @JsonProperty("taskID") int task_ID,
                          @JsonProperty("date") Date date)
    {
        this.username = username;
        this.task_ID = task_ID;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public int getTaskID() {
        return task_ID;
    }

    public Date getDate() {
        return date;
    }
}
