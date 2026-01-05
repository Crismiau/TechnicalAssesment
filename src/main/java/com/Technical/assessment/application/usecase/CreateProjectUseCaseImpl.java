package com.Technical.assessment.application.usecase;

import com.Technical.assessment.application.port.in.CreateProjectUseCase;
import com.Technical.assessment.application.port.out.ProjectRepositoryPort;
import com.Technical.assessment.application.port.out.CurrentUserPort;
import com.Technical.assessment.domain.model.Project;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateProjectUseCaseImpl implements CreateProjectUseCase {

    private final ProjectRepositoryPort projectRepository;
    private final CurrentUserPort currentUserPort;

    public CreateProjectUseCaseImpl(ProjectRepositoryPort projectRepository, CurrentUserPort currentUserPort) {
        this.projectRepository = projectRepository;
        this.currentUserPort = currentUserPort;
    }

    @Override
    public Project createProject(Project project) {
        UUID currentUserId = currentUserPort.getCurrentUserId();

        Project newProject = new Project(
                UUID.randomUUID(),
                currentUserId,
                project.getName(),
                Project.ProjectStatus.DRAFT,
                false
        );

        // Retornamos el resultado del guardado
        return projectRepository.save(newProject);
    }
}