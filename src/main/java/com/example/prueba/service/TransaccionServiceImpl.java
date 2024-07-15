package com.example.prueba.service;

import com.example.prueba.Entity.Cuenta;
import com.example.prueba.Entity.Transaccion;
import com.example.prueba.Repository.ICuentaRepository;
import com.example.prueba.Repository.ITransaccionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransaccionServiceImpl {

    private final ITransaccionRepository transaccionRepository;
    private final ICuentaRepository productoRepository;

    @Transactional
    public Transaccion realizarTransferencia(Long cuentaOrigenId, Long cuentaDestinoId, Double monto) {
        Cuenta cuentaOrigen = productoRepository.findById(cuentaOrigenId)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta origen no encontrada"));
        Cuenta cuentaDestino = productoRepository.findById(cuentaDestinoId)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta destino no encontrada"));

        if (cuentaOrigen.getSaldo() < monto) {
            throw new InsufficientFundsException("Fondos insuficientes en la cuenta origen");
        }

        cuentaOrigen.setSaldo((long) (cuentaOrigen.getSaldo() - monto));
        cuentaDestino.setSaldo((long) (cuentaDestino.getSaldo() + monto));

        Transaccion transaccionOrigen = new Transaccion();
        transaccionOrigen.setTipo("AHORRO");
        transaccionOrigen.setMonto(monto);
        transaccionOrigen.setFecha(LocalDateTime.now());
        transaccionOrigen.setCuentaOrigen(cuentaOrigen);
        transaccionOrigen.setCuentaDestino(cuentaDestino);

        Transaccion transaccionDestino = new Transaccion();
        transaccionDestino.setTipo("CREDITO");
        transaccionDestino.setMonto(monto);
        transaccionDestino.setFecha(LocalDateTime.now());
        transaccionDestino.setCuentaOrigen(cuentaDestino);
        transaccionDestino.setCuentaDestino(cuentaOrigen);

        productoRepository.save(cuentaOrigen);
        productoRepository.save(cuentaDestino);
        transaccionRepository.save(transaccionOrigen);
        transaccionRepository.save(transaccionDestino);

        return transaccionOrigen;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public class InsufficientFundsException extends RuntimeException {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

}
