package seniorServe.seniorServe.dao;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import seniorServe.seniorServe.model.Task;
import seniorServe.seniorServe.model.TaskLocation;

import java.sql.Date;
import java.util.*;

@Repository("taskPostgres")
public class TaskDataAccessService implements TaskDao {
    private final JdbcTemplate jdbcTemplate;
    private static final List<String> taskAttributes = Arrays.asList("task_id", "date", "description",
            "num_volunteer", "status", "postalcode", "address", "username", "createtime");
    private static final List<String> taskTypes = Arrays.asList("int", "Date", "String",
            "int", "String", "String", "String", "String", "Date");
    private static final List<String> locationAttributes = Arrays.asList("postalcode", "address", "city", "province");
    private static final List<String> taskFilters = Arrays.asList("loc", "city", "prov", "pref", "daterange");
    public TaskDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    // Note: May need to handle DataAccessException. update() returns number of rows affected.
    public int insertTask(Task task) {
        String updateQuery = QueryHelper.insertQuery("Task", taskAttributes, getTaskValues(task));
        return jdbcTemplate.update(updateQuery);
    }

    @Override
    public int deleteTask(int task_ID) {
        String query = "DELETE FROM Task WHERE Task_ID = '" + task_ID + "';";
        return jdbcTemplate.update(query);
    }

    @Override
    public int updateTaskBody(Task task) {
        String query = QueryHelper.updateQuery("Task",
                taskAttributes, getTaskValues(task),
                "task_id = '" + task.getTask_ID() + "'");
        return jdbcTemplate.update(query);
    }

    @Override
    public int updateTask(int task_ID, String updateString) {
        Map<String, String> updateMap = parseUpdateString(updateString, taskAttributes, taskTypes);
        List<String> attributes = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for (Map.Entry<String,String> entry : updateMap.entrySet()) {
            attributes.add(entry.getKey());
            values.add(entry.getValue());
        }

        if (attributes.size() <= 0) {
            return 0;
        }

        String query = QueryHelper.updateQueryNoType("Task", attributes, values, "task_id = " + task_ID);
        return jdbcTemplate.update(query);
    }

    /**
     * Parse the update string. If there are duplicate attributes, only include the first one.
     * @param updateString - each attribute is delimited by "|" (%7C)
     *                     Each update should be format: attribute=value
     * @return
     */
    private Map<String, String> parseUpdateString(String updateString, List<String> validAttributes, List<String> validTypes) {
        Map<String, String> updateMap = new HashMap<>();
        String[] updates = updateString.split("\\|");
        for (int i=0; i<updates.length; i++) {
            String[] update = updates[i].trim().split("=",2);
            if (update.length == 2 && validAttributes.contains(update[0].toLowerCase())
                    && updateMap.get(update[0].toLowerCase()) == null) {
                String value = update[1];
                if (!validTypes.get(validAttributes.indexOf(update[0].toLowerCase())).equals("int")) {
                    value = "'" + value + "'";
                }
                updateMap.put(update[0], value);
            }
        }
        return updateMap;
    }

    @Override
    public List<TaskLocation> selectAllTask() {
        String sqlQuery = "SELECT Task.Task_ID, Date, Description, Num_Volunteer, Task.PostalCode, Status, Address, Username, CreateTime, City, Province " +
                "FROM PostalCode, Task " +
                "WHERE PostalCode.PostalCode = Task.PostalCode";
        return jdbcTemplate.query(sqlQuery, CustomRowMapper::TaskLocationRowMapper);
    }

