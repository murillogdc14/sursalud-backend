package com.sursalud.citas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "pacientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "El documento es obligatorio")
    @Size(max = 20)
    @Column(nullable = false, unique = true)
    private String documento;

    @Size(max = 20)
    private String telefono;

    @Email(message = "Formato de correo inválido")
    @Size(max = 100)
    @Column(unique = true)
    private String correo;
}