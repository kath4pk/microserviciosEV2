package com.microservicios.perfulandia.autenticacion.services;
import com.microservicios.perfulandia.autenticacion.model.Usuario;
import com.microservicios.perfulandia.autenticacion.repository.AutenticacionRepository;
import com.microservicios.perfulandia.autenticacion.repository.AutenticacionRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionServices {

	private final AutenticacionRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;


	public AutenticacionServices(AutenticacionRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}

    public Usuario registrarUsuario(Usuario usuario) {
        // Verificar si el usuario ya existe
        if (usuarioRepository.existsByNombreUsuario(usuario.getNombreUsuario())) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }
        
        // Codificar la contraseña antes de guardarla
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        
        // Guardar el nuevo usuario
        return usuarioRepository.save(usuario);
    }

    public Usuario autenticarUsuario(Usuario usuario) {
        // Buscar el usuario por nombre de usuario
        Usuario usuarioExistente = usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Verificar la contraseña
        if (!passwordEncoder.matches(usuario.getContrasena(), usuarioExistente.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuarioExistente;
    }

    public org.springframework.http.ResponseEntity<String> loginUsuario(Usuario usuario) {
        try {
            Usuario usuarioAutenticado = autenticarUsuario(usuario);
            return org.springframework.http.ResponseEntity.ok("Login exitoso para: " + usuarioAutenticado.getNombreUsuario());
        } catch (RuntimeException e) {
            return org.springframework.http.ResponseEntity.status(401).body(e.getMessage());
        }
    }

}
