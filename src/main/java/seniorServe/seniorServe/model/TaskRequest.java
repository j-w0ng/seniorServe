package seniorServe.seniorServe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

public class TaskRequest {
    @NotNull
    private final int request_ID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final Date date;
    @NotNull
    private final int task_ID;
    @NotBlank
    private final String username;

    public TaskRequest(@JsonProperty("request_ID") int request_ID,
                       @JsonProperty("date") Date date,
                       @JsonProperty("task_ID") int task_ID,
                       @JsonProperty("username") String username) {
        this.request_ID = request_ID;
        this.date = date;
        this.task_ID = task_ID;
        this.username = username;
    }

    public int getRequest_ID() {
        return request_ID;
    }

    public Date getDate() {
        return date;
    }

    public int getTask_ID() {
        return task_ID;
    }

    public String getUsername() {
        return username;
    }
}
