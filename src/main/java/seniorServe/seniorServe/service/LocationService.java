package seniorServe.seniorServe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import seniorServe.seniorServe.dao.LocationDao;
import seniorServe.seniorServe.model.Location;

import java.util.List;

@Service
public class LocationService {
    private final LocationDao locationDao;

    @Autowired
    public LocationService(@Qualifier("locationDaoPostgres") LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    public int insertLocation(Location location) {
        return locationDao.insertLocation(location);
    }

    public int deleteLocation(Location location) {
        return locationDao.deleteLocation(location);
    }

    public List<Location> getLocations() {
        return locationDao.selectAllLocations();
    }

    public List<String> getAllProvinces()
    {
        return locationDao.selectAllProvinces();
    }

    public List<String> getAllCities()
    {
        return locationDao.selectAllCities();
    }
}
