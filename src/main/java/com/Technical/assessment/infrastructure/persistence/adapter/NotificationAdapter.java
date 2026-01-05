package com.Technical.assessment.infrastructure.persistence.adapter;

import com.Technical.assessment.application.port.out.NotificationPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NotificationAdapter implements NotificationPort {

    private static final Logger logger = LoggerFactory.getLogger(NotificationAdapter.class);

    @Override
    public void notify(String message) {
      // And here we send to notify in accordance with the business rule number 6
        // Ruler #6: "Project activation and task completion must generate a notification".
        logger.info("NOTIFICATION SENT: {}", message);
    }
}