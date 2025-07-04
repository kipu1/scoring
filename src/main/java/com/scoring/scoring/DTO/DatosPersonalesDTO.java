package com.scoring.scoring.DTO;



import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public class DatosPersonalesDTO {

    @Min(value = 1, message = "La edad debe ser mayor o igual a 1")
    private int edad;

    @Positive(message = "El ingreso debe ser un número positivo")
    private double ingreso;

    public DatosPersonalesDTO() {}

    public DatosPersonalesDTO(int edad, double ingreso) {
        this.edad = edad;
        this.ingreso = ingreso;
    }

    // Getters y setters
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
}
