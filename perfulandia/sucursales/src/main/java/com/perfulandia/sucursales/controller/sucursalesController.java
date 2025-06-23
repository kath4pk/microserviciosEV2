package com.perfulandia.sucursales.controller;

import com.perfulandia.sucursales.model.Sucursal;
import com.perfulandia.sucursales.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sucursales")
@Tag(name = "sucursal controller", description = "descripción de sucursales")
public class sucursalesController {

    @Autowired
    private final SucursalService sucursalService;

    public sucursalesController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @PostMapping("/agregar")
    @Operation(summary = "Crear nueva sucursal", description = "Guarda una nueva sucursal en la base de datos")
    public String saveSucursal(@RequestBody Sucursal sucursal){
        return sucursalService.saveSucursal(sucursal);
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar sucursal", description = "Actualiza una sucursal en la base de datos")
    public String updateSucursal(@PathVariable int id, @RequestBody Sucursal sucursal) {
        return sucursalService.updateSucursal(id, sucursal);
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar sucursal", description = "Elimina la sucursal correspondiente al ID especificado")
    public String deleteSucursal(@PathVariable int id){
        return sucursalService.deleteSucursal(id);
    }

    @GetMapping("/listar")
    @Operation(summary = "LISTAR SUCURSALES", description = "OBTENER UN LISTADO DE SUCURSALES DE LA BASE DE DATOS")
    public CollectionModel<EntityModel<Sucursal>> getSucursales() {
        List<Sucursal> sucursal = sucursalService.getSucursal();

        List<EntityModel<Sucursal>> sucursalModels = sucursal.stream()
            .map(s -> EntityModel.of(
                    s,
                    linkTo(methodOn(sucursalesController.class).getSucursalById((int) s.getId())).withSelfRel(),
                    linkTo(methodOn(sucursalesController.class).getSucursales()).withRel("sucursales")
            ))
            .collect(Collectors.toList());

        return CollectionModel.of(
            sucursalModels,
            linkTo(methodOn(sucursalesController.class).getSucursales()).withSelfRel()
        );
    }

    @GetMapping("/listar/{id}")
    public EntityModel<Sucursal> getSucursalById(@PathVariable int id) {
        Sucursal sucursal = sucursalService.getSucursal(id);
        return EntityModel.of(
            sucursal,
            linkTo(methodOn(sucursalesController.class).getSucursalById(id)).withSelfRel(),
            linkTo(methodOn(sucursalesController.class).getSucursales()).withRel("sucursales")
        );
    }

    public List<Sucursal> listarSucursal() {
        return sucursalService.getSucursal();
    }
}
    