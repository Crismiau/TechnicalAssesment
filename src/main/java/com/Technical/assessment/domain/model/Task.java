package com.Technical.assessment.domain.model;

import java.util.UUID;

public class Task {
    private UUID id;
    private UUID projectId;
    private String title;
    private boolean completed;
    private boolean deleted;

    // Constructor de 5 parámetros (Requerido por el error de compilación)
    public Task(UUID id, UUID projectId, String title, boolean completed, boolean deleted) {
        this.id = id;
        this.projectId = projectId;
        this.title = title;
        this.completed = completed;
        this.deleted = deleted;
    }

    // Método de negocio (Soluciona el error "cannot find symbol: method complete()")
    public void complete() {
        this.completed = true;
    }

    // Getters necesarios para el Mapper y Use Cases
    public UUID getId() { return id; }
    public UUID getProjectId() { return projectId; }
    public String getTitle() { return title; }
    public boolean isCompleted() { return completed; }
    public boolean isDeleted() { return deleted; }
}