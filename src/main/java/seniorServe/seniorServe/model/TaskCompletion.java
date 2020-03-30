package seniorServe.seniorServe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

public class TaskCompletion {
    @NotNull
    private final int complete_ID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final Date date;
    @NotNull
    private final int task_ID;
    private final double monetaryAmount;
    @NotBlank
    private final String username;

    public TaskCompletion(@JsonProperty("complete_ID") int complete_ID,
                          @JsonProperty("date") Date date,
                          @JsonProperty("task_ID") int task_ID,
                          @JsonProperty("monetaryAmount") double monetaryAmount,
                          @JsonProperty("username") String username) {
        this.complete_ID = complete_ID;
        this.date = date;
        this.task_ID = task_ID;
        this.monetaryAmount = monetaryAmount;
        this.username = username;
    }

    public int getComplete_ID() {
        return complete_ID;
    }

    public Date getDate() {
        return date;
    }

    public int getTask_ID() {
        return task_ID;
    }

    public double getMonetaryAmount() {
        return monetaryAmount;
    }

    public String getUsername() {
        return username;
    }
}
