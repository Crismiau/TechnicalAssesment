package com.Technical.assessment.application.port.in;

import com.Technical.assessment.domain.model.Task;
import java.util.UUID;

public interface CreateTaskUseCase {
    void createTask(UUID projectId, Task task);
}