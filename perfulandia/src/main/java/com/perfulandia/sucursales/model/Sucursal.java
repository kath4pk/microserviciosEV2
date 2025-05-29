package com.perfulandia.sucursales.model;

import java.util.Date;

import org.apache.commons.math3.analysis.function.Identity;
import org.springframework.boot.autoconfigure.domain.EntityScan;

// import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

Identity
@Table(name="sucursal")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Sucursal {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true, length = 13, nullable=false)
    private String run;

    @Column(nullable=false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = true)
    private Date fechaNacimiento;

    @Column(nullable = false)
    private String correo;
}

