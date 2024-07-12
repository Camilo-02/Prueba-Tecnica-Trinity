package com.example.prueba.IService;

import com.example.prueba.Entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
     List<Cliente> listar();
     Optional<Cliente> listarId(int id);
     Cliente save (Cliente cliente);
     void delete(int id);
     void update(Cliente cliente, int id);
}
