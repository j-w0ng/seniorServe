package seniorServe.seniorServe.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class User {
    private final String username;
    @NotBlank
    private final String firstName;
    private final String lastName;
    private final String postalCode;
    private final String address;

    public User(@JsonProperty("username") String username,
                @JsonProperty("firstName") String firstName,
                @JsonProperty("lastName") String lastName,
                @JsonProperty("postalCode") String postalCode,
                @JsonProperty("address") String address) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
        this.address = address;
    }

    public String getUsername() {
        return this.username;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getAddress() {
        return address;
    }
}

