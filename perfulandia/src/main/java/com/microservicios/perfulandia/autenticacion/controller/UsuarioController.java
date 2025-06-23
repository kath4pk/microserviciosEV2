package com.microservicios.perfulandia.autenticacion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservicios.perfulandia.autenticacion.model.Usuario;
import com.microservicios.perfulandia.autenticacion.services.AutenticacionServices;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final AutenticacionServices service;

    public UsuarioController(AutenticacionServices service) {
        this.service = service;
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
}