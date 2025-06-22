package com.perfulandia.sucursales.model;

// import jakarta.persistence.*; 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

// @Entity

public class Sucursal {
    private Integer id;
    private String nombreSucursal;
    private String direccion;
}

