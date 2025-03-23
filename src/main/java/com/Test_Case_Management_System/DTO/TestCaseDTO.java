package com.Test_Case_Management_System.DTO;

import com.Test_Case_Management_System.Model.Priority;
import com.Test_Case_Management_System.Model.Status;

import java.time.Instant;

public class TestCaseDTO {


    private String id;
    private String title;
    private String description;
    private Priority priority;
    private Status status;
    private Instant createdAt;
    private Instant updatedAt;
    public TestCaseDTO(String id, String title, String description, Priority priority, Status status, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public String getId() {
        return id;
    }



    public String getTitle() {
        return title;
    }



    public String getDescription() {
        return description;
    }



    public Priority getPriority() {
        return priority;
    }



    public Status getStatus() {
        return status;
    }



    public Instant getCreatedAt() {
        return createdAt;
    }



    public Instant getUpdatedAt() {
        return updatedAt;
    }


}
