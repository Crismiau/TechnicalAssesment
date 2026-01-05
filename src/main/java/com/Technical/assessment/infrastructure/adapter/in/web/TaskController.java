package com.Technical.assessment.infrastructure.adapter.in.web;

import com.Technical.assessment.application.port.in.CompleteTaskUseCase;
import com.Technical.assessment.application.port.in.CreateTaskUseCase;
import com.Technical.assessment.domain.model.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@Tag(name = "Tasks", description = "Endpoints para la gestión de tareas")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final CompleteTaskUseCase completeTaskUseCase;

    public TaskController(CreateTaskUseCase createTaskUseCase, CompleteTaskUseCase completeTaskUseCase) {
        this.createTaskUseCase = createTaskUseCase;
        this.completeTaskUseCase = completeTaskUseCase;
    }

    @Operation(summary = "Crear tarea", description = "Crea una tarea asociada a un proyecto específico")
    @PostMapping("/projects/{projectId}/tasks")
    public ResponseEntity<Void> createTask(
            @PathVariable UUID projectId,
            @RequestBody Task taskRequest) {
        createTaskUseCase.createTask(projectId, taskRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Completar tarea", description = "Marca una tarea como completada")
    @PatchMapping("/tasks/{id}/complete")
    public ResponseEntity<Void> completeTask(@PathVariable UUID id) {
        completeTaskUseCase.complete(id);
        return ResponseEntity.noContent().build();
    }
}