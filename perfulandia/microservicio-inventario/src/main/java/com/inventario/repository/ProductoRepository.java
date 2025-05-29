package com.inventario.repository;

import com.inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Interface que extiende JpaRepository para ofrecer operaciones CRUD básicas
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Aquí puedes añadir métodos personalizados si quieres, por ahora con lo básico basta
}
