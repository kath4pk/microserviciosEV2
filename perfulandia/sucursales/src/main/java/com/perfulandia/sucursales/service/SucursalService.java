package com.perfulandia.sucursales.service;

import com.perfulandia.sucursales.model.Sucursal;
import com.perfulandia.sucursales.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SucursalService {
    @Autowired
    private SucursalRepository sucursalRepository;

    public List<Sucursal>getSucursal() {
        return sucursalRepository.obtenerSucursal();
    }

    public String saveSucursal(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }
    
    //public Sucursal getSucursal (int id) {
      //  return sucursalRepository.buscarPorId(id);
    //}

    public String updateSucursal(int id, Sucursal sucursal) {
        return sucursalRepository.updateSucursal(sucursal);
    }

    public String deleteSucursal(int id){
        sucursalRepository.deleteSucursal(id);
        return "producto eliminado";
    }
}
