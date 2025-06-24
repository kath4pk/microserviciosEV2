package com.microservicios.perfulandia.notificaciones.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservicios.perfulandia.notificaciones.model.Notificacion;
import com.microservicios.perfulandia.notificaciones.repository.NotificacionRepositorio;

@Service
public class NotificacionServicio {

    private final NotificacionRepositorio notificacionRepositorio;

    public NotificacionServicio(NotificacionRepositorio notificacionRepositorio) {
        this.notificacionRepositorio = notificacionRepositorio;
    }

    public Notificacion enviarNotificacion(Notificacion notificacion) {
        if (notificacion == null) {
            throw new IllegalArgumentException("La notificación no puede ser nula");
        }
        return notificacionRepositorio.save(notificacion);
    }

    public List<Notificacion> obtenerNotificacionesPorDestinatario(String destinatario) {
        return notificacionRepositorio.findByDestinatario(destinatario);
    }

    public Notificacion obtenerNotificacionPorId(Long id) {
        return notificacionRepositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Notificación no encontrada: " + id));
    }
}
