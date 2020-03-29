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

    @PostMapping
    public void addLocation(@Valid @NotNull @RequestBody Location location) {
        locationService.insertLocation(location);
    }

    @GetMapping(path = "getAllLocations")
    public List<Location> getAllLocations() {
        return locationService.getLocations();
    }

    @DeleteMapping
    public int deleteLocation(@Valid @NotNull @RequestBody Location location) {
        return locationService.deleteLocation(location);
    }
}
