package seniorServe.seniorServe.dao;

import seniorServe.seniorServe.model.Preference;

import java.util.List;

public interface PreferenceDao
{
    List<Preference> selectAllPreferences();

    List<String> selectAllPreferenceNames();

    int addTaskPreference(int task_id, int pref_id);
}
