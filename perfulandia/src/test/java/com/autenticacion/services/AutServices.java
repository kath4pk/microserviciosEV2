package com.autenticacion.services;

import com.microservicios.perfulandia.autenticacion.model.Usuario;
import com.microservicios.perfulandia.autenticacion.repository.AutenticacionRepository;

public class AutServices {
    private final AutenticacionRepository usuarioRepository;

    public AutServices(AutenticacionRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean existeUsuario(String nombreUsuario) {
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public Usuario buscarUsuarioPorNombre(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
