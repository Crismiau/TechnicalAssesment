package com.Technical.assessment.application.port.in;

import java.util.UUID;

public interface CompleteTaskUseCase {
    void complete(UUID taskId);
}