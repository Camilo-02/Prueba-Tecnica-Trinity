package com.example.prueba.service;
import com.example.prueba.Entity.Cliente;
import com.example.prueba.controller.ClienteController;
import com.example.prueba.service.IService.IClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClienteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    void testSaveCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        when(clienteService.save(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/api/cliente/guardar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"John\", \"apellido\": \"Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(clienteService, times(1)).save(any(Cliente.class));
    }

    @Test
    void testUpdateCliente() throws Exception {
        doNothing().when(clienteService).update(any(Cliente.class), anyLong());

        mockMvc.perform(put("/api/cliente/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"John\", \"apellido\": \"Doe\"}"))
                .andExpect(status().isOk());

        verify(clienteService, times(1)).update(any(Cliente.class), eq(1L));
    }

    @Test
    void testDeleteCliente() throws Exception {
        doNothing().when(clienteService).delete(anyLong());

        mockMvc.perform(delete("/api/cliente/{id}", 1L))
                .andExpect(status().isOk());

        verify(clienteService, times(1)).delete(1L);
    }

    @Test
    void testFindAllClientes() throws Exception {
        when(clienteService.listar()).thenReturn(Collections.singletonList(new Cliente()));

        mockMvc.perform(get("/api/cliente"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());

        verify(clienteService, times(1)).listar();
    }

    @Test
    void testFindClienteById() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        when(clienteService.listarId(anyLong())).thenReturn(Optional.of(cliente));

        mockMvc.perform(get("/api/cliente/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(clienteService, times(1)).listarId(1L);
    }
}
