package co.edu.udec.taskmgr.domain.entidades;

import co.edu.udec.taskmgr.domain.enums.TaskStatus;

public class Task {

    private long id; 
    private String title;
    private String description;
    private TaskStatus status;
    private String userEmail;


    public Task(long id, String title, String description, TaskStatus status, String userEmail) {
        this.id = id;
        this.title = title;
        this.description = description == null ? "" : description;
        this.status = status;
        this.userEmail = userEmail;
    }

   
    public Task(String title, String description, TaskStatus status, String userEmail) {
        this.title = title;
        this.description = description == null ? "" : description;
        this.status = status;
        this.userEmail = userEmail;
    }


    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public TaskStatus getStatus() { return status; }

    public void setStatus(TaskStatus status) { this.status = status; }

    public String getUserEmail() { return userEmail; }

    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
}