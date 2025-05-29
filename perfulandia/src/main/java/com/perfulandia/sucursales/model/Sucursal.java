package com.perfulandia.sucursales.model;


import java.util.Date;
import lombok.Data;
import.jakarta.persistence;;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="sucursal")

public class Sucursal {
    public Sucursal(Integer id, String nombreSucursal, String direccion) {
        this.id = id;
        this.nombreSucursal = nombreSucursal;
        this.direccion = direccion;
    }
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true, nullable=false)
    private String nombreSucursal;

    @Column(nullable=false)
    private String direccion;
}

