package com.scoring.scoring.controller;

import com.scoring.scoring.DTO.DatosPersonalesDTO;
import com.scoring.scoring.model.Scoring;
import com.scoring.scoring.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scoring")
public class ScoringController {
    @Autowired
    private ScoringService scoringService;

    @PostMapping("/datos")
    public ResponseEntity<?> guardarDatos(@RequestBody DatosPersonalesDTO dto, Authentication auth) {
        String email = auth.getName(); // viene del token JWT
        scoringService.guardarDatos(email, dto);
        return ResponseEntity.ok("Datos personales guardados");
    }

    @GetMapping
    public ResponseEntity<Scoring> obtenerScoring(Authentication auth) {
        String email = auth.getName();
        Scoring score = scoringService.obtenerScoring(email);
        return ResponseEntity.ok(score);
    }
}
