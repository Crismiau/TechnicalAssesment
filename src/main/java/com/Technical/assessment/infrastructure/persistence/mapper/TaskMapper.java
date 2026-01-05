package com.Technical.assessment.infrastructure.persistence.mapper;

import com.Technical.assessment.domain.model.Task;
import com.Technical.assessment.infrastructure.persistence.entity.TaskEntity;

public class TaskMapper {

    public static TaskEntity toEntity(Task domain) {
        TaskEntity entity = new TaskEntity();
        entity.setId(domain.getId());
        entity.setProjectId(domain.getProjectId());
        entity.setTitle(domain.getTitle());
        entity.setStatus(domain.isCompleted() ? "COMPLETED" : "PENDING");

        entity.setDeleted(domain.isDeleted());
        return entity;
    }

    public static Task toDomain(TaskEntity entity) {
        Task task = new Task(entity.getId(), entity.getProjectId(), entity.getTitle());

        if ("COMPLETED".equals(entity.getStatus())) {
            task.complete();
        }

        return task;
    }
}
