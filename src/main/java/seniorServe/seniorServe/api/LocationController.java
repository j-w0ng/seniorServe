package seniorServe.seniorServe.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import seniorServe.seniorServe.model.Location;
import seniorServe.seniorServe.service.LocationService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/location")
@RestController
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService)
    {
        this.locationService = locationService;
    }

    /**
     * Inserts Location (PostalCode, Address, City, Province) into the database. If the combination of
     * (PostalCode, Address) already exists, return 0. Else return number of inserts made
     *
     * @param location
     * @return returns the number of lines inserted (1 if only Location, 2 if added Location and PostalCode)
     *
     */
    @PostMapping
    public int addLocation(@Valid @NotNull @RequestBody Location location) {
        return locationService.insertLocation(location);
    }

    @GetMapping(path = "getAllLocations")
    public List<Location> getAllLocations() {
        return locationService.getLocations();
    }

    @GetMapping(path = "getAllProvinces")
    public List<String> getAllProvinces()
    {
        return locationService.getAllProvinces();
    }

    @GetMapping(path = "getAllCities")
    public List<String> getAllCities()
    {
        return locationService.getAllCities();
    }

    @DeleteMapping
    public int deleteLocation(@Valid @NotNull @RequestBody Location location) {
        return locationService.deleteLocation(location);
    }
}
