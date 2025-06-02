package com.scoring.scoring.model;

import jakarta.persistence.*;

@Entity
public class Foto {
    @Id
    @GeneratedValue
    private Long id;

    @Lob
    private byte[] data;

    @OneToOne
    @JoinColumn(unique = true) // ✅ esto asegura la unicidad del usuario en la tabla foto
    private Usuario usuario;

    public Foto() {
    }

    public Foto(Long id, byte[] data, Usuario usuario) {
        this.id = id;
        this.data = data;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
