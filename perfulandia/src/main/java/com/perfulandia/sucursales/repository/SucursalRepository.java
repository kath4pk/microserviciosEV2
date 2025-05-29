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

    public List <Sucursal> obtenerSucursals(){
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

    public String update(Sucursal sucursal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public Sucursal buscarPorId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    public void delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
    // public void delete(Long id){
       // sucursalRepository.deleteById(id);
}
       
 