package com.scoring.scoring.controller;

import com.scoring.scoring.DTO.DatosPersonalesDTO;
import com.scoring.scoring.DTO.ScoringDTO;
import com.scoring.scoring.model.Scoring;
import com.scoring.scoring.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/scoring")
public class ScoringController {
    @Autowired
    private ScoringService scoringService;

    @PostMapping("/datos")
    public ResponseEntity<?> guardarDatos(@RequestBody DatosPersonalesDTO dto, Authentication auth) {
        System.out.println("Llega petición guardarDatos con dto: " + dto.getEdad() + ", " + dto.getIngreso());
        String email = auth.getName();
        scoringService.guardarDatos(email, dto);
        // Retornamos un JSON con mensaje
        return ResponseEntity.ok(Map.of("mensaje", "Foto guardada"));

    }

    @PutMapping("/datos")
    public ResponseEntity<?> editarDatos(@RequestBody DatosPersonalesDTO dto, Authentication auth) {
        String email = auth.getName();
        scoringService.editarDatos(email, dto);
        return ResponseEntity.ok(Map.of("mensaje", "Datos actualizados correctamente"));
    }
    @DeleteMapping
    public ResponseEntity<?> eliminarDatos(Authentication auth) {
        String email = auth.getName();
        scoringService.eliminarDatos(email);
        return ResponseEntity.ok(Map.of("mensaje", "Datos eliminados correctamente"));
    }

    @GetMapping
    public ResponseEntity<ScoringDTO> obtenerScoring(Authentication auth) {
        String email = auth.getName();
        Scoring scoring = scoringService.obtenerScoring(email);
        ScoringDTO dto = new ScoringDTO(scoring.getEdad(), scoring.getIngreso(), scoring.getScore());
        return ResponseEntity.ok(dto);
    }
}
