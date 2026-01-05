package com.Technical.assessment.infrastructure.persistence.mapper;

import com.Technical.assessment.domain.model.Task;
import com.Technical.assessment.infrastructure.persistence.entity.TaskEntity;

public class TaskMapper {

    public static TaskEntity toEntity(Task domain) {
        TaskEntity entity = new TaskEntity();
        entity.setId(domain.getId());
        entity.setProjectId(domain.getProjectId());
        entity.setTitle(domain.getTitle());
        entity.setCompleted(domain.isCompleted()); // Mapeo directo
        entity.setDeleted(domain.isDeleted());
        return entity;
    }

    public static Task toDomain(TaskEntity entity) {
        // Aquí usamos el constructor de 5 parámetros que creaste
        return new Task(
                entity.getId(),
                entity.getProjectId(),
                entity.getTitle(),
                entity.isCompleted(),
                entity.isDeleted()
        );
    }
}
