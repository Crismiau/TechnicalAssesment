package com.Technical.assessment.infrastructure.persistence.adapter;

import com.Technical.assessment.application.port.out.ProjectRepositoryPort;
import com.Technical.assessment.domain.model.Project;
import com.Technical.assessment.infrastructure.persistence.entity.ProjectEntity;
import com.Technical.assessment.infrastructure.persistence.jpa.JpaProjectRepository;
import com.Technical.assessment.infrastructure.persistence.mapper.ProjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProjectPersistenceAdapter implements ProjectRepositoryPort {

    private final JpaProjectRepository jpaRepository;

    public ProjectPersistenceAdapter(JpaProjectRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Project save(Project project) {
        // 1. Mapeamos el modelo de dominio a la entidad de JPA
        ProjectEntity entity = ProjectMapper.toEntity(project);

        // 2. Guardamos y capturamos la entidad persistida (JPA llena los campos aquí)
        ProjectEntity savedEntity = jpaRepository.save(entity);

        // 3. Mapeamos la entidad guardada de vuelta al modelo de dominio para devolverla
        return ProjectMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Project> findById(UUID id) {
        return jpaRepository.findById(id).map(ProjectMapper::toDomain);
    }

    @Override
    public List<Project> findByOwnerId(UUID ownerId) {
        List<ProjectEntity> entities = jpaRepository.findByOwnerId(ownerId);

        return entities.stream()
                .map(ProjectMapper::toDomain)
                .collect(Collectors.toList());
    }
}
