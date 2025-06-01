package com.scoring.scoring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Scoring {
    @Id
    @GeneratedValue
    private Long id;
    private int edad;
    private double ingreso;
    private int score; // calculado

    @OneToOne
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

