package com.microservicios.perfulandia.autenticacion.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.microservicios.perfulandia.autenticacion.model.Usuario;
import com.microservicios.perfulandia.autenticacion.repository.AutenticacionRepository;

@ExtendWith(MockitoExtension.class)
class AutenticacionServicesTest {

    @Mock
    private AutenticacionRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AutenticacionServices autServices;

    @Captor
    private ArgumentCaptor<Usuario> usuarioCaptor;

    private Usuario usuarioValido;

    @BeforeEach
    void setUp() {
        usuarioValido = new Usuario();
        usuarioValido.setNombreUsuario("testuser");
        usuarioValido.setContrasena("password123");
    }

    @Test
    void existeUsuarioDevuelveTrue() {
        when(usuarioRepository.existsByNombreUsuario("testuser")).thenReturn(true);
        assertTrue(autServices.existeUsuario("testuser"));
    }

    @Test
    void existeUsuarioDevuelveFalse() {
        when(usuarioRepository.existsByNombreUsuario("testuser")).thenReturn(false);
        assertFalse(autServices.existeUsuario("testuser"));
    }

    @Test
    void registrarUsuarioExitoso() {
        when(usuarioRepository.existsByNombreUsuario("testuser")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("hashedPassword");
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario u = invocation.getArgument(0);
            u.setId(1L);
            return u;
        });

        Usuario resultado = autServices.registrarUsuario(usuarioValido);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("hashedPassword", resultado.getContrasena());
        verify(usuarioRepository).save(usuarioCaptor.capture());
        assertEquals("testuser", usuarioCaptor.getValue().getNombreUsuario());
    }

    @Test
    void registrarUsuarioDuplicadoLanzaException() {
        when(usuarioRepository.existsByNombreUsuario("testuser")).thenReturn(true);
        RuntimeException ex = assertThrows(RuntimeException.class,
            () -> autServices.registrarUsuario(usuarioValido));
        assertEquals("El nombre de usuario ya está en uso", ex.getMessage());
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void autenticarUsuarioExitoso() {
        Usuario usuarioEnDB = new Usuario();
        usuarioEnDB.setNombreUsuario("testuser");
        usuarioEnDB.setContrasena("hashedPassword");

        when(usuarioRepository.findByNombreUsuario("testuser"))
            .thenReturn(Optional.of(usuarioEnDB));
        when(passwordEncoder.matches("password123", "hashedPassword")).thenReturn(true);

        Usuario resultado = autServices.autenticarUsuario(usuarioValido);

        assertNotNull(resultado);
        assertEquals(usuarioEnDB, resultado);
    }

    @Test
    void autenticarUsuarioNoEncontradoLanzaException() {
        when(usuarioRepository.findByNombreUsuario("testuser"))
            .thenReturn(Optional.empty());
        RuntimeException ex = assertThrows(RuntimeException.class,
            () -> autServices.autenticarUsuario(usuarioValido));
        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    @Test
    void autenticarPasswordIncorrectoLanzaException() {
        Usuario usuarioEnDB = new Usuario();
        usuarioEnDB.setNombreUsuario("testuser");
        usuarioEnDB.setContrasena("hashedPassword");

        when(usuarioRepository.findByNombreUsuario("testuser"))
            .thenReturn(Optional.of(usuarioEnDB));
        when(passwordEncoder.matches("password123", "hashedPassword")).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
            () -> autServices.autenticarUsuario(usuarioValido));
        assertEquals("Contraseña incorrecta", ex.getMessage());
    }

    @Test
    void loginExitoso() {
        when(usuarioRepository.existsByNombreUsuario("testuser")).thenReturn(true);

        ResponseEntity<String> resp = autServices.loginUsuario(usuarioValido);

        assertEquals(200, resp.getStatusCodeValue());
        assertEquals("Login exitoso", resp.getBody());
    }

    @Test
    void loginFallidoDevuelveCredencialesInvalidas() {
        when(usuarioRepository.existsByNombreUsuario("testuser")).thenReturn(false);

        ResponseEntity<String> resp = autServices.loginUsuario(usuarioValido);

        assertEquals(401, resp.getStatusCodeValue());
        assertEquals("Credenciales inválidas", resp.getBody());
    }
}