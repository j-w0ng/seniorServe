package seniorServe.seniorServe.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class UserRatingHoursDate extends UserRatingHours {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private final Date recentDate;

    public UserRatingHoursDate(String username, double rating, int totalHours, Date recentDate) {
        super(username, rating, totalHours);
        this.recentDate = recentDate;
    }

    public Date getRecentDate() {
        return recentDate;
    }
}
