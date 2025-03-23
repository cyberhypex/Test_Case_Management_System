package com.Test_Case_Management_System.Model;


import com.mongodb.lang.Nullable;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document("TestCaseModel")
public class TestModel {
    @Id
    private String id;

    private String  title;

    @Nullable
    private String description;

    @Field("priority")
    private Priority priority;

    @Field("status")
    private Status status;

   @CreatedDate
   @Field("createdAt")
    private Instant createdAt;

   @LastModifiedDate
   @Field("updatedAt")
    private Instant updatedAt;


    public TestModel(String title, @Nullable String description, Priority priority, Status status) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }
}
