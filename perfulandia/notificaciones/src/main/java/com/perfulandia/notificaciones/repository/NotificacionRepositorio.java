package com.perfulandia.notificaciones.repository;

import com.perfulandia.notificaciones.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificacionRepositorio extends JpaRepository<Notificacion, Long> {
 
    List<Notificacion> findByDestinatario(String destinatario);
    
    List<Notificacion> findByTipo(String tipo);
}
