package com.example.prueba.Repository;

import com.example.prueba.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IClienteRepository extends JpaRepository <Cliente, Integer> {
}
