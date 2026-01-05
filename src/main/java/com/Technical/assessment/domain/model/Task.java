package com.Technical.assessment.domain.model;

import java.util.UUID;

public class Task {
    private UUID id;
    private UUID projectId;
    private String title;
    private boolean completed;
    private boolean deleted;

    public Task(UUID id, UUID projectId, String title) {
        this.id = id;
        this.projectId = projectId;
        this.title = title;
        this.completed = false;
        this.deleted = false;
    }
    public void complete() {
        this.completed = true;
    }

    // Getters
    public UUID getId() { return id; }
    public UUID getProjectId() { return projectId; }
    public String getTitle() { return title; }
    public boolean isCompleted() { return completed; }
    public boolean isDeleted() { return deleted; }
}

