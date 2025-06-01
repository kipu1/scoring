package com.scoring.scoring.repository;

import com.scoring.scoring.model.Scoring;
import com.scoring.scoring.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScoringRepository extends JpaRepository<Scoring, Long> {
    Optional<Scoring> findByUsuario(Usuario usuario);
}