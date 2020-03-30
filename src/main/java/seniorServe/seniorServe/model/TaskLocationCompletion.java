package seniorServe.seniorServe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class TaskLocationCompletion extends TaskLocation {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final Date completionDate;

    public TaskLocationCompletion(@JsonProperty("Task_ID") int task_ID,
                                  @JsonProperty("Date") Date date,
                                  @JsonProperty("Description") String description,
                                  @JsonProperty("Num_Volunteer") int num_Volunteer,
                                  @JsonProperty("Status") String status,
                                  @JsonProperty("PostalCode") String postalCode,
                                  @JsonProperty("Address") String address,
                                  @JsonProperty("Username") String username,
                                  @JsonProperty("CreateTime") Date createTime,
                                  @JsonProperty("City") String city,
                                  @JsonProperty("Province") String province,
                                  @JsonProperty("CompletionDate") Date completionDate) {
        super(task_ID, date, description, num_Volunteer, status, postalCode, address, username, createTime, city, province);
        this.completionDate = completionDate;
    }


    public Date getCompletionDate() {
        return completionDate;
    }
}
