package com.Technical.assessment.infrastructure.persistence.mapper;

import com.Technical.assessment.domain.model.User;
import com.Technical.assessment.infrastructure.persistence.entity.UserEntity;

public class UserMapper {
    public static UserEntity toEntity(User domain) {
        UserEntity entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setEmail(domain.getEmail());
        entity.setUsername(domain.getUsername()); // Agregado
        entity.setPassword(domain.getPassword()); // Agregado
        return entity;
    }
    public static User toDomain(UserEntity entity) {
        // CORRECCIÓN: Ahora pasamos los 4 parámetros que pide el constructor de tu dominio
        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword()
        );
    }
}
