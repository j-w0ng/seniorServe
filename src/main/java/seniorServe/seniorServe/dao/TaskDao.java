package seniorServe.seniorServe.dao;

import seniorServe.seniorServe.model.Task;

import java.sql.Date;
import java.util.List;

public interface TaskDao {

    int insertTask(Task task);

    int deleteTask(int task_ID);

    int updateTask(int task_ID, Date date, String description, int num_Volunteer, String status, String postalCode,
                   String address, String username, Date createTime);

    List<Task> selectAllTask();

    Task selectTaskByID(int task_ID);
}
