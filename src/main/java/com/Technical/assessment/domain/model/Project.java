package com.Technical.assessment.domain.model;

import com.Technical.assessment.domain.exception.DomainException;

import java.util.UUID;

public class Project {
    private UUID id;
    private UUID ownerId;
    private String name;
    private ProjectStatus status;
    private boolean deleted;

    public enum ProjectStatus { DRAFT, ACTIVE }

    // Constructor de 5 parámetros (Esto arreglará el error de tu imagen)
    public Project(UUID id, UUID ownerId, String name, ProjectStatus status, boolean deleted) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.status = status;
        this.deleted = deleted;
    }
    public void activate() {
        // Regla implícita: No tiene sentido activar algo ya activado
        if (this.status == ProjectStatus.ACTIVE) {
            throw new DomainException("El proyecto ya se encuentra activo");
        }
        this.status = ProjectStatus.ACTIVE;
    }


    // Getters
    public UUID getId() { return id; }
    public UUID getOwnerId() { return ownerId; }
    public String getName() { return name; }
    public ProjectStatus getStatus() { return status; }
    public boolean isDeleted() { return deleted; }
}