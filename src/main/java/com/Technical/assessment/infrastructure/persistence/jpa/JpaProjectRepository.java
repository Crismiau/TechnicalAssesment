package com.Technical.assessment.infrastructure.persistence.jpa;

import com.Technical.assessment.infrastructure.persistence.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaProjectRepository extends JpaRepository<ProjectEntity, UUID> {
    List<ProjectEntity> findByOwnerId(UUID ownerId);
}