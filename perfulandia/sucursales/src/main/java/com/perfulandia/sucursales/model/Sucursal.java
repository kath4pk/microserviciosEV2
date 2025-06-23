package com.perfulandia.sucursales.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

@Entity

public class Sucursal {
    @Id
    private int id;
    private String nombreSucursal;
    private String direccion;
    }

