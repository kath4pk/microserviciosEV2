package com.perfulandia.sucursales.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perfulandia.sucursales.model.Sucursal;
@Repository
public class SucursalRepository {
    private List<Sucursal> listaSucursales = new ArrayList<>();

    public List <Sucursal> obtenerSucursal(){
        return listaSucursales;
    }

    @Autowired
    private SucursalRepository sucursalRepository;

    public List<Sucursal> findAll(){
        return sucursalRepository.findAll();
    }

    public Sucursal findById(long id){
        return (Sucursal) sucursalRepository.findById(id);
    }

    public String save(Sucursal sucursal){
        return sucursalRepository.save(sucursal);
    }

    public String updateSucursal(Sucursal sucursal) {
        return sucursalRepository.updateSucursal(sucursal);
    }

    public Object deleteSucursal(int id) {
        return sucursalRepository.deleteSucursal(id);
    }
}

       
 