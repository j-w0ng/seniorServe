package seniorServe.seniorServe.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class Review
{
    private final String reviewID;
    @NotBlank
    private final String description;
    private final int rating;
    private final int taskID;
    private final String volunteerUserName;

    public Review(@JsonProperty("reviewID") String reviewID,
                  @JsonProperty("description") String description,
                  @JsonProperty("rating") int rating,
                  @JsonProperty("taskID") int taskID,
                  @JsonProperty("volunteerUserName") String volunteerUserName)
    {
        this.reviewID = reviewID;
        this.description = description;
        this.rating = rating;
        this.taskID = taskID;
        this.volunteerUserName = volunteerUserName;
    }

    public String getReviewID()
    {
        return reviewID;
    }

    public String getDescription()
    {
        return description;
    }

    public int getRating()
    {
        return rating;
    }

    public int getTaskID()
    {
        return taskID;
    }

    public String getVolunteerUserName()
    {
        return volunteerUserName;
    }
}
