package com.Technical.assessment.application.usecase;

import com.Technical.assessment.application.port.in.CreateProjectUseCase; // DEBES IMPORTAR EL IN PORT
import com.Technical.assessment.application.port.out.ProjectRepositoryPort;
import com.Technical.assessment.application.port.out.CurrentUserPort;
import com.Technical.assessment.domain.model.Project;

import java.util.UUID;

public class CreateProjectUseCaseImpl implements CreateProjectUseCase {

    private final ProjectRepositoryPort projectRepository;
    private final CurrentUserPort currentUserPort;

    // Constructor para inyectar los puertos de salida
    public CreateProjectUseCaseImpl(ProjectRepositoryPort projectRepository, CurrentUserPort currentUserPort) {
        this.projectRepository = projectRepository;
        this.currentUserPort = currentUserPort;
    }

    @Override
    public void create(String name) {
        UUID ownerId = currentUserPort.getCurrentUserId();

        // Creamos la instancia del modelo de dominio
        Project project = new Project(UUID.randomUUID(), ownerId, name);

        // Llamamos al puerto de salida (Opción A: void)
        projectRepository.save(project);
    }
}