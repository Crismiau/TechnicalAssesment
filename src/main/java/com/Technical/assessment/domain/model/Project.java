package com.Technical.assessment.domain.model;

import java.util.UUID;

public class Project {
    private UUID id;
    private UUID ownerId;
    private String name;
    private ProjectStatus status;
    private boolean deleted;

    public enum ProjectStatus { DRAFT, ACTIVE }

    public Project(UUID id, UUID ownerId, String name) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.status = ProjectStatus.DRAFT;
        this.deleted = false;
    }

    // Business Logic, A proyect can be activated only if has at least one task active
    public void activate() {
        this.status = ProjectStatus.ACTIVE;
    }

    public UUID getId() { return id; }
    public UUID getOwnerId() { return ownerId; }
    public String getName() { return name; }
    public ProjectStatus getStatus() { return status; }
    public boolean isDeleted() { return deleted; }

}
