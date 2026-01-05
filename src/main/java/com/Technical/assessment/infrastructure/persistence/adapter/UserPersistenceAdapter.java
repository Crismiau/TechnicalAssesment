package com.Technical.assessment.infrastructure.persistence.adapter;

import com.Technical.assessment.application.port.out.UserRepositoryPort;
import com.Technical.assessment.domain.model.User;
import com.Technical.assessment.infrastructure.persistence.entity.UserEntity;
import com.Technical.assessment.infrastructure.persistence.jpa.JpaUserRepository;
import com.Technical.assessment.infrastructure.persistence.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;

    public UserPersistenceAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public void save(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        jpaUserRepository.save(entity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email)
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username)
                .map(UserMapper::toDomain);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaUserRepository.existsByUsername(username);
    }

    @Override
    public Optional<User> findById(java.util.UUID id) {
        return jpaUserRepository.findById(id)
                .map(UserMapper::toDomain);
    }
}