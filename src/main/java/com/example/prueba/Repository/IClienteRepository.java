package com.example.prueba.Repository;

import com.example.prueba.Entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteRepository extends CrudRepository<Cliente, Long> {

}
