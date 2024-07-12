package com.example.prueba.service;

import com.example.prueba.Entity.Cliente;
import com.example.prueba.Entity.Cuenta;
import com.example.prueba.IService.ICuentaService;
import com.example.prueba.Repository.IClienteRepository;
import com.example.prueba.Repository.ICuentaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CuentaService implements ICuentaService {

    private final ICuentaRepository cuentaRepository;
    private final IClienteRepository clienteRepository;

    @Transactional
    public Cuenta crearCuenta(int id, String tipoCuenta, long GMF) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        if (!tipoCuenta.equals("AHORRO") && !tipoCuenta.equals("CORRIENTE")) {
            throw new IllegalArgumentException("Tipo de cuenta inválido");
        }

        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta(tipoCuenta);
        cuenta.setNumeroCuenta(Integer.parseInt(generarNumeroCuenta(tipoCuenta)));
        cuenta.setEstado("ACTIVA");
        cuenta.setSaldo(0);
        cuenta.setGMF(GMF);
        cuenta.setFechaCreacion(String.valueOf(LocalDateTime.now()));
        cuenta.setFechaModificacion(String.valueOf(LocalDateTime.now()));
        cuenta.setCliente(cliente);

        return cuentaRepository.save(cuenta);
    }


    public String generarNumeroCuenta(String tipoCuenta) {
        String prefix = tipoCuenta.equals("AHORRO") ? "53" : "33";
        StringBuilder numeroCuenta = new StringBuilder(prefix);
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            numeroCuenta.append(random.nextInt(10));
        }
        return numeroCuenta.toString();
    }

    @Override
    public void actualizarSaldo(int id, double nuevoSaldo) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        if (cuenta.getTipoCuenta().equals("AHORRO") && nuevoSaldo < 0) {
            throw new IllegalArgumentException("El saldo de una cuenta de ahorros no puede ser menor a $0");
        }

        cuenta.setSaldo((long) nuevoSaldo);
        cuenta.setFechaModificacion(String.valueOf(LocalDateTime.now()));
        cuentaRepository.save(cuenta);
    }

    @Override
    public void cambiarEstado(int id, String nuevoEstado) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        if (!nuevoEstado.equals("ACTIVA") && !nuevoEstado.equals("INACTIVA") && !nuevoEstado.equals("CANCELADA")) {
            throw new IllegalArgumentException("Estado inválido");
        }

        cuenta.setEstado(nuevoEstado);
        cuenta.setFechaModificacion(String.valueOf(LocalDateTime.now()));
        cuentaRepository.save(cuenta);
    }

    @Override
    public Optional<Cuenta> obtenerCuentaPorId(int id) {
        return cuentaRepository.findById(id);
    }
}
