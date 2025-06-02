package com.scoring.scoring.controller;

import com.scoring.scoring.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/foto")
public class FotoController {
    @Autowired
    private FotoService fotoService;

    @PostMapping
    public ResponseEntity<?> subirFoto(@RequestParam("archivo") MultipartFile archivo, Authentication auth) {
        String email = auth.getName();
        try {
            byte[] data = archivo.getBytes();
            fotoService.guardarFoto(email, data);
            return ResponseEntity.ok("Foto guardada");
        } catch (Exception e) {
            e.printStackTrace(); // Muy importante para saber el error real
            return ResponseEntity.status(500).body("Error al guardar foto: " + e.getMessage());
        }
    }
    @DeleteMapping
    public ResponseEntity<?> eliminarFoto(Authentication auth) {
        String email = auth.getName();

        try {
            fotoService.eliminarFoto(email);
            return ResponseEntity.ok("Foto eliminada");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar foto: " + e.getMessage());
        }
    }



}
