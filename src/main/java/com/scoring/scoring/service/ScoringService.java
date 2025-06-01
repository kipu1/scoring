package com.scoring.scoring.service;

import com.scoring.scoring.DTO.DatosPersonalesDTO;
import com.scoring.scoring.model.Scoring;
import com.scoring.scoring.model.Usuario;
import com.scoring.scoring.repository.ScoringRepository;
import com.scoring.scoring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoringService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired private ScoringRepository scoringRepository;

    public int calcularScore(int edad, double ingreso) {
        int score = (int) (edad * 2 + ingreso / 100);
        return Math.min(score, 100);
    }


    public void guardarDatos(String email, DatosPersonalesDTO datos) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();

        Scoring scoring = new Scoring();
        scoring.setEdad(datos.getEdad());
        scoring.setIngreso(datos.getIngreso());
        scoring.setScore(calcularScore(datos.getEdad(), datos.getIngreso()));
        scoring.setUsuario(usuario);

        scoringRepository.save(scoring);
    }

    public Scoring obtenerScoring(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow();
        return scoringRepository.findByUsuario(usuario).orElseThrow();
    }
}
