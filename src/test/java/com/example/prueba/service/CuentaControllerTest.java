package com.example.prueba.service;

import com.example.prueba.Entity.Cuenta;
import com.example.prueba.controller.CuentaController;
import com.example.prueba.service.IService.ICuentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CuentaControllerTest {

    @Mock
    private ICuentaService cuentaService;

    @InjectMocks
    private CuentaController cuentaController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cuentaController).build();
    }

    @Test
    void crearCuenta() throws Exception {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setTipoCuenta("AHORRO");
        when(cuentaService.crearCuenta(anyLong(), anyString(), anyLong())).thenReturn(cuenta);

        mockMvc.perform(post("/api/cuenta/crear")
                        .param("id", "1")
                        .param("tipoCuenta", "AHORRO")
                        .param("GMF", "12345"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{'id': 1, 'tipoCuenta': 'AHORRO'}"));
    }

    @Test
    void obtenerCuentaPorId() throws Exception {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(1L);
        cuenta.setTipoCuenta("AHORRO");
        when(cuentaService.obtenerCuentaPorId(anyLong())).thenReturn(Optional.of(cuenta));

        mockMvc.perform(get("/api/cuenta/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{'id': 1, 'tipoCuenta': 'AHORRO'}"));
    }
}