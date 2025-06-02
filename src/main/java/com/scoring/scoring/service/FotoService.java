package com.scoring.scoring.service;

import com.scoring.scoring.model.Foto;
import com.scoring.scoring.model.Usuario;
import com.scoring.scoring.repository.FotoRepository;
import com.scoring.scoring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FotoService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired private FotoRepository fotoRepository;

    public void guardarFoto(String email, byte[] data) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        Foto existente = fotoRepository.findByUsuario(usuario);

        if (existente != null) {
            existente.setData(data); // actualizar imagen
            fotoRepository.save(existente);
        } else {
            Foto nuevaFoto = new Foto();
            nuevaFoto.setData(data);
            nuevaFoto.setUsuario(usuario);
            fotoRepository.save(nuevaFoto);
        }
    }
    public void eliminarFoto(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setFoto(null);
        usuarioRepository.save(usuario);
    }

}
