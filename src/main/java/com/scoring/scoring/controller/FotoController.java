package com.scoring.scoring.controller;

import com.scoring.scoring.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/foto")
public class FotoController {
    @Autowired
    private FotoService fotoService;

    @PostMapping
    public ResponseEntity<?> subirFoto(@RequestParam("archivo") MultipartFile archivo, Authentication auth) throws IOException {
        String email = auth.getName();
        byte[] data = archivo.getBytes();

        fotoService.guardarFoto(email, data);
        return ResponseEntity.ok("Foto guardada");
    }
}
