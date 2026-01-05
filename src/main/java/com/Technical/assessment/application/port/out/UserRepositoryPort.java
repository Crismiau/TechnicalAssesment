package com.Technical.assessment.application.port.out;

import com.Technical.assessment.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    void save(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    Optional<User> findById(UUID id);
}