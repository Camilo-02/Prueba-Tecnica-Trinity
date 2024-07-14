package com.example.prueba.controller;


import com.example.prueba.Entity.Cuenta;
import com.example.prueba.service.IService.ICuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cuenta")
public class CuentaController {


    private  final ICuentaService cuentaService;

    @PostMapping("/crear")
    public Cuenta crearCuenta(@RequestParam int id, @RequestParam String tipoCuenta, @RequestParam long GMF) {
        return cuentaService.crearCuenta(id, tipoCuenta, GMF);
    }

    @PutMapping("/{cuentaId}/actualizarSaldo")
    public void actualizarSaldo(@PathVariable int id, @RequestParam double nuevoSaldo) {
        cuentaService.actualizarSaldo(id, nuevoSaldo);
    }

    @PutMapping("/{cuentaId}/cambiarEstado")
    public void cambiarEstado(@PathVariable int id, @RequestParam String nuevoEstado) {
        cuentaService.cambiarEstado(id, nuevoEstado);
    }

    @GetMapping("/{cuentaId}")
    public Cuenta obtenerCuentaPorId(@PathVariable int id) {
        return cuentaService.obtenerCuentaPorId(id).orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
    }

}
