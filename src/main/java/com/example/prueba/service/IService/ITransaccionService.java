package com.example.prueba.service.IService;

import com.example.prueba.Entity.Transaccion;

public interface ITransaccionService {
    Transaccion realizarTransferencia(Long cuentaOrigenId, Long cuentaDestinoId, Double monto);
}

