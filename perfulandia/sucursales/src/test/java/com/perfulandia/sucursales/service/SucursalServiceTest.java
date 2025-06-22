package com.perfulandia.sucursales.service;

import com.perfulandia.sucursales.model.Sucursal;
import com.perfulandia.sucursales.repository.SucursalRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SucursalServiceTest {

    private SucursalRepository sucursalRepository;
    private SucursalService sucursalService;

    @BeforeEach
    void setUp() {
        sucursalRepository = mock(SucursalRepository.class);
        sucursalService = new SucursalService(sucursalRepository);
    }

    @Test
    public void testGuardarSucursal() {
    Sucursal sucursal = new Sucursal(1, "Sucursal Maipú", "Avenida Pajaritos 123");
    when(sucursalRepository.saveSucursal(sucursal)).thenReturn("Sucursal guardada");

    String respuesta = sucursalService.saveSucursal(sucursal);

    verify(sucursalRepository, times(1)).saveSucursal(sucursal);
    assertEquals("Sucursal guardada", respuesta);
}


    /*
    @Test
    public void testObtenerSucursal() {
        when(sucursalRepository.obtenerSucursal()).thenReturn(List.of(
                new Sucursal(1, "Sucursal 1", "Dirección 1")
        )); 

        List<Sucursal> resultado = sucursalService.getSucursal();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("Sucursal 1", resultado.get(0).getNombre());
    }

    @Test
    public void testUpdateSucursal() {
        Sucursal actualizada = new Sucursal(1, "Sucursal Actualizada", "Providencia");

        when(sucursalRepository.updateSucursal(actualizada)).thenReturn("Sucursal actualizada con éxito");

        String respuesta = sucursalService.updateSucursal(1, actualizada);

        assertEquals("Sucursal actualizada con éxito", respuesta);
        verify(sucursalRepository, times(1)).updateSucursal(actualizada);
    }

    @Test
    public void testDeleteSucursal() {
        when(sucursalRepository.deleteSucursal(1)).thenReturn("Sucursal eliminada con éxito");

        String respuesta = sucursalService.deleteSucursal(1);

        assertEquals("Sucursal eliminada con éxito", respuesta);
    }
    */
}
