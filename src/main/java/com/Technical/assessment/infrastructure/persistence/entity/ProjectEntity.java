package com.Technical.assessment.infrastructure.persistence.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "projects")
@Getter @Setter
public class ProjectEntity {
    @Id
    private UUID id;

    private UUID ownerId;
    private String name;
    private String status;
    private boolean deleted;
}