package com.Technical.assessment.application.usecase;

import com.Technical.assessment.application.port.in.CompleteTaskUseCase;
import com.Technical.assessment.application.port.out.*;
import com.Technical.assessment.domain.exception.DomainException;
import com.Technical.assessment.domain.model.Project;
import com.Technical.assessment.domain.model.Task;

import java.util.UUID;

public class CompleteTaskUseCaseImpl implements CompleteTaskUseCase {

    private final TaskRepositoryPort taskRepository;
    private final ProjectRepositoryPort projectRepository;
    private final CurrentUserPort currentUserPort;
    private final AuditLogPort auditLogPort;
    private final NotificationPort notificationPort;

    public CompleteTaskUseCaseImpl(TaskRepositoryPort taskRepository,
                                   ProjectRepositoryPort projectRepository,
                                   CurrentUserPort currentUserPort,
                                   AuditLogPort auditLogPort,
                                   NotificationPort notificationPort) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.currentUserPort = currentUserPort;
        this.auditLogPort = auditLogPort;
        this.notificationPort = notificationPort;
    }


    @Override
    public void complete(UUID taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new DomainException("Tarea no encontrada"));

        // Third Rule
        // #3 A completed task cannot be modified

        if (task.isCompleted()) {
            throw new DomainException("La tarea ya se encuentra completada");
        }

        // Validate ownership through the project
        Project project = projectRepository.findById(task.getProjectId())
                .orElseThrow(() -> new DomainException("Proyecto no encontrado"));

        if (!project.getOwnerId().equals(currentUserPort.getCurrentUserId())) {
            throw new DomainException("No tienes permiso para modificar esta tarea");
        }

        task.complete();
        taskRepository.save(task);

        // Here is the rule five and six
        // #5 Project activation and task completion must generate an audit trail.
        // #6 Project activation and task completion must generate a notification.
        auditLogPort.register("TASK_COMPLETED", task.getId());
        notificationPort.notify("Tarea completada: " + task.getTitle());

    }
}

