package com.Technical.assessment.infrastructure.persistence.adapter;

import com.Technical.assessment.application.port.out.AuditLogPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuditLogAdapter implements AuditLogPort {

    private static final Logger logger = LoggerFactory.getLogger(AuditLogAdapter.class);

    @Override
    public void register(String action, UUID entityId) {

        // Here we create the logger function to se the auditory and see who do something
        // In this method we can agree with the business logic:
        // Ruler #5: "Project activation and task completion must generate an audit trail".

        logger.info("AUDIT TRAIL | Action: {} | Entity ID: {} | Timestamp: {}",
                action, entityId, java.time.LocalDateTime.now());
    }
}