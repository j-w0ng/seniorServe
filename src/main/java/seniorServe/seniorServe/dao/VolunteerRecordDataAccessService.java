package seniorServe.seniorServe.dao;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import seniorServe.seniorServe.model.UserRatingHours;
import seniorServe.seniorServe.model.VolunteerRecordString;
import seniorServe.seniorServe.model.VolunteerTimeEntryRecord;
import seniorServe.seniorServe.model.VolunteerTimeEntryRecordTaskInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

// PRIMARY Data Access Class for TABLE VolunteersTimeEntryRecord (Date, TimeOfDay, Hours, Username, Task_ID)
@Repository("volunteerRecordPostgres")
public class VolunteerRecordDataAccessService implements VolunteerRecordDao {
    private final JdbcTemplate jdbcTemplate;

    private final List<String> record_attributes = Arrays.asList("date", "timeOfDay", "hours", "username",
            "task_id", "senior", "description");

    public VolunteerRecordDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addVolunteerRecord(VolunteerTimeEntryRecord r) {
        int recordID = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
        String query = QueryHelper.insertQuery("VolunteersTimeEntryRecord",
                Arrays.asList("Record_ID", "Date", "TimeOfDay", "Hours", "Username", "Task_ID"),
                Arrays.asList(Integer.toString(recordID), r.getDate().toString(), r.getTimeOfDay().toString(),
                        Integer.toString(r.getHours()), r.getUsername(), Integer.toString(r.getTask_id())));
        return jdbcTemplate.update(query);
    }

    @Override
    public int deleteVolunteerRecord(VolunteerTimeEntryRecord r) {
        String query = "DELETE FROM VolunteersTimeEntryRecord WHERE record_ID = " + r.getRecord_ID();
        return jdbcTemplate.update(query);
    }

    @Override
    public int deleteVolunteerRecordByUser(String username) {
        String query = "DELETE FROM VolunteersTimeEntryRecord WHERE "+
                "Username = '" + username + "'";
        return jdbcTemplate.update(query);
    }


    /**
     * @param r
     * @return 2 - entry was updated
     *         1 - entry was added (didn't exist before)
     */
    @Override
    public int updateVolunteerRecord(VolunteerTimeEntryRecord r) {
        String query = QueryHelper.insertQuery("VolunteersTimeEntryRecord",
                Arrays.asList("Record_ID", "Date", "TimeOfDay", "Hours", "Username", "Task_ID"),
                Arrays.asList(Integer.toString(r.getRecord_ID()), r.getDate().toString(), r.getTimeOfDay().toString(),
                        Integer.toString(r.getHours()), r.getUsername(), Integer.toString(r.getTask_id())));
        return deleteVolunteerRecord(r) + jdbcTemplate.update(query);
    }

    // VolunteersTimeEntryRecord (Date, TimeOfDay, Hours, Username, Task_ID)

    /**
     * Returns the sum of hours by user. If no hours exist, return 0
     * @param username
     * @return
     */
    @Override
    public int getSumHoursByUser(String username) {
        String sqlQuery =
                "SELECT to_char(SUM(Hours),'999999') FROM VolunteersTimeEntryRecord WHERE Username = '" + username +"'";
        try {
            Object result = jdbcTemplate.queryForObject(sqlQuery, Integer.class);
            if (result == null) {
                return 0;
            }
            return (int) result;
        } catch (IncorrectResultSizeDataAccessException e) {
            return 0;
        }
    }

    @Override
    public List<VolunteerTimeEntryRecordTaskInfo> getVolunteerRecordByUser(String username) {
        String sqlQuery =
                " SELECT r.record_ID, r.Date, r.TimeOfDay, r.Hours, r.Username, r.Task_ID, t.Description, t.Username as senior" +
                " FROM VolunteersTimeEntryRecord r, Task t" +
                " WHERE r.Task_ID = t.Task_ID AND r.Username = '" + username + "' ORDER BY r.Date DESC, r.TimeOfDay DESC";
        return jdbcTemplate.query(sqlQuery, CustomRowMapper::VolunteerRecordRowMapperTaskInfo);
    }

