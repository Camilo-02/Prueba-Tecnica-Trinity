package com.example.prueba.Repository;

import com.example.prueba.Entity.Cliente;
import com.example.prueba.Entity.Cuenta;
import org.springframework.data.repository.CrudRepository;

public interface ICuentaRepository extends CrudRepository<Cuenta, Long> {
    long countByCliente(Cliente cliente);

}
