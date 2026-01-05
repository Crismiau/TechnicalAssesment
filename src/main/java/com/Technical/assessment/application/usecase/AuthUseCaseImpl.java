package com.Technical.assessment.application.usecase;

import com.Technical.assessment.application.dto.AuthResponse;
import com.Technical.assessment.application.dto.LoginRequest;
import com.Technical.assessment.application.port.out.UserRepositoryPort;
import com.Technical.assessment.domain.model.User;
import com.Technical.assessment.infrastructure.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Technical.assessment.application.port.in.AuthUseCase;

import java.util.UUID;

@Service
public class AuthUseCaseImpl implements AuthUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthUseCaseImpl(UserRepositoryPort userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(user.getId(), user.getEmail());
        return new AuthResponse(token);
    }


    @Override
    public void register(User user) {
        // 1. Encriptar contraseña (si ya tienes el encoder)
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        // 2. Crear la instancia de dominio con los 5 parámetros requeridos
        User newUser = new User(
                UUID.randomUUID(),    // Generamos el ID
                user.getUsername(),   // Nombre de usuario
                user.getEmail(),      // Email
                encodedPassword,      // Contraseña encriptada
                false                 // EL QUINTO PARÁMETRO: deleted = false
        );

        // 3. Guardar a través del puerto
        userRepository.save(newUser);
    }

}