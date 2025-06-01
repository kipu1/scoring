package com.scoring.scoring.DTO;

public class AuthResponse {
    private String token;

    // Constructor vacío
    public AuthResponse() {}

    // Constructor con token
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter y Setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
