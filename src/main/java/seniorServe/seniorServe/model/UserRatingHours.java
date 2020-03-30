package seniorServe.seniorServe.model;

public class UserRatingHours extends UserRating {
    private int totalHours;

    public UserRatingHours(String username, double rating, int totalHours) {
        super(username, rating);
        this.totalHours = totalHours;
    }

    public double getTotalHours() {
        return totalHours;
    }
}
