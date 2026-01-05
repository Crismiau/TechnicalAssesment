package com.Technical.assessment.application.port.in;

import com.Technical.assessment.domain.model.Project;

public interface CreateProjectUseCase {
    // Cambiamos de void a Project
    Project createProject(Project project);
}