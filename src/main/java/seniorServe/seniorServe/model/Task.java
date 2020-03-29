package seniorServe.seniorServe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class Task {

    @NotNull
    private final int task_ID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final Date date;
    private final String description;
    private final int num_Volunteer;
    private final String status;
    private final String postalCode;
    private final String address;
    private final String username;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final Date createTime;

    public Task(@JsonProperty("Task_ID") int task_ID,
                @JsonProperty("Date") Date date,
                @JsonProperty("Description") String description,
                @JsonProperty("Num_Volunteer") int num_Volunteer,
                @JsonProperty("Status") String status,
                @JsonProperty("PostalCode") String postalCode,
                @JsonProperty("Address") String address,
                @JsonProperty("Username") String username,
                @JsonProperty("CreateTime") Date createTime) {
        this.task_ID = task_ID;
        this.date = date;
        this.description = description;
        this.num_Volunteer = num_Volunteer;
        this.status = status;
        this.postalCode = postalCode;
        this.address = address;
        this.username = username;
        this.createTime = createTime;
    }

    public int getTask_ID() {
        return task_ID;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getNum_Volunteer() {
        return num_Volunteer;
    }

    public String getStatus() {
        return status;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
