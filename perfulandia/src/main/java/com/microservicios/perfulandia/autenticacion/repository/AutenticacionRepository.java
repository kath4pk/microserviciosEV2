package com.microservicios.perfulandia.autenticacion.repository;

import com.microservicios.perfulandia.autenticacion.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface AutenticacionRepository extends JpaRepository<Usuario, Long> {
    
    // Método para buscar un usuario por su nombre de usuario
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    
    // Método para verificar si un nombre de usuario ya existe
    boolean existsByNombreUsuario(String nombreUsuario);
    
    // Método para verificar si un email ya existe
    boolean existsByEmail(String email);
}
