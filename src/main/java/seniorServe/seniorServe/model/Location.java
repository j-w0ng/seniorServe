package seniorServe.seniorServe.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

public class Location {
    private final String postalCode;
    private final String address;
    private final String city;
    private final String province;
    private static final List<String> canadaProvinces =
            Arrays.asList("BC", "AB", "MB", "NB", "NL", "NS", "NT", "NU", "ON", "PE", "QC", "SK", "YT");

    public Location(@JsonProperty("PostalCode") String postalCode,
                    @JsonProperty("Address") String address,
                    @JsonProperty("City") String city,
                    @JsonProperty("Province") String province) {
        this.postalCode = postalCode;
        this.address = address;
        this.city = city;
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getAddress() {
        return address;
    }


    public String getCity() {
        return city;
    }

    public static List<String> getCanadianProvinces() {
        return canadaProvinces;
    }

    public String getProvince() {
        return province;
    }
}
