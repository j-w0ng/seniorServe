package seniorServe.seniorServe.dao;

import java.util.List;

public class QueryHelper
{
    /** TODO: This method needs to be able to recognize ' or " in the string and fix accordingly.
     *   Alternatively, make sure whenever we are inserting data, use '' instead of ')
     */
    public static String insertQuery(String table, List<String> attributes, List<String> values)
    {
        StringBuilder query = new StringBuilder("INSERT INTO " + table + "(");

        for (int i = 0; i < attributes.size(); i++)
        {
            query.append(attributes.get(i).replaceAll("'", "''"));
            if (i != attributes.size() - 1)
            {
                query.append(", ");
            }
        }
        query.append(") VALUES (");

        for (int i = 0; i < values.size(); i++)
        {
            query.append("'");
            query.append(values.get(i).replaceAll("'", "''"));
            query.append("'");
            if (i != values.size() - 1)
            {
                query.append(", ");
            }
        }

        query.append(");");

        return query.toString();
    }

    public static String updateQuery(String table, List<String> attributes, List<String> values, String whereCondition)
    {
        StringBuilder query = new StringBuilder("UPDATE " + table + " SET ");

        for (int i = 0; i < attributes.size(); i++)
        {
            query.append(attributes.get(i).replaceAll("'", "''"));
            query.append("=");
            query.append("'");
            query.append(values.get(i).replaceAll("'", "''"));
            query.append("'");

            if (i != attributes.size() - 1)
            {
              query.append(", ");
            }
        }

        query.append(" WHERE ");
        query.append(whereCondition);
        query.append(";");

        return query.toString();
    }

    public static String updateQueryNoType(String table, List<String> attributes, List<String> values, String whereCondition)
    {
        StringBuilder query = new StringBuilder("UPDATE " + table + " SET ");

        for (int i = 0; i < attributes.size(); i++)
        {
            query.append(attributes.get(i).replaceAll("'", "''"));
            query.append("=");
            query.append(values.get(i).replaceAll("'", "''"));

            if (i != attributes.size() - 1)
            {
                query.append(", ");
            }
        }

        query.append(" WHERE ");
        query.append(whereCondition);
        query.append(";");

        return query.toString();
    }
}
