package com.scoring.scoring.controller;

import com.scoring.scoring.DTO.AuthRequest;
import com.scoring.scoring.DTO.AuthResponse;
import com.scoring.scoring.DTO.PerfilDTO;
import com.scoring.scoring.model.Usuario;
import com.scoring.scoring.security.JwtUtil;
import com.scoring.scoring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        try {
            usuarioService.registrarUsuario(usuario);
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Usuario registrado con éxito");
            return ResponseEntity.ok(respuesta);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/perfil")
    public ResponseEntity<?> actualizarPerfil(@RequestBody PerfilDTO perfilDTO, Authentication auth) {
        String emailActual = auth.getName();

        try {
            String nuevoEmail = usuarioService.editarPerfil(emailActual, perfilDTO.getNuevoEmail(), perfilDTO.getNombre());

            // Generar nuevo token si el email fue actualizado
            String nuevoToken = jwtUtil.generateToken(nuevoEmail);

            return ResponseEntity.ok(Map.of(
                    "mensaje", "Perfil actualizado correctamente",
                    "token", nuevoToken,
                    "nuevoEmail", nuevoEmail
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    @PutMapping("/editar-usuario")
    public ResponseEntity<?> editarUsuario(@RequestBody Map<String, String> payload, Authentication auth) {
        try {
            String emailActual = auth.getName();
            String nuevoNombre = payload.get("nombre");
            String nuevoEmail = payload.get("email");

            usuarioService.editarUsuario(emailActual, nuevoNombre, nuevoEmail);

            return ResponseEntity.ok(Map.of("mensaje", "Usuario actualizado correctamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            var authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );

            String username = authentication.getName();
            Usuario usuario = usuarioService.obtenerPorEmail(username); // Corrección aquí ✅

            String token = jwtUtil.generateToken(username);

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "nombre", usuario.getNombre() // Aseguramos que el nombre esté en la respuesta
            ));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
        }
    }
}