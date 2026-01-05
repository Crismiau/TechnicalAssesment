package com.Technical.assessment.infrastructure.adapter.in.web;

import com.Technical.assessment.application.dto.AuthResponse;
import com.Technical.assessment.application.dto.LoginRequest;
import com.Technical.assessment.application.port.in.AuthUseCase;
import com.Technical.assessment.infrastructure.adapter.in.web.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints para registro y autenticación")
public class AuthController {

    private final AuthUseCase authUseCase;

    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @Operation(summary = "Registro de usuario", description = "Crea un nuevo usuario en el sistema")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequest user) {
        authUseCase.register(UserMapper.toDomain(user));
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }

    @Operation(summary = "Inicio de sesión", description = "Autentica al usuario y devuelve un token JWT")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authUseCase.login(request));
    }
}