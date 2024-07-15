package com.example.prueba.service.IService;

import com.example.prueba.Entity.Cuenta;

import java.util.Optional;

public interface ICuentaService {
    Cuenta crearCuenta(long id, String tipoCuenta, long GMF);
    void actualizarSaldo(long id, double nuevoSaldo);
    void cambiarEstado(long id, String nuevoEstado);
    Optional<Cuenta> obtenerCuentaPorId(long id);
}
