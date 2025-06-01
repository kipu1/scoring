package com.scoring.scoring.DTO;

public class ScoringDTO {
    private int edad;
    private double ingreso;
    private int score;

    public ScoringDTO(int edad, double ingreso, int score) {
        this.edad = edad;
        this.ingreso = ingreso;
        this.score = score;
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
}
