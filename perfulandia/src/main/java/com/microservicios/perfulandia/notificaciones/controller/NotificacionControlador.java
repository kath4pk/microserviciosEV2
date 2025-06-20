package com.microservicios.perfulandia.notificaciones.controller;
import com.microservicios.perfulandia.notificaciones.model.Notificacion;
import com.microservicios.perfulandia.notificaciones.services.NotificacionServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionControlador {
    private final NotificacionServicio notificacionServicio;

    public NotificacionControlador(NotificacionServicio notificacionServicio) {
        this.notificacionServicio = notificacionServicio;
    }

    @PostMapping
    public ResponseEntity<Notificacion> enviarNotificacion(@RequestBody Notificacion notificacion) {
        Notificacion nuevaNotificacion = notificacionServicio.enviarNotificacion(notificacion);
        return ResponseEntity.ok(nuevaNotificacion);
    }

    @GetMapping("/destinatario/{destinatario}")
    public ResponseEntity<List<Notificacion>> obtenerNotificacionesPorDestinatario(@PathVariable String destinatario) {
        List<Notificacion> notificaciones = notificacionServicio.obtenerNotificacionesPorDestinatario(destinatario);
        return ResponseEntity.ok(notificaciones);
    }
}
