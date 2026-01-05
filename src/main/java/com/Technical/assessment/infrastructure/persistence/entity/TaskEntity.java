package com.Technical.assessment.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@Getter @Setter
public class TaskEntity {
    @Id
    private UUID id;
    private UUID projectId;
    private String title;
    private boolean completed;
    private boolean deleted;


}