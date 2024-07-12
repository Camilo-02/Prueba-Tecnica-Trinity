package com.example.prueba.controller;


import com.example.prueba.Entity.Cuenta;
import com.example.prueba.IService.ICuentaService;
import com.example.prueba.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cuenta")
public class CuentaController {

    @Autowired
    private ICuentaService cuentaService;

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

    /*
    private final CuentaService cuentaService;

    @PostMapping("")
    public Cuenta createCuenta(@RequestBody Cuenta cuenta) { return cuentaService.crearCuenta(cuenta.getId(), cuenta.getTipoCuenta(), cuenta.getGMF());}

    /*
    @PutMapping("/{id}")
    public void actualizarSaldo(@RequestBody Cuenta cuenta,@PathVariable int id) { cuentaService.actualizarSaldo(cuenta.getId(), cuenta.getNuevoSaldo());}

    @PutMapping("/{id}")
    public void cambiarEstado (@RequestBody Cuenta cuenta, @PathVariable int id) { cuentaService.cambiarEstado(cuenta.getId(), cuenta.getEstado());}

    @GetMapping("/{id}")
    public Optional<Cuenta> obtenerCuentaPorId(@PathVariable int id){ return cuentaService.obtenerCuentaPorId(id);}
  */

}
