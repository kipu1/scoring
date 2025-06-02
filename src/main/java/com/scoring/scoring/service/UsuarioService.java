package com.scoring.scoring.service;

import com.scoring.scoring.DTO.DatosPersonalesDTO;
import com.scoring.scoring.model.Scoring;
import com.scoring.scoring.model.Usuario;
import com.scoring.scoring.repository.ScoringRepository;
import com.scoring.scoring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ScoringRepository scoringRepository;

    /**
     * Registra un nuevo usuario en la base de datos.
     */
    public void registrarUsuario(Usuario usuario) {
        // Validar si el email ya está registrado
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }

        // Encriptar contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }

    /**
     * Obtiene un usuario por email
     */
    public Usuario obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
    public void editarDatos(String email, DatosPersonalesDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Scoring scoring = scoringRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("No se encontró scoring para el usuario"));

        scoring.setEdad(dto.getEdad());
        scoring.setIngreso(dto.getIngreso());

        int nuevoScore = calcularScore(dto.getEdad(), dto.getIngreso());
        scoring.setScore(nuevoScore);

        scoringRepository.save(scoring);
    }

    private int calcularScore(int edad, double ingreso) {
        // Lógica de ejemplo (ajústala según tus reglas reales)
        int score = 300;
        if (edad >= 25 && edad <= 50) score += 100;
        if (ingreso >= 2000) score += 200;
        return score;
    }

}
