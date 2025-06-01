package com.scoring.scoring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import java.util.Objects;

@Entity
public class Scoring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int edad;
    private double ingreso;
    private int score; // calculado

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    @JsonIgnore  // <-- Agrega esta línea para romper el ciclo en la serialización
    private Usuario usuario;

    public Scoring() {
    }

    public Scoring(Long id, int edad, double ingreso, Usuario usuario, int score) {
        this.id = id;
        this.edad = edad;
        this.ingreso = ingreso;
        this.usuario = usuario;
        this.score = score;
    }

    // Getters y setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getIngreso() {
        return ingreso;
    }

    public void setIngreso(double ingreso) {
        this.ingreso = ingreso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scoring)) return false;
        Scoring scoring = (Scoring) o;
        return Objects.equals(id, scoring.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
