package com.Technical.assessment.application.usecase;

import com.Technical.assessment.application.port.in.ActivateProjectUseCase;
import com.Technical.assessment.application.port.out.*;
import com.Technical.assessment.domain.exception.DomainException;
import com.Technical.assessment.domain.model.Project;
import com.Technical.assessment.domain.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class ActivateProjectUseCaseImpl implements ActivateProjectUseCase {

    private final ProjectRepositoryPort projectRepository;
    private final TaskRepositoryPort taskRepository;
    private final CurrentUserPort currentUserPort;
    private final AuditLogPort auditLogPort;
    private final NotificationPort notificationPort;

    public ActivateProjectUseCaseImpl(ProjectRepositoryPort projectRepository,
                                      TaskRepositoryPort taskRepository,
                                      CurrentUserPort currentUserPort,
                                      AuditLogPort auditLogPort,
                                      NotificationPort notificationPort) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.currentUserPort = currentUserPort;
        this.auditLogPort = auditLogPort;
        this.notificationPort = notificationPort;
    }

    @Override
    public void activate(UUID projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new DomainException("Proyecto no encontrado"));

    // Here is the second Business Logic
        // #2 Only the owner can modify
        if (!project.getOwnerId().equals(currentUserPort.getCurrentUserId())) {
            throw new DomainException("No tienes permiso para activar este proyecto");
        }
    // Here is the first Business Logic
        // #1 A proyecto can be activated only if has at least one task active
        List<Task> tasks = taskRepository.findByProjectId(projectId);
        boolean hasActiveTasks = tasks.stream().anyMatch(t -> !t.isDeleted());

        if (!hasActiveTasks) {
            throw new DomainException("Un proyecto solo puede activarse si tiene al menos una tarea activa");
        }

        project.activate(); // Here it change the status to active at the domain
        projectRepository.save(project);

    // Here is the rule five and six
        // #5 Project activation and task completion must generate an audit trail.
        // #6 Project activation and task completion must generate a notification.
        auditLogPort.register("PROJECT_ACTIVATED", project.getId());
        notificationPort.notify("Proyecto activado: " + project.getName());
}
}
