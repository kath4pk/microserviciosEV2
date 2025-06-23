package com.microservicios.perfulandia.notificaciones.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microservicios.perfulandia.notificaciones.model.Notificacion;
import com.microservicios.perfulandia.notificaciones.repository.NotificacionRepositorio;

@ExtendWith(MockitoExtension.class)
public class NotificacionServicesTest {

    @Mock
    private NotificacionRepositorio notificacionRepositorio;

    @InjectMocks
    private NotificacionServicio notificacionServicio;

    private Notificacion notificacionValida;
    private Notificacion notificacionGuardada;

    @BeforeEach
    void setUp() {
        // Configuración común para todas las pruebas
        notificacionValida = new Notificacion();
        notificacionValida.setMensaje("Reunión importante");
        notificacionValida.setDestinatario("juan@empresa.com");
        
        notificacionGuardada = new Notificacion();
        notificacionGuardada.setId(1L);
        notificacionGuardada.setMensaje("Reunión importante");
        notificacionGuardada.setDestinatario("juan@empresa.com");
    }

    @Test
    void enviarNotificacion_DeberiaRetornarNotificacionConId() {
        // Configurar comportamiento del repositorio
        when(notificacionRepositorio.save(any(Notificacion.class)))
            .thenReturn(notificacionGuardada);
        
        // Ejecutar
        Notificacion resultado = notificacionServicio.enviarNotificacion(notificacionValida);
        
        // Verificar
        assertNotNull(resultado.getId());
        assertEquals(1L, resultado.getId());
        
        // Verificar interacción con el repositorio
        verify(notificacionRepositorio).save(notificacionValida);
    }

    @Test
    void obtenerNotificacionesPorDestinatario_DeberiaRetornarListaCorrecta() {
        // Configurar datos de prueba
        Notificacion n1 = new Notificacion();
        n1.setId(1L);
        n1.setMensaje("Mensaje 1");
        n1.setDestinatario("maria@test.com");

        Notificacion n2 = new Notificacion();
        n2.setId(2L);
        n2.setMensaje("Mensaje 2");
        n2.setDestinatario("maria@test.com");

        List<Notificacion> notificaciones = List.of(n1, n2);
        
        // Configurar comportamiento del repositorio
        when(notificacionRepositorio.findByDestinatario("maria@test.com"))
            .thenReturn(notificaciones);
        
        // Ejecutar
        List<Notificacion> resultado = notificacionServicio.obtenerNotificacionesPorDestinatario("maria@test.com");
        
        // Verificar
        assertEquals(2, resultado.size());
        assertEquals("Mensaje 1", resultado.get(0).getMensaje());
        assertEquals("Mensaje 2", resultado.get(1).getMensaje());
    }

    @Test
    void obtenerNotificacionesPorDestinatario_SinResultados_DeberiaRetornarListaVacia() {
        // Configurar repositorio para retornar lista vacía
        when(notificacionRepositorio.findByDestinatario("inexistente@test.com"))
            .thenReturn(Collections.emptyList());
        
        // Ejecutar
        List<Notificacion> resultado = notificacionServicio.obtenerNotificacionesPorDestinatario("inexistente@test.com");
        
        // Verificar
        assertTrue(resultado.isEmpty());
    }

    @Test
    void enviarNotificacion_ConNotificacionNull_DeberiaLanzarExcepcion() {
        // Configurar y ejecutar
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            notificacionServicio.enviarNotificacion(null);
        });
        
        // Verificar
        assertEquals("La notificación no puede ser nula", exception.getMessage());
        verify(notificacionRepositorio, never()).save(any());
    }
}
