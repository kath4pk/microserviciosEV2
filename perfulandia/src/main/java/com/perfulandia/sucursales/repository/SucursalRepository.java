package com.perfulandia.sucursales.repository;

import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.perfulandia.sucursales.model.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long>{

    Object findById(long id);

    List<Sucursal> findAll();

    Sucursal save(Sucursal sucursal);

    void deleteById(Long id);

    
}
