package com.example.prueba.service;

import com.example.prueba.Entity.Cliente;
import com.example.prueba.Repository.IClienteRepository;
import com.example.prueba.Repository.ICuentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private IClienteRepository iClienteRepository;

    @Mock
    private ICuentaRepository iCuentaRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente clienteMayorEdad;
    private Cliente clienteMenorEdad;

    @BeforeEach
    public void setUp() {
        clienteMayorEdad = new Cliente();
        clienteMayorEdad.setDate(LocalDate.now().minusYears(20)); // Cliente mayor de edad

        clienteMenorEdad = new Cliente();
        clienteMenorEdad.setDate(LocalDate.now().minusYears(16)); // Cliente menor de edad
    }

    @Test
    public void testSaveClienteMayorEdad() {
        when(iClienteRepository.save(clienteMayorEdad)).thenReturn(clienteMayorEdad);

        Cliente result = clienteService.save(clienteMayorEdad);

        assertEquals(clienteMayorEdad, result);
    }

    @Test
    public void testSaveClienteMenorEdad() {
        assertThrows(IllegalArgumentException.class, () -> clienteService.save(clienteMenorEdad));
    }

    @Test
    public void listarClientesTest() {
        Cliente cliente1 = new Cliente(/* inicializar con valores */);
        Cliente cliente2 = new Cliente(/* inicializar con valores */);
        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);

        when(iClienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> listaClientes = clienteService.listar();

        assertEquals(clientes.size(), listaClientes.size());
    }

    @Test
    public void testListarIdClienteExistente() {
        long idCliente = 1L;
        Cliente clienteExistente = new Cliente(/* inicializar con valores */);

        when(iClienteRepository.findById(idCliente)).thenReturn(Optional.of(clienteExistente));

        Optional<Cliente> clienteObtenido = clienteService.listarId(idCliente);

        assertTrue(clienteObtenido.isPresent());
        assertEquals(clienteExistente.getId(), clienteObtenido.get().getId());
    }

    @Test
    public void testListarIdClienteNoExistente() {
        long idClienteNoExistente = 999L;

        when(iClienteRepository.findById(idClienteNoExistente)).thenReturn(Optional.empty());

        Optional<Cliente> clienteObtenido = clienteService.listarId(idClienteNoExistente);

        assertFalse(clienteObtenido.isPresent());
    }

    @Test
    public void testUpdateClienteExistente() {
        long idCliente = 1L;
        Cliente clienteExistente = new Cliente(/* inicializar con valores */);

        when(iClienteRepository.findById(idCliente)).thenReturn(Optional.of(clienteExistente));
        when(iClienteRepository.save(clienteExistente)).thenReturn(clienteExistente);

        Cliente clienteActualizado = new Cliente(/* inicializar con valores actualizados */);
        clienteActualizado.setId(idCliente);  // Asegúrate de establecer el ID del cliente actualizado

        clienteService.update(clienteActualizado, idCliente);

        // Puedes agregar más aserciones según sea necesario para verificar que la actualización fue correcta
        assertEquals(clienteExistente.getNombre(), clienteActualizado.getNombre());
    }

    @Test
    public void testUpdateClienteNoExistente() {
        long idClienteNoExistente = 999L;
        Cliente clienteActualizado = new Cliente(/* inicializar con valores actualizados */);

        when(iClienteRepository.findById(idClienteNoExistente)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> clienteService.update(clienteActualizado, idClienteNoExistente));
    }

    @Test
    public void testDeleteClienteSinCuentasVinculadas() {
        long idCliente = 1L;
        Cliente clienteExistente = new Cliente(/* inicializar con valores */);

        when(iClienteRepository.findById(idCliente)).thenReturn(Optional.of(clienteExistente));
        when(iCuentaRepository.countByCliente(clienteExistente)).thenReturn(0L);

        clienteService.delete(idCliente);

        // Verifica que el cliente se haya eliminado correctamente
    }

    @Test
    public void testDeleteClienteConCuentasVinculadas() {
        long idCliente = 1L;
        Cliente clienteExistente = new Cliente(/* inicializar con valores */);

        when(iClienteRepository.findById(idCliente)).thenReturn(Optional.of(clienteExistente));
        when(iCuentaRepository.countByCliente(clienteExistente)).thenReturn(1L); // Simulación de cuenta vinculada

        assertThrows(IllegalArgumentException.class, () -> clienteService.delete(idCliente));
    }

    @Test
    public void testExistsByClienteConCuentasVinculadas() {
        long idCliente = 1L;
        Cliente clienteExistente = new Cliente(/* inicializar con valores */);

        when(iClienteRepository.findById(idCliente)).thenReturn(Optional.of(clienteExistente));
        when(iCuentaRepository.countByCliente(clienteExistente)).thenReturn(1L); // Simulación de cuenta vinculada

        boolean result = clienteService.existsByCliente(idCliente);

        assertTrue(result);
    }

    @Test
    public void testExistsByClienteNoExistente() {
        long idClienteNoExistente = 999L;

        when(iClienteRepository.findById(idClienteNoExistente)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> clienteService.existsByCliente(idClienteNoExistente));
    }
}
