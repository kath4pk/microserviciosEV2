package com.perfulandia.sucursales.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia.sucursales.model.Sucursal;
import com.perfulandia.sucursales.repository.SucursalRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
// @Transactional
public class SucursalService {
    
    @Autowired
    private SucursalRepository sucursalRepository;

    public List<Sucursal> findAll(){
        return sucursalRepository.findAll();
    }

    public Sucursal findById(long id){
        return sucursalRepository.findById(id).get();
    }

    public Sucursal save(Sucursal sucursal){
        return sucursalRepository.save(sucursal);
    }
    
    public void delete(Long id){
        sucursalRepository.deleteById(id);
    }

}
