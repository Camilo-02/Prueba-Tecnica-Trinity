package com.example.prueba.service;

import com.example.prueba.Entity.Transaccion;
import com.example.prueba.controller.TransaccionController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TransaccionControllerTest {

    @Mock
    private TransaccionServiceImpl transaccionService;

    @InjectMocks
    private TransaccionController transaccionController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transaccionController).build();
    }

    @Test
    void realizarTransferencia() throws Exception {
        Transaccion transaccion = new Transaccion();
        transaccion.setId(1L);
        transaccion.setMonto(100.0);
        when(transaccionService.realizarTransferencia(anyLong(), anyLong(), anyDouble())).thenReturn(transaccion);

        mockMvc.perform(post("/transacciones/transacciones")
                        .param("cuentaOrigenId", "1")
                        .param("cuentaDestinoId", "2")
                        .param("monto", "100.0"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{'id': 1, 'monto': 100.0}"));
    }
}
