package seniorServe.seniorServe.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import seniorServe.seniorServe.model.Location;

import java.util.List;
import java.util.regex.Pattern;

@Repository("locationDaoPostgres")
public class LocationDataAccessService implements LocationDao {

    private final JdbcTemplate jdbcTemplate;
    public LocationDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertLocation(Location l) {
        String postalCodeInsertQuery =
                "INSERT INTO PostalCode VALUES ('" + l.getPostalCode() + "', '" + l.getCity() + "', '" + l.getProvince() +"') " +
                        "ON CONFLICT (PostalCode) DO NOTHING;";

        String locationInsert =
                "INSERT INTO Location VALUES ('" + l.getPostalCode() + "', '" + addDoubleQuery(l.getAddress()) + "') " +
                        "ON CONFLICT (PostalCode, Address) DO NOTHING;";
        return jdbcTemplate.update(postalCodeInsertQuery) + jdbcTemplate.update(locationInsert);
    }

    @Override
    public List<Location> selectAllLocations() {
        String sqlQuery =
                "SELECT * FROM Location l, PostalCode p WHERE l.postalcode = p.postalcode ORDER BY p.PostalCode;";
        return jdbcTemplate.query(sqlQuery, CustomRowMapper::LocationRowMapper);
    }

    @Override
    public int deleteLocation(Location location) {
        String query = "DELETE FROM Location WHERE PostalCode = '" + location.getPostalCode() + "'" +
                "AND Address = '" + addDoubleQuery(location.getAddress()) + "'";
        String query2 = "DELETE FROM PostalCode p WHERE NOT EXISTS (SELECT * FROM Location l " +
                "WHERE l.postalCode = p.postalCode)";
        return jdbcTemplate.update(query) + jdbcTemplate.update(query2);
    }

    @Override
    public int updateLocation(Location previous, Location updated) {
        return 0;
    }

    @Override
    public List<String> selectAllProvinces() {
        String query = "SELECT DISTINCT province FROM postalcode;";
        return jdbcTemplate.query(query, (resultSet, i) ->
            resultSet.getString("province"));
    }

    @Override
    public List<String> selectAllCities() {
        String query = "SELECT DISTINCT city FROM postalcode;";
        return jdbcTemplate.query(query, (resultSet, i) ->
                resultSet.getString("city"));
    }

    private static boolean isValidPostalCode(String PostalCode) {
        String regex = "^(?![DFIOQU])([ABCEGHJ-NPRSTVXY]\\d[A-Z]\\d[A-Z]\\d)$";
        return Pattern.matches(regex, PostalCode);
    }

    /**
     * Takes input string, and makes any apostrophe (') to ('')
     * @param input
     * @return
     */
    private static String addDoubleQuery(String input) {
        String output = "";
        String[] strings = input.split("'");
        for (int i=0; i<strings.length; i++) {
            output += strings[i];
            if (i < strings.length - 1) {
                output += "''";
            }
        }
        return output;
    }
}
