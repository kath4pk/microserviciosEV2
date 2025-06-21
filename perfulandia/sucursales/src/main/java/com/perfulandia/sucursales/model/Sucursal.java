package com.perfulandia.sucursales.model;

// import jakarta.persistence.*; 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// @Entity

public class Sucursal {
    private Integer id;
    private String nombreSucursal;
    private String direccion;
}

