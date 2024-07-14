package com.example.prueba.Repository;

import com.example.prueba.Entity.Cliente;
import com.example.prueba.Entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ICuentaRepository extends JpaRepository <Cuenta, Integer> {
    static boolean existsByCliente(Cliente cliente) {
        return false;
    }
}
