package com.example.prueba.IService;

import com.example.prueba.Entity.Cuenta;

import java.util.Optional;

public interface ICuentaService {
    Cuenta crearCuenta(int id, String tipoCuenta, long GMF);
    void actualizarSaldo(int id, double nuevoSaldo);
    void cambiarEstado(int id, String nuevoEstado);
    Optional<Cuenta> obtenerCuentaPorId(int id);
}
