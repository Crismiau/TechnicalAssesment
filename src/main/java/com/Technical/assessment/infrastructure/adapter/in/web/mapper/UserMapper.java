package com.Technical.assessment.infrastructure.adapter.in.web.mapper;

import com.Technical.assessment.application.dto.LoginRequest;
import com.Technical.assessment.domain.model.User;
import com.Technical.assessment.infrastructure.persistence.entity.UserEntity;

public class UserMapper {
    public static LoginRequest toDTO(User domain) {
        LoginRequest DTO = new LoginRequest(domain.getEmail(),domain.getPassword());
        return DTO;
    }

    public static User toDomain(LoginRequest dto) {
        // CORRECCIÓN: Ahora pasamos los 5 parámetros (incluyendo el Boolean)
        User user = new User();
        user.setEmail(dto.email());
        user.setPassword(dto.password());

        return user;
    }
}
