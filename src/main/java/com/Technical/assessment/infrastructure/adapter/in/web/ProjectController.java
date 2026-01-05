package com.Technical.assessment.infrastructure.adapter.in.web;

import com.Technical.assessment.application.port.in.CreateProjectUseCase;
import com.Technical.assessment.application.port.in.ActivateProjectUseCase;
import com.Technical.assessment.domain.model.Project;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
@Tag(name = "Projects", description = "Endpoints para la gestión de proyectos")
public class ProjectController {

    private final CreateProjectUseCase createProjectUseCase;
    private final ActivateProjectUseCase activateProjectUseCase;

    public ProjectController(CreateProjectUseCase createProjectUseCase,
                             ActivateProjectUseCase activateProjectUseCase) {
        this.createProjectUseCase = createProjectUseCase;
        this.activateProjectUseCase = activateProjectUseCase;
    }

    @Operation(summary = "Crear un nuevo proyecto", description = "Registra un proyecto en la base de datos")
    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        Project created = createProjectUseCase.createProject(project);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Activar un proyecto", description = "Cambia el estado del proyecto a activo mediante su UUID")
    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable UUID id) {
        activateProjectUseCase.activate(id);
        return ResponseEntity.noContent().build();
    }
}