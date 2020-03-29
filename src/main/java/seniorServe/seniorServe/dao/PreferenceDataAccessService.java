package seniorServe.seniorServe.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import seniorServe.seniorServe.model.Preference;

import java.util.List;

@Repository("preferenceDaoPostgres")
public class PreferenceDataAccessService implements PreferenceDao
{
    private final JdbcTemplate jdbcTemplate;

    public PreferenceDataAccessService(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Preference> selectAllPreferences() {
        String query = "SELECT * FROM preference;";
        return jdbcTemplate.query(query, (resultSet, i) ->
                new Preference(resultSet.getInt("pref_id"),
                        resultSet.getString("pref_name"),
                        resultSet.getString("description")));
    }

    @Override
    public List<String> selectAllPreferenceNames() {
        String query = "SELECT DISTINCT pref_name FROM preference;";
        return jdbcTemplate.query(query, (resultSet, i) ->
                resultSet.getString("pref_name"));
    }
}
