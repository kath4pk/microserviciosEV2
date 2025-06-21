package com.perfulandia.sucursales.controller;

import com.perfulandia.sucursales.model.Sucursal;
import com.perfulandia.sucursales.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalesController {

    @Autowired
    private SucursalService sucursalService;

    @PostMapping("/agregar")
    public Sucursal agregarSucursal(@RequestBody Sucursal sucursal){
        return sucursalService.agregarSucursal(sucursal);
    }

    @PutMapping("/actualizar/{id}")
    public Sucursal updateSucursal(@PathVariable int id, @RequestBody Sucursal sucursal) {
        return sucursalService.updateSucursal(id, sucursal);
    }

    @DeleteMapping("/eliminar/{id}")
    public String deleteSucursal(@PathVariable int id){
        return sucursalService.deleteSucursal(id);
    }

    @GetMapping("/listar")
    public List<Sucursal> listarSucursales() {
        return sucursalService.getAllSucursales();
    }
}
