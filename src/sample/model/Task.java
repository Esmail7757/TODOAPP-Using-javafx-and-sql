package sample.model;

import java.sql.Timestamp;

public class Task {
    public Task(){

    }
    public Task(String task, Timestamp datecreated, String description) {
        this.task = task;
        this.datecreated = datecreated;
        this.description = description;
    }

    private int userid;
    private String task;
    private Timestamp datecreated;
    private String description;
    private int taskId;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Timestamp getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getUserId() {
        return userid;
    }

    public void setUserId(int userId) {
        this.userid = userId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
