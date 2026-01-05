package com.Technical.assessment.application.port.in;

import java.util.UUID;

public interface CreateTaskUseCase {
    void create(UUID projectId, String title);
}