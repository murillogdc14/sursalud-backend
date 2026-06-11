package com.sursalud.citas.service;

import com.sursalud.citas.model.Cita;
import com.sursalud.citas.repository.CitaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;
    private final PacienteService pacienteService;
    private final MedicoService medicoService;

    public List<Cita> obtenerTodas() {
        return citaRepository.findAll();
    }

    public Cita obtenerPorId(Long id) {
        return citaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada con id: " + id));
    }

    public Cita crear(Cita cita) {
        // Validar que el paciente y el médico existan
        pacienteService.obtenerPorId(cita.getPaciente().getId());
        medicoService.obtenerPorId(cita.getMedico().getId());

        // Validar que no haya conflicto de horario para el médico
        boolean conflicto = citaRepository.existsByMedicoIdAndFechaAndHora(
                cita.getMedico().getId(), cita.getFecha(), cita.getHora()
        );
        if (conflicto) {
            throw new IllegalArgumentException("El médico ya tiene una cita en esa fecha y hora");
        }

        cita.setEstado("PROGRAMADA");
        return citaRepository.save(cita);
    }

    public void cancelar(Long id) {
        Cita cita = obtenerPorId(id);
        cita.setEstado("CANCELADA");
        citaRepository.save(cita);
    }
}