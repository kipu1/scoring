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

    @Autowired
    private ScoringRepository scoringRepository;

    public int calcularScore(int edad, double ingreso) {
        int score = (int) (edad * 2 + ingreso / 100);
        return Math.min(score, 100);
    }

    public void guardarDatos(String email, DatosPersonalesDTO datos) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));

        Scoring scoring = scoringRepository.findByUsuario(usuario).orElse(new Scoring());
        // Si no existe scoring, crea uno nuevo, si existe, actualiza

        scoring.setEdad(datos.getEdad());
        scoring.setIngreso(datos.getIngreso());
        scoring.setScore(calcularScore(datos.getEdad(), datos.getIngreso()));
        scoring.setUsuario(usuario);

        scoringRepository.save(scoring);
    }

    public Scoring obtenerScoring(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));

        return scoringRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("No existe scoring para el usuario con email: " + email));
    }
    public void editarDatos(String email, DatosPersonalesDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Scoring scoring = scoringRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Scoring no encontrado"));

        scoring.setEdad(dto.getEdad());
        scoring.setIngreso(dto.getIngreso());
        scoring.setScore(calcularScore(dto.getEdad(), dto.getIngreso()));

        scoringRepository.save(scoring);
    }
    public void eliminarDatos(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Scoring scoring = scoringRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Scoring no encontrado"));

        scoringRepository.delete(scoring);
        // Opcional: borrar también foto si lo deseas
    }

}
