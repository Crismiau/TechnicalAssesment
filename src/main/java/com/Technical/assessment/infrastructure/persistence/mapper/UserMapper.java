package com.Technical.assessment.infrastructure.persistence.mapper;

import com.Technical.assessment.domain.model.User;
import com.Technical.assessment.infrastructure.persistence.entity.UserEntity;

public class UserMapper {
    public static UserEntity toEntity(User domain) {
        UserEntity entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setEmail(domain.getEmail());
        entity.setUsername(domain.getUsername());
        entity.setPassword(domain.getPassword());
        entity.setDeleted(domain.getDeleted()); // Mapear el nuevo campo
        return entity;
    }

    public static User toDomain(UserEntity entity) {
        // CORRECCIÓN: Ahora pasamos los 5 parámetros (incluyendo el Boolean)
        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getDeleted() // El 5to parámetro que faltaba
        );
    }
}