package com.microservicios.perfulandia.autenticacion.controller;

import com.microservicios.perfulandia.autenticacion.model.Usuario;
import com.microservicios.perfulandia.autenticacion.services.AutenticacionServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/autenticacion") 
public class AutenticacionController {

    private final AutenticacionServices autenticacionServices;

    public AutenticacionController(AutenticacionServices autenticacionServices) {
        this.autenticacionServices = autenticacionServices;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = autenticacionServices.registrarUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUsuario(@RequestBody Usuario usuario) {
        return autenticacionServices.loginUsuario(usuario);
    }


}