    @Override
    public Task selectTaskByID(int task_ID) {
        try {
            String sqlQuery =
                    "SELECT Task_ID, Date, Description, Num_Volunteer, PostalCode, Status, Address, Username, CreateTime " +
                    "FROM Task WHERE Task_ID = " + task_ID + ";";
            return jdbcTemplate.queryForObject(sqlQuery, new Object[]{}, CustomRowMapper::TaskRowMapper);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    /**
     * Returns a list of tasks that are associated with given location.
     * @param filterConditions String that contains cities, provinces, dates(on, before after), preferences
     *                   loc=locAttribute - user input for location tab (Checks province AND city)
     *                   city=cityAttribute - only checks City in Location
     *                   prov=provAttribute - only checks Province in Location
     *                   pref=prefAttribute - checks task Preferences.pref_name (Not case sensitive)
     *                   daterange=start:end - range of two dates before and after. (If multiple, only take first)
     *
     *                   Each condition is separated by | (%7C), each condition must have and =
     *
     *                   WHERE (loc OR prov OR city) AND (pref) AND (daterange)
     *
     * @param order from GET REQUEST: Format is taskAttribute(=(desc|asc))? separated by | (%7C)
     *              If (=(desc|asc))? does not match, do not include.
     *
     * @return list of tasks that matching filter
     */
    public List<TaskLocation> selectTaskByFilter(String filterConditions, String order) {
        List<String> validOrderAttributes = new ArrayList<>();
        validOrderAttributes.addAll(taskAttributes);
        validOrderAttributes.addAll(locationAttributes);

        String orderString = parseTaskOrder(order, validOrderAttributes);
        Map<String, List<String>> filterMap = parseTaskFilterWhere(filterConditions);

        String sqlQuery =
                "SELECT Task.Task_ID, Date, Description, Num_Volunteer, Task.PostalCode, Status, Address, Username, CreateTime, City, Province " +
                "FROM PostalCode, Task " +
                "WHERE PostalCode.PostalCode = Task.PostalCode " + createSQLFromFilterMap(filterMap) + orderString;
//        return jdbcTemplate.query(sqlQuery, CustomRowMapper::TaskRowMapper);
        return jdbcTemplate.query(sqlQuery, CustomRowMapper::TaskLocationRowMapper);
    }

    /**
     * Given username, return all tasks published by given username.
     * @param username of a User.
     * @param order of sorting: Date desc, Date, Status
     * @return list of tasks associated with username.
     */
    public List<Task> selectTaskByUsername(String username, String order) {
        String sqlQuery =
                "SELECT Task_ID, Date, Description, Num_Volunteer, PostalCode, Status, Address, Username, CreateTime " +
                "FROM Task WHERE Username = " + addSingleQuotes(username) + parseTaskOrder(order, taskAttributes);
        return jdbcTemplate.query(sqlQuery, CustomRowMapper::TaskRowMapper);
    }

    private Map<String, List<String>> parseTaskFilterWhere(String filter) {
        Map<String, List<String>> filterClause = new HashMap<>();
        if (filter.length() == 0)
            return filterClause;

        String[] filters = filter.split("\\|");
        List<String> toAddLocationFilters = new ArrayList<>();
        List<String> toAddPreferenceFilters = new ArrayList<>();
        List<String> toAddDateFilter = new ArrayList<>();

        for (String f : filters) {
            String currentFilter = f.trim().toLowerCase();
            String[] filterAttribute = currentFilter.split("=", 2);
            if (filterAttribute.length >= 2 && !filterAttribute[1].isEmpty() && taskFilters.contains(filterAttribute[0])) {
                if (filterAttribute[0].equals("loc")) {
                    for (String locationAtt : locationAttributes) {
                        if (locationAtt.equals("postalcode") || locationAtt.equals("address"))
                            toAddLocationFilters.add("Task." + locationAtt + " ILIKE '" + filterAttribute[1] + "%'");
                        else
                            toAddLocationFilters.add(locationAtt + " ILIKE '" + filterAttribute[1] + "%'");
                    }
                } else if (filterAttribute[0].equals("city")) {
                    toAddLocationFilters.add("city ILIKE '" + filterAttribute[1] + "'");

                } else if (filterAttribute[0].equals("prov")) {
                    toAddLocationFilters.add("province ILIKE '" + filterAttribute[1] + "'");

                } else if (filterAttribute[0].equals("pref")) {
//                    toAddPreferenceFilters.add("pref_id = '" + filterAttribute[1] + "'");
                    toAddPreferenceFilters.add("pref_name ILIKE '" + filterAttribute[1] + "'");

                } else if (filterAttribute[0].equals("daterange")) {
                    String[] dateRange = filterAttribute[1].split(":");
                    if (dateRange.length == 2 && validDateRange(dateRange[0]) && validDateRange(dateRange[1])) {
                        if (dateRange[0].length() == 0 && dateRange[1].length() > 0) {
                            toAddDateFilter.add("Date <= '" + dateRange[1] + "'");
                        } else if (dateRange[0].length() > 0 && dateRange[1].length() == 0) {
                            toAddDateFilter.add("Date >= '" + dateRange[0] + "'");
                        } else if (dateRange[0].length() > 0 && dateRange[1].length() > 0) {
                            toAddDateFilter.add("Date BETWEEN '" + dateRange[0] + "' and '" + dateRange[1] + "'");
                        }
                    }
                }
            }
        }

        filterClause.put("location", toAddLocationFilters);
        filterClause.put("preference", toAddPreferenceFilters);
        filterClause.put("date", toAddDateFilter);

        return filterClause;
    }

    private String createSQLFromFilterMap(Map<String, List<String>> filterMap) {
        String SQL = "";
        List<String> filterLocation = filterMap.get("location");
        List<String> filterPreference = filterMap.get("preference");
        List<String> filterDate = filterMap.get("date");

        if (filterLocation != null && filterLocation.size() > 0) {
            SQL += " AND (";
            for (int i=0; i<filterLocation.size(); i++) {
                SQL += filterLocation.get(i);
                if (i < filterLocation.size()-1)
                    SQL += " OR ";
                else
                    SQL += ") ";
            }
        }

        if (filterPreference != null && filterPreference.size() > 0) {
            SQL += " AND Task.Task_ID IN (" +
                    " SELECT Task.Task_ID FROM Task, TasksHasPreference, Preference" +
                    " WHERE Task.Task_ID = TasksHasPreference.Task_ID" +
                    " AND TasksHasPreference.Pref_ID = Preference.Pref_ID " +
                    " AND (";
            for (int i=0; i<filterPreference.size(); i++) {
                SQL += filterPreference.get(i);
                if (i < filterPreference.size()-1)
                    SQL += " OR ";
                else
                    SQL += ") ";
            }
            SQL += ") ";
        }

        if (filterDate != null && filterDate.size() > 0) {
            SQL += " AND (" + filterDate.get(0) + ") ";
        }
        return SQL;
    }

    private boolean validDateRange(String date) {
        if (date.length() == 0)
            return true;
        try {
            Date.valueOf(date);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
    /**
     * Given input string, parse into the ORDER BY clause. Function only includes orders that match a column_name in
     * Task given in taskAttributes
     *
     * @param order from GET REQUEST: Format is taskAttribute(=(desc|asc))? separated by | (%7C)
     *              If (=(desc|asc))? does not match, do not include.
     * @return the SQL ORDER clause ("ORDER BY " clause) given the order section of the GET REQUEST.
     */
    private String parseTaskOrder(String order, List<String> validOrderAttributes) {
        String orderClause = "";
        if (order.length() == 0)
            return orderClause;

        String[] orders = order.split("\\|");
        List<String> toAddOrders = new ArrayList<>();
        for (String s : orders) {
            String toAdd = "";
            String currentOrder = s.trim().toLowerCase();
            String[] orderAttribute = currentOrder.split("=", 2);
            if (validOrderAttributes.contains(orderAttribute[0])) {

                if (orderAttribute[0].equals("postalcode") || orderAttribute[0].equals("address"))
                    toAdd = "Task." + orderAttribute[0].trim();
                else
                    toAdd = orderAttribute[0].trim();
                if (orderAttribute.length > 1 && (orderAttribute[1].equals("desc") || orderAttribute[1].equals("asc"))) {
                    toAdd += " " + orderAttribute[1].trim();
                }
            }
            if (toAdd.length() > 0)
                toAddOrders.add(toAdd);
        }

        if (toAddOrders.size() > 0) {
            orderClause += "ORDER BY ";
            for (int i=0; i<toAddOrders.size(); i++) {
                if (i > 0) {
                    orderClause += ", ";
                }
                orderClause += toAddOrders.get(i);
            }
        }
        return orderClause;
    }

    private List<String> getTaskValues(Task task) {
        String task_ID = Integer.toString(task.getTask_ID());
        String date = task.getDate().toString();
        String description = task.getDescription();
        String num_Volunteer = Integer.toString(task.getNum_Volunteer());
        String status = task.getStatus();
        String postalCode = task.getPostalCode();
        String address = task.getAddress();
        String username = task.getUsername();
        String createTime = task.getCreateTime().toString();

        return Arrays.asList(task_ID, date, description, num_Volunteer, status, postalCode, address, username, createTime);
    }

    private String addSingleQuotes(String input) {
        return "'" + input + "'";
    }
}
