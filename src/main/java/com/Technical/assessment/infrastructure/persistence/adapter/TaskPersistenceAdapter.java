package com.Technical.assessment.infrastructure.persistence.adapter;


import com.Technical.assessment.application.port.out.TaskRepositoryPort;
import com.Technical.assessment.domain.model.Task;
import com.Technical.assessment.infrastructure.persistence.jpa.JpaTaskRepository;
import com.Technical.assessment.infrastructure.persistence.mapper.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TaskPersistenceAdapter implements TaskRepositoryPort {

    private final JpaTaskRepository jpaRepository;

    public TaskPersistenceAdapter(JpaTaskRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Task task) {
        jpaRepository.save(TaskMapper.toEntity(task));
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return jpaRepository.findById(id).map(TaskMapper::toDomain);
    }

    @Override
    public List<Task> findByProjectId(UUID projectId) {
        return jpaRepository.findByProjectId(projectId).stream()
                .map(TaskMapper::toDomain)
                .collect(Collectors.toList());
    }

}
