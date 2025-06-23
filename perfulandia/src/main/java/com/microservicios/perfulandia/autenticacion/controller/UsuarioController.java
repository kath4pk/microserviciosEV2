package com.microservicios.perfulandia.autenticacion.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservicios.perfulandia.autenticacion.model.Usuario;
import com.microservicios.perfulandia.autenticacion.services.AutenticacionServices;
import com.microservicios.perfulandia.assemblers.UsuarioModelAssembler;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final AutenticacionServices service;
    private final UsuarioModelAssembler assembler;

    public UsuarioController(AutenticacionServices service,
                            UsuarioModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevo = service.registrarUsuario(usuario);
        return ResponseEntity.ok(nuevo);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUsuario(@RequestBody Usuario usuario) {
        return service.loginUsuario(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> getUsuarioById(@PathVariable Long id) {
        EntityModel<Usuario> model = assembler.toModel(service.findById(id));
        return ResponseEntity.ok(model);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Usuario>>> getAllUsuarios() {
        List<EntityModel<Usuario>> usuarios = service.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        CollectionModel<EntityModel<Usuario>> collection = CollectionModel.of(
            usuarios,
            linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withSelfRel()
        );
        return ResponseEntity.ok(collection);
    }
}