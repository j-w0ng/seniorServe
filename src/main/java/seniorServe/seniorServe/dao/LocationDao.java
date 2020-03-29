package seniorServe.seniorServe.dao;

import seniorServe.seniorServe.model.Location;

import java.util.List;

public interface LocationDao {
    int insertLocation(Location location);

    List<Location> selectAllLocations();

    int deleteLocation(Location location);

    int updateLocation(Location previous, Location updated);

    List<String> selectAllProvinces();

    List<String> selectAllCities();
}
