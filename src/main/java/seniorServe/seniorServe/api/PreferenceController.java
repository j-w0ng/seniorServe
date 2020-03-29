package seniorServe.seniorServe.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seniorServe.seniorServe.model.Preference;
import seniorServe.seniorServe.service.PreferenceService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/preference")
@RestController
public class PreferenceController
{
    private final PreferenceService preferenceService;

    @Autowired
    public PreferenceController(PreferenceService preferenceService)
    {
        this.preferenceService = preferenceService;
    }

    @GetMapping
    public List<Preference> getPreferences()
    {
        return preferenceService.getAllPreferences();
    }

    @GetMapping(path="getAllPreferenceNames")
    public List<String> getAllPreferenceNames()
    {
        return preferenceService.getAllPreferenceNames();
    }

}
