package com.perfulandia.sucursales.controller;

import com.perfulandia.sucursales.model.Sucursal;
import com.perfulandia.sucursales.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/sucursales")
public class sucursalesController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping ("api/sucursales/agregar")
    public Sucursal agregarSucursal(@RequestBody Sucursal sucursal){
        return sucursalService.getSucursal(0);
    }

    @PutMapping("path/put")
    public String updateSucursal(@PathVariable int id, @RequestBody Sucursal sucursal) {
        //TODO: process PUT request
        return (String) sucursalService.updateSucursal(sucursal);
    }

    @DeleteMapping("/api/delete")
    public String deleteSucursal(@PathVariable int id){
        return sucursalService.deleteSucursal(id);
    }
}
