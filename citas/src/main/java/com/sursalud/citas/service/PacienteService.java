package com.sursalud.citas.service;

import com.sursalud.citas.model.Paciente;
import com.sursalud.citas.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public List<Paciente> obtenerTodos() {
        return pacienteRepository.findAll();
    }

    public Paciente obtenerPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con id: " + id));
    }

    public Paciente crear(Paciente paciente) {
        if (pacienteRepository.existsByDocumento(paciente.getDocumento())) {
            throw new IllegalArgumentException("Ya existe un paciente con ese documento");
        }
        if (paciente.getCorreo() != null && pacienteRepository.existsByCorreo(paciente.getCorreo())) {
            throw new IllegalArgumentException("Ya existe un paciente con ese correo");
        }
        return pacienteRepository.save(paciente);
    }

    public Paciente actualizar(Long id, Paciente datos) {
        Paciente existente = obtenerPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setTelefono(datos.getTelefono());
        existente.setCorreo(datos.getCorreo());
        return pacienteRepository.save(existente);
    }

    public void eliminar(Long id) {
        obtenerPorId(id); // lanza excepción si no existe
        pacienteRepository.deleteById(id);
    }
}