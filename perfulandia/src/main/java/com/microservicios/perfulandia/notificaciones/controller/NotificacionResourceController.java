package com.microservicios.perfulandia.notificaciones.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservicios.perfulandia.notificaciones.assembler.NotificacionModelAssembler;
import com.microservicios.perfulandia.notificaciones.assembler.NotificacionModelAssembler.NotificacionResource;
import com.microservicios.perfulandia.notificaciones.model.Notificacion;
import com.microservicios.perfulandia.notificaciones.services.NotificacionServicio;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionResourceController {

    private final NotificacionServicio notificacionServicio;
    private final NotificacionModelAssembler assembler;

    public NotificacionResourceController(NotificacionServicio notificacionServicio, NotificacionModelAssembler assembler) {
        this.notificacionServicio = notificacionServicio;
        this.assembler = assembler;
    }

    @PostMapping
    public ResponseEntity<NotificacionResource> enviarNotificacion(@RequestBody Notificacion notificacion) {
        Notificacion nuevaNotificacion = notificacionServicio.enviarNotificacion(notificacion);
        return ResponseEntity.ok(assembler.toModel(nuevaNotificacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacionResource> obtenerNotificacionPorId(@PathVariable Long id) {
        Notificacion notificacion = notificacionServicio.obtenerNotificacionPorId(id);
        return ResponseEntity.ok(assembler.toModel(notificacion));
    }
}
