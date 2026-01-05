package com.Technical.assessment.application.port.out;

import java.util.UUID;

public interface AuditLogPort {
    void register(String action, UUID entityId);
}