    @Override
    public List<String> getAllProjectedVolunteerRecordsByUser(String username, String projectionProp) {
        String querySelection;
        switch (projectionProp)
        {
            case "date":
                querySelection = "r.date";
                break;
            case "description":
                querySelection = "t.Description";
                break;
            case "hours":
                querySelection = "r.Hours";
                break;
            case "senior":
                querySelection = "t.Username";
                break;
            default:
                querySelection = "r.record_ID, r.Date, r.TimeOfDay, r.Hours, r.Username, r.Task_ID, t.Description, t.Username as senior";
        }

        String query =  " SELECT " + querySelection + " as property " +
                        " FROM VolunteersTimeEntryRecord r, Task t" +
                        " WHERE r.Task_ID = t.Task_ID AND r.Username = '" + username + "' ORDER BY r.Date DESC, r.TimeOfDay DESC";
        return jdbcTemplate.query(query,  (resultSet, i) ->
                resultSet.getString("property"));
    }

    @Override
    public List<UserRatingHours> getRatingHoursForAllUsers() {
        String sqlQuery =
                "SELECT * FROM userRatingHours ORDER BY totalHours DESC, rating DESC limit 5";

        return jdbcTemplate.query(sqlQuery, CustomRowMapper::UserRatingHoursRowMapper);
    }

    @Override
    public List<VolunteerTimeEntryRecord> getAllVolunteerRecords() {
        String sqlQuery =
                "SELECT * FROM VolunteersTimeEntryRecord ORDER BY Username";
        return jdbcTemplate.query(sqlQuery, CustomRowMapper::VolunteerRecordRowMapper);
    }

    public List<String> parseProjectionList(String projectionList) {
        List<String> list = new ArrayList<>();
        String[] updates = projectionList.split("\\|");

        for (String update : updates) {
            if (record_attributes.contains(update)) {
                list.add(update);
            }
        }
        System.out.println(list);
        return list;
    }

    public String projectionQueryBuilder(List<String> parsedProjectionList)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(",");
        if (parsedProjectionList.contains("date")) {
            sb.append("r.date as date, ");
        }

        if (parsedProjectionList.contains("timeOfDay")) {
            sb.append("r.timeOfDay as timeOfDay, ");
        }

        if (parsedProjectionList.contains("hours")) {
            sb.append("r.hours as hours, ");
        }

        if (parsedProjectionList.contains("username")) {
            sb.append("r.username as username, ");
        }

        if (parsedProjectionList.contains("task_id")) {
            sb.append("r.task_id as task_id, ");
        }

        if (parsedProjectionList.contains("senior")) {
            sb.append("t.username as senior, ");
        }

        if (parsedProjectionList.contains("description")) {
            sb.append("t.description as description, ");
        }
        sb.replace(sb.lastIndexOf(","), sb.length(), "");
        System.out.println(sb.toString());
        return sb.toString();
    }

    @Override
    public List<VolunteerRecordString> getProjection(String username, String projectionList) {
        List<String> projArray = parseProjectionList(projectionList);
        String query = " SELECT r.record_id " + projectionQueryBuilder(projArray) +  " FROM VolunteersTimeEntryRecord r, Task t" +
                " WHERE r.Task_ID = t.Task_ID AND r.Username = '" + username + "' ORDER BY r.Date DESC, r.TimeOfDay DESC";
        return jdbcTemplate.query(query,
                rs -> {
                    List<VolunteerRecordString> listRecord = new ArrayList<>();
                    while (rs.next()) {
                        VolunteerRecordString record = new VolunteerRecordString();
                        record.setRecord_ID(rs.getString("record_ID"));

                        if (projArray.contains("date")) {
                            record.setDate(rs.getString("date"));
                        }

                        if (projArray.contains("timeOfDay")) {
                            record.setTimeOfDay(rs.getString("timeOfDay"));
                        }

                        if (projArray.contains("hours")) {
                            record.setHours(rs.getString("hours"));
                        }

                        if (projArray.contains("username")) {
                            record.setUsername(rs.getString("username"));
                        }

                        if (projArray.contains("task_id")) {
                            record.setTask_id(rs.getString("task_id"));
                        }

                        if (projArray.contains("senior")) {
                            record.setSeniorUsername(rs.getString("senior"));
                        }

                        if (projArray.contains("description")) {
                            record.setTaskDescription(rs.getString("description"));
                        }
                        listRecord.add(record);
                    }
                    return listRecord;
                });
    }

}
