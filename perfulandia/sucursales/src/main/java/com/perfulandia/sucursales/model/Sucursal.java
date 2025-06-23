package com.perfulandia.sucursales.model;

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
    private int id;
    private String nombreSucursal;
    private String direccion;
    }

