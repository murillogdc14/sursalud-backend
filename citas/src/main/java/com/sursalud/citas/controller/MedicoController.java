package com.sursalud.citas.controller;

import com.sursalud.citas.model.Medico;
import com.sursalud.citas.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/medicos")
@RequiredArgsConstructor
@Tag(name = "Médicos", description = "Gestión de médicos de la clínica")
public class MedicoController {

    private final MedicoService medicoService;

    @GetMapping
    @Operation(summary = "Obtener todos los médicos")
    public ResponseEntity<List<Medico>> obtenerTodos() {
        return ResponseEntity.ok(medicoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener médico por ID")
    public ResponseEntity<Medico> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.obtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Crear nuevo médico")
    public ResponseEntity<Medico> crear(@Valid @RequestBody Medico medico) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.crear(medico));
    }
}