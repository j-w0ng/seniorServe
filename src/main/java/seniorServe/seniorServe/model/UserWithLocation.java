package seniorServe.seniorServe.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserWithLocation extends User {

    private final String province;
    private final String city;

    public UserWithLocation(@JsonProperty("username") String username,
                            @JsonProperty("firstName") String firstName,
                            @JsonProperty("lastName") String lastName,
                            @JsonProperty("postalCode") String postalCode,
                            @JsonProperty("address") String address,
                            @JsonProperty("province") String province,
                            @JsonProperty("city") String city)
    {
        super(username, firstName, lastName, postalCode, address);
        this.province = province;
        this.city = city;
    }


    public String getProvince()
    {
        return province;
    }

    public String getCity()
    {
        return city;
    }
}
