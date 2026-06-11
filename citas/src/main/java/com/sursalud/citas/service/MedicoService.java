package com.sursalud.citas.service;

import com.sursalud.citas.model.Medico;
import com.sursalud.citas.repository.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public List<Medico> obtenerTodos() {
        return medicoRepository.findAll();
    }

    public Medico obtenerPorId(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Médico no encontrado con id: " + id));
    }

    public Medico crear(Medico medico) {
        return medicoRepository.save(medico);
    }
}