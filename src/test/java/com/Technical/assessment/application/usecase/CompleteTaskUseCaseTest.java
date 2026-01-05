package com.Technical.assessment.application.usecase;

import com.Technical.assessment.application.port.out.*;
import com.Technical.assessment.domain.exception.DomainException;
import com.Technical.assessment.domain.model.Project;
import com.Technical.assessment.domain.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CompleteTaskUseCaseTest {
    private TaskRepositoryPort taskRepository;
    private ProjectRepositoryPort projectRepository;
    private CurrentUserPort currentUserPort;
    private AuditLogPort auditLogPort;
    private NotificationPort notificationPort;
    private CompleteTaskUseCaseImpl completeTaskUseCase;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepositoryPort.class);
        projectRepository = mock(ProjectRepositoryPort.class);
        currentUserPort = mock(CurrentUserPort.class);
        auditLogPort = mock(AuditLogPort.class);
        notificationPort = mock(NotificationPort.class);

        // Ahora el constructor coincide con la clase
        completeTaskUseCase = new CompleteTaskUseCaseImpl(
                taskRepository, projectRepository, currentUserPort, auditLogPort, notificationPort
        );
    }

    // TEST #4: CompleteTask_AlreadyCompleted_ShouldFail
    @Test
    void completeTask_AlreadyCompleted_ShouldFail(){
        // 1. Define ID's
        UUID taskId = UUID.randomUUID();
        Task task = new Task(taskId, UUID.randomUUID(), "Done Task");
        task.complete(); // Here we instantiate the default status "completed" for the test

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        assertThrows(DomainException.class, () -> completeTaskUseCase.complete(taskId));
    }


    // TEST #5:CompleteTask_ShouldGenerateAuditAndNotification
    @Test
    void completeTask_ShouldGenerateAuditAndNotification() {
        // 1. Define ID's
        UUID taskId = UUID.randomUUID();
        UUID projectId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        // 2. Create domain objects for the test
        Task task = new Task(taskId, projectId, "Pending Task");
        Project project = new Project(projectId, userId, "Test Project");

        // 3. CONFIGURE MOCKS
        // When searching for the task, return it
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        // When I search for the project for that task, have it return.
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        // When prompted, the current user should be the project owner.        when(currentUserPort.getCurrentUserId()).thenReturn(userId);

        // 4. Execute
        completeTaskUseCase.complete(taskId);

        // 5. VErify
        assertTrue(task.isCompleted());
        verify(auditLogPort).register(eq("TASK_COMPLETED"), eq(taskId));
        verify(notificationPort).notify(anyString());
    }

}
