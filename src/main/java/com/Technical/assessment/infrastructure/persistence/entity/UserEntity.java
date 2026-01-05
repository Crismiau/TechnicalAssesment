package com.Technical.assessment.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter @Setter
public class UserEntity {
    @Id
    private UUID id;

    private String username;

    @Column(unique = true)
    private String email;

    private Boolean deleted = false;
    private String password;
    private String role;
}