package com.Technical.assessment.application.usecase;

import com.Technical.assessment.application.port.out.*;
import com.Technical.assessment.domain.exception.DomainException;
import com.Technical.assessment.domain.model.Project;
import com.Technical.assessment.domain.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ActivateProjectUseCaseTest {

    private ProjectRepositoryPort projectRepository;
    private TaskRepositoryPort taskRepository;
    private CurrentUserPort currentUserPort;
    private AuditLogPort auditLogPort;
    private NotificationPort notificationPort;
    private ActivateProjectUseCaseImpl activateProjectUseCase;


    @BeforeEach
    void setUp() {
        projectRepository = mock(ProjectRepositoryPort.class);
        taskRepository = mock(TaskRepositoryPort.class);
        currentUserPort = mock(CurrentUserPort.class);
        auditLogPort = mock(AuditLogPort.class);
        notificationPort = mock(NotificationPort.class);

        activateProjectUseCase = new ActivateProjectUseCaseImpl(
                projectRepository, taskRepository, currentUserPort, auditLogPort, notificationPort
        );
    }

    // TEST #1: ActivateProject_WithTasks_ShouldSucceed
    // Here we see if the project with all data can be activated
    @Test
    void activateProject_WithTasks_ShouldSucceed(){
        UUID projectId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Project project = new Project(projectId, userId, "Test Project");
        Task task = new Task(UUID.randomUUID(), projectId, "Task 1");

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(currentUserPort.getCurrentUserId()).thenReturn(userId);
        when(taskRepository.findByProjectId(projectId)).thenReturn(List.of(task));


        activateProjectUseCase.activate(projectId);

        assertEquals(Project.ProjectStatus.ACTIVE, project.getStatus());
        verify(projectRepository).save(project);
        verify(auditLogPort).register(eq("PROJECT_ACTIVATED"), any());
    }

    // TEST #2: activateProject_WithoutTasks_ShouldFail
    // Here we create a task to see if the conditional (The project should fail IF don't have any task)
    @Test
    void activateProject_WithoutTasks_ShouldFail(){
        UUID projectId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        Project project = new Project(projectId, userId, "Empty Project");

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(currentUserPort.getCurrentUserId()).thenReturn(userId);
        when(taskRepository.findByProjectId(projectId)).thenReturn(Collections.emptyList());

        assertThrows(DomainException.class, () -> activateProjectUseCase.activate(projectId));
    }

    // TEST 3: ActivateProject_ByNonOwner_ShouldFail
    // In this test we can see if someone that is not the owner of the project active the project, should fail
    // because he is not the owner of the project
    @Test
    void activateProject_ByNonOwner_ShouldFail() {
        UUID projectId = UUID.randomUUID();
        UUID ownerId = UUID.randomUUID();
        UUID otherUserId = UUID.randomUUID();
        Project project = new Project(projectId, ownerId, "Private Project");

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(currentUserPort.getCurrentUserId()).thenReturn(otherUserId);

        assertThrows(DomainException.class, () -> activateProjectUseCase.activate(projectId));
    }


}
