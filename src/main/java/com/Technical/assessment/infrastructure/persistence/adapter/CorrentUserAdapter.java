package com.Technical.assessment.infrastructure.persistence.adapter;


import com.Technical.assessment.application.port.out.CurrentUserPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CorrentUserAdapter  implements CurrentUserPort {

    private static final UUID MOCK_USER_ID = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");

    @Override
    public UUID getCurrentUserId() {
        return MOCK_USER_ID;
    }

}
