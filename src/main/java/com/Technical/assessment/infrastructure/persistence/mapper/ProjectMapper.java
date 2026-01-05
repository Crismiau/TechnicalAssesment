package com.Technical.assessment.infrastructure.persistence.mapper;

import com.Technical.assessment.domain.model.Project;
import com.Technical.assessment.infrastructure.persistence.entity.ProjectEntity;

public class ProjectMapper {
    public static ProjectEntity toEntity(Project domain) {
        ProjectEntity entity = new ProjectEntity();
        entity.setId(domain.getId());
        entity.setOwnerId(domain.getOwnerId());
        entity.setName(domain.getName());
        entity.setStatus(domain.getStatus().name());
        return entity;
    }
    public static Project toDomain(ProjectEntity entity) {
        return new Project(
                entity.getId(),
                entity.getOwnerId(),
                entity.getName(),
                Project.ProjectStatus.valueOf(entity.getStatus()), // O el mapeo que uses para el enum
                entity.isDeleted()
        );
    }
}
