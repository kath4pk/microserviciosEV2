package com.microservicios.perfulandia.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.microservicios.perfulandia.autenticacion.controller.UsuarioController;
import com.microservicios.perfulandia.autenticacion.model.Usuario;

@Component
public class UsuarioModelAssembler 
    implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(usuario,
            // Enlace para registrar un usuario
            linkTo(methodOn(UsuarioController.class)
                .registrarUsuario(usuario)).withRel("registrar"),
            // Enlace para hacer login
            linkTo(methodOn(UsuarioController.class)
                .loginUsuario(usuario)).withRel("login")
        );
    }
}