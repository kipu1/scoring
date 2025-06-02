package com.scoring.scoring.DTO;

public class AuthRequest {
    private String nombre;
    private String email;
    private String password;

    // Constructor vacío requerido por Spring
    public AuthRequest() {}

    // Constructor útil para pruebas o creación rápida


    public AuthRequest(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
