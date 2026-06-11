package com.sursalud.citas.repository;

import com.sursalud.citas.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByDocumento(String documento);
    boolean existsByDocumento(String documento);
    boolean existsByCorreo(String correo);
}