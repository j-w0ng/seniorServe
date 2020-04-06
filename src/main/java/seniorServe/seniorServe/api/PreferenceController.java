package seniorServe.seniorServe.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping(path = "/taskPref={task_id}&{pref_id}")
    public int addTaskPreference(@PathVariable("task_id") int task_id, @PathVariable("pref_id") int pref_id) {
        return preferenceService.addTaskPreference(task_id, pref_id);
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
