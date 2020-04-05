package seniorServe.seniorServe.model;

public class UserJoin {
    private final String username1;
    private final Object attribute1;
    private final String username2;
    private final Object attribute2;

    public UserJoin(String username1, Object attribute1, String username2, Object attribute2) {
        this.username1 = username1;
        this.attribute1 = attribute1;
        this.username2 = username2;
        this.attribute2 = attribute2;
    }

    public String getUsername1() {
        return username1;
    }

    public Object getAttribute1() {
        return attribute1;
    }

    public String getUsername2() {
        return username2;
    }

    public Object getAttribute2() {
        return attribute2;
    }
}
