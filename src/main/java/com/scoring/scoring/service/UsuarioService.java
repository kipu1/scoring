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
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("El correo ya está siendo utilizado");
        }


        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }

    public String editarPerfil(String emailActual, String nuevoEmail, String nuevoNombre) {
        Usuario usuario = usuarioRepository.findByEmail(emailActual)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Si cambia el correo, verificar que no esté repetido
        if (!emailActual.equals(nuevoEmail) && usuarioRepository.findByEmail(nuevoEmail).isPresent()) {
            throw new RuntimeException("El nuevo email ya está en uso");
        }

        usuario.setEmail(nuevoEmail);
        usuario.setNombre(nuevoNombre);

        usuarioRepository.save(usuario);
        return nuevoEmail; // lo usamos para emitir un nuevo token
    }

    /**
     * Obtiene un usuario por email
     */
    public Usuario obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
    public void editarUsuario(String emailActual, String nuevoNombre, String nuevoEmail) {
        Usuario usuario = usuarioRepository.findByEmail(emailActual)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Validar que el nuevo email no esté en uso por otro usuario
        if (!nuevoEmail.equals(emailActual) && usuarioRepository.findByEmail(nuevoEmail).isPresent()) {
            throw new RuntimeException("El nuevo email ya está registrado");
        }

        usuario.setNombre(nuevoNombre);
        usuario.setEmail(nuevoEmail);
        usuarioRepository.save(usuario);
    }


    private int calcularScore(int edad, double ingreso) {
        // Lógica de ejemplo (ajústala según tus reglas reales)
        int score = 300;
        if (edad >= 25 && edad <= 50) score += 100;
        if (ingreso >= 2000) score += 200;
        return score;
    }

}
