package com.Technical.assessment.infrastructure.persistence.jpa;

import com.Technical.assessment.infrastructure.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaTaskRepository extends JpaRepository<TaskEntity, UUID> {
    List<TaskEntity> findByProjectId(UUID projectId);
}