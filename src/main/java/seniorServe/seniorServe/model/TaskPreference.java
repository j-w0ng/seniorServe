package seniorServe.seniorServe.model;

public class TaskPreference {
    public int task_ID;
    public int pref_ID;

    public TaskPreference(int task_ID, int pref_ID) {
        this.task_ID = task_ID;
        this.pref_ID = pref_ID;
    }

    public int getTask_ID() {
        return task_ID;
    }

    public int getPref_ID() {
        return pref_ID;
    }
}
