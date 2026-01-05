package com.Technical.assessment.application.dto;

import com.Technical.assessment.application.dto.AuthResponse;
import com.Technical.assessment.application.dto.LoginRequest;
import com.Technical.assessment.domain.model.User;

public interface AuthUseCase {
    AuthResponse login(LoginRequest request);
    void register(User user);
}