package com.Technical.assessment.domain.model;

import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String email;
    private String password; // In the domain it's just a String, the encoding happens in infrastructure

    public User(UUID id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters
    public UUID getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}