package com.microservicios.perfulandia.notificaciones.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

import com.microservicios.perfulandia.notificaciones.controller.NotificacionResourceController;
import com.microservicios.perfulandia.notificaciones.model.Notificacion;

@Component
public class NotificacionModelAssembler
        implements RepresentationModelAssembler<Notificacion, NotificacionModelAssembler.NotificacionResource> {

    public static class NotificacionResource extends RepresentationModel<NotificacionResource> {
        private final Notificacion notificacion;

        public NotificacionResource(Notificacion notificacion) {
            this.notificacion = notificacion;
        }

        public Notificacion getNotificacion() {
            return notificacion;
        }
    }

    @Override
    @NonNull
    public NotificacionResource toModel(@NonNull Notificacion notificacion) {
        NotificacionResource resource = new NotificacionResource(notificacion);
        resource.add(linkTo(methodOn(NotificacionResourceController.class)
                .obtenerNotificacionPorId(notificacion.getId()))
                .withSelfRel());
        resource.add(linkTo(methodOn(NotificacionResourceController.class)
                .enviarNotificacion(null))
                .withRel("enviar-notificacion"));
        return resource;
    }
}