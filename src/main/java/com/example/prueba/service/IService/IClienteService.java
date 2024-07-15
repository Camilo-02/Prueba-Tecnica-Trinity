package com.example.prueba.service.IService;

import com.example.prueba.Entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
     List<Cliente> listar();
     Optional<Cliente> listarId(long id);
     Cliente save (Cliente cliente);
     void delete(long id);
     void update(Cliente cliente, long id);
}
