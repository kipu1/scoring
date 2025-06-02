package com.scoring.scoring.repository;

import com.scoring.scoring.model.Foto;
import com.scoring.scoring.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoRepository extends JpaRepository<Foto, Long> {
    Foto findByUsuario(Usuario usuario);
}

