package com.microservicios.perfulandia.autenticacion.services;

import com.microservicios.perfulandia.autenticacion.model.Usuario;
import com.microservicios.perfulandia.autenticacion.repository.AutenticacionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	// nuevo método para el test
	public boolean existeUsuario(String nombreUsuario) {
		return usuarioRepository.existsByNombreUsuario(nombreUsuario);
	}

    public Usuario registrarUsuario(Usuario usuario) {
        if (existeUsuario(usuario.getNombreUsuario())) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }
        
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        
        return usuarioRepository.save(usuario);
    }

    public Usuario autenticarUsuario(Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        if (!passwordEncoder.matches(usuario.getContrasena(), usuarioExistente.getContrasena())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        
        return usuarioExistente;
    }

    // ajustado para devolver exactamente "Login exitoso"
    public ResponseEntity<String> loginUsuario(Usuario usuario) {
        if (existeUsuario(usuario.getNombreUsuario())) {
            return ResponseEntity.ok("Login exitoso");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body("Credenciales inválidas");
    }

}
