package com.perfulandia.sucursales.service;

import com.perfulandia.sucursales.model.Sucursal;
import com.perfulandia.sucursales.repository.SucursalRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SucursalService {
    private final SucursalRepository sucursalRepository;

    public SucursalService(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    public List<Sucursal> getSucursal() {
        return sucursalRepository.obtenerSucursal();
    }

    public String saveSucursal(Sucursal sucursal) {
        return sucursalRepository.saveSucursal(sucursal);
    }
    
    public Sucursal getSucursal(int id) {
        return sucursalRepository.findById(id);
    }

    public String updateSucursal(int id, Sucursal sucursal) {
        return sucursalRepository.updateSucursal(sucursal);
    }

    public String deleteSucursal(int id) {
        sucursalRepository.deleteSucursal(id);
        return "Sucursal eliminada";
    }
}


