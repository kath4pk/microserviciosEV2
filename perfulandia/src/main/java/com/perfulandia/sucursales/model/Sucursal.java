package com.perfulandia.sucursales.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Sucursal {

    private int id; 

    private String nombreSucursal;
    private String direccion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true, nullable=false)
    private String nombreSucursal;

    @Column(nullable=false)
    private String direccion;
}

