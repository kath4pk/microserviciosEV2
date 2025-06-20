package com.microservicios.perfulandia.autenticacion.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombreUsuario;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String rol; // Puede ser "USER", "ADMIN", etc.
}
