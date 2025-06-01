package com.scoring.scoring.service;

import com.scoring.scoring.model.Foto;
import com.scoring.scoring.model.Usuario;
import com.scoring.scoring.repository.FotoRepository;
import com.scoring.scoring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired private FotoRepository fotoRepository;

    public void guardarFoto(String email, byte[] data) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));

        Foto foto = new Foto();
        foto.setUsuario(usuario);
        foto.setData(data);

        fotoRepository.save(foto);
    }

}
