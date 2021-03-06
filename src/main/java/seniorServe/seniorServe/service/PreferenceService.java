package seniorServe.seniorServe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import seniorServe.seniorServe.dao.PreferenceDao;
import seniorServe.seniorServe.model.Preference;

import java.util.List;

@Service
public class PreferenceService
{
    private final PreferenceDao preferenceDao;

    @Autowired
    public PreferenceService(@Qualifier("preferenceDaoPostgres") PreferenceDao preferenceDao)
    {
        this.preferenceDao = preferenceDao;
    }

    public int addTaskPreference(int task_id, int pref_id) {
        return preferenceDao.addTaskPreference(task_id, pref_id);
    }

    public List<Preference> getAllPreferences()
    {
        return preferenceDao.selectAllPreferences();
    }

    public List<String> getAllPreferenceNames()
    {
        return preferenceDao.selectAllPreferenceNames();
    }
}
