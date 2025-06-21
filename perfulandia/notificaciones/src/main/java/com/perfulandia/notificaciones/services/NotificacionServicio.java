package com.perfulandia.notificaciones.services;

import com.perfulandia.notificaciones.model.Notificacion;
import com.perfulandia.notificaciones.repository.NotificacionRepositorio;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class NotificacionServicio {
    private final NotificacionRepositorio notificacionRepositorio;

    public NotificacionServicio(NotificacionRepositorio notificacionRepositorio) {
        this.notificacionRepositorio = notificacionRepositorio;
    }

    public Notificacion enviarNotificacion(Notificacion notificacion) {
        return notificacionRepositorio.save(notificacion);
    }

    public List<Notificacion> obtenerNotificacionesPorDestinatario(String destinatario) {
        return notificacionRepositorio.findByDestinatario(destinatario);
    }

}
