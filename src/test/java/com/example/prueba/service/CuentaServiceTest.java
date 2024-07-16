package com.example.prueba.service;

import com.example.prueba.Entity.Cliente;
import com.example.prueba.Entity.Cuenta;
import com.example.prueba.Repository.IClienteRepository;
import com.example.prueba.Repository.ICuentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CuentaServiceTest {

    @Mock
    private ICuentaRepository cuentaRepository;

    @Mock
    private IClienteRepository clienteRepository;

    @InjectMocks
    private CuentaServiceImpl cuentaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearCuenta() {

        Cliente cliente = new Cliente();
        cliente.setId(1L);

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(cuentaRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Cuenta cuenta = cuentaService.crearCuenta(1L, "AHORRO", 0);

        assertNotNull(cuenta);
        assertEquals("AHORRO", cuenta.getTipoCuenta());
        assertEquals("ACTIVA", cuenta.getEstado());
        assertEquals(cliente, cuenta.getCliente());
    }

    @Test
    void testActualizarSaldo() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setTipoCuenta("AHORRO");


        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));
        when(cuentaRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        cuentaService.actualizarSaldo(1L, 100.0);

        assertEquals(100.0, cuenta.getSaldo());
    }

    @Test
    void testCambiarEstado() {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setTipoCuenta("CORRIENTE");
        cuenta.setEstado("ACTIVA");

        when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));
        when(cuentaRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        cuentaService.cambiarEstado(1L, "INACTIVA");

        assertEquals("INACTIVA", cuenta.getEstado());
    }
}