package com.perfulandia.sucursales.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.perfulandia.sucursales.model.Sucursal;

@Repository
public class SucursalRepository {
    private List<Sucursal> listaSucursales = new ArrayList<>();

    public List<Sucursal> obtenerSucursal(){
        return listaSucursales;
    }

    public List<Sucursal> findAll(){
        return listaSucursales;
    }

    public Sucursal findById(long id){
        // ejemplo simple para buscar por id en la lista
        return listaSucursales.stream()
            .filter(s -> s.getId() == id)
            .findFirst()
            .orElse(null);
    }

    public String saveSucursal(Sucursal sucursal){
        listaSucursales.add(sucursal);
        return "Sucursal guardada";
    }

    public String updateSucursal(Sucursal sucursal) {
        // Aquí deberías buscar y actualizar la sucursal en la lista
        for (int i = 0; i < listaSucursales.size(); i++) {
            if (listaSucursales.get(i).getId() == sucursal.getId()) {
                listaSucursales.set(i, sucursal);
                return "Sucursal actualizada";
            }
        }
        return "Sucursal no encontrada";
    }

    public String deleteSucursal(int id) {
        listaSucursales.removeIf(s -> s.getId() == id);
        return "Sucursal eliminada";
    }
}
