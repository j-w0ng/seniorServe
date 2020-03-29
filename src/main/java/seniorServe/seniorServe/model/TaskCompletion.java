package seniorServe.seniorServe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

public class TaskCompletion {
    @NotNull
    private final int Complete_ID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final Date date;
    @NotNull
    private final int Task_ID;
    private final double MonetaryAmount;
    @NotBlank
    private final String Username;

    public TaskCompletion(@JsonProperty("complete_ID") int complete_ID,
                          @JsonProperty("date") Date date,
                          @JsonProperty("task_ID") int task_ID,
                          @JsonProperty("monetaryAmount") double monetaryAmount,
                          @JsonProperty("username") String username) {
        Complete_ID = complete_ID;
        this.date = date;
        Task_ID = task_ID;
        MonetaryAmount = monetaryAmount;
        Username = username;
    }

    public int getComplete_ID() {
        return Complete_ID;
    }

    public Date getDate() {
        return date;
    }

    public int getTask_ID() {
        return Task_ID;
    }

    public double getMonetaryAmount() {
        return MonetaryAmount;
    }

    public String getUsername() {
        return Username;
    }
}
