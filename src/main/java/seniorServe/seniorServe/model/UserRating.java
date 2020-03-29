package seniorServe.seniorServe.model;

public class UserRating {
    private String username;
    private double rating;

    public UserRating(String username, double rating) {
        this.rating = rating;
        this.username = username;
    }

    public double getRating() {
        return rating;
    }

    public String getUsername() {
        return username;
    }
}
