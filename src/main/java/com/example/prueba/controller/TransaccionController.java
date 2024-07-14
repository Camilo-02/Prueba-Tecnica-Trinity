package com.example.prueba.controller;


import com.example.prueba.Entity.Transaccion;
import com.example.prueba.service.TransaccionService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transacciones")
public class TransaccionController {

    private final TransaccionService transaccionService;

    @PostMapping("/transacciones")
    public ResponseEntity<Transaccion> realizarTransferencia(
            @RequestParam Long cuentaOrigenId,
            @RequestParam Long cuentaDestinoId,
            @RequestParam Double monto) {
        Transaccion transaccion = transaccionService.realizarTransferencia(cuentaOrigenId, cuentaDestinoId, monto);
        return new ResponseEntity<>(transaccion, HttpStatus.OK);
    }
}

