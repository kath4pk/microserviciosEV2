package com.autenticacion.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microservicios.perfulandia.autenticacion.model.Usuario;
import com.microservicios.perfulandia.autenticacion.repository.AutenticacionRepository;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AutServicesTest {

    @Mock
    private AutenticacionRepository usuarioRepository;

    @InjectMocks
    private AutServices autServices;

    @Test
    void CredencialesValidas() {
        Usuario usuarioMock = new Usuario();
        usuarioMock.setNombreUsuario("testUser");
        usuarioMock.setContrasena("testPassword");

        when(usuarioRepository.existsByNombreUsuario("testUser")).thenReturn(true);
        when(usuarioRepository.findByNombreUsuario("testUser")).thenReturn(Optional.of(usuarioMock));

        // Ejecutar y verificar existeUsuario
        boolean existe = autServices.existeUsuario("testUser");
        assertTrue(existe);

        // Ejecutar y verificar buscarUsuarioPorNombre
        Usuario encontrado = autServices.buscarUsuarioPorNombre("testUser");
        assertNotNull(encontrado);
        assertEquals("testUser", encontrado.getNombreUsuario());
        assertEquals("testPassword", encontrado.getContrasena());

        // Verificar que los métodos del mock fueron llamados
        verify(usuarioRepository).existsByNombreUsuario("testUser");
        verify(usuarioRepository).findByNombreUsuario("testUser");
    }

    
}
