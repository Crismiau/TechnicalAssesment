package com.Technical.assessment.application.usecase;

import com.Technical.assessment.application.port.in.CreateTaskUseCase;
import com.Technical.assessment.application.port.out.TaskRepositoryPort;
import com.Technical.assessment.application.port.out.ProjectRepositoryPort;
import com.Technical.assessment.application.port.out.CurrentUserPort;
import com.Technical.assessment.domain.model.Task;
import com.Technical.assessment.domain.exception.DomainException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateTaskUseCaseImpl implements CreateTaskUseCase {

    private final TaskRepositoryPort taskRepository;
    private final ProjectRepositoryPort projectRepository;
    private final CurrentUserPort currentUserPort;

    public CreateTaskUseCaseImpl(TaskRepositoryPort taskRepository,
                                 ProjectRepositoryPort projectRepository,
                                 CurrentUserPort currentUserPort) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.currentUserPort = currentUserPort;
    }

    @Override
    public void createTask(UUID projectId, Task task) {
        // Validar que el proyecto existe y que el usuario es el dueño
        var project = projectRepository.findById(projectId)
                .orElseThrow(() -> new DomainException("Proyecto no encontrado"));

        if (!project.getOwnerId().equals(currentUserPort.getCurrentUserId())) {
            throw new DomainException("No tienes permiso para agregar tareas a este proyecto");
        }

        // Crear la tarea vinculada
        Task newTask = new Task(
                UUID.randomUUID(),
                projectId,
                task.getTitle(),
                false, // completed (inicialmente falso)
                false  // deleted (inicialmente falso)
        );

        taskRepository.save(newTask);
    }
}