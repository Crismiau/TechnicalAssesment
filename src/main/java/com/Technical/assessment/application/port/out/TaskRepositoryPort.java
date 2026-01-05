package com.Technical.assessment.application.port.out;
import com.Technical.assessment.domain.model.Task;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepositoryPort {
    void save(Task task);
    Optional<Task> findById(UUID id);
    List<Task> findByProjectId(UUID projectId);
}
