package com.example.prueba.controller;


import com.example.prueba.Entity.Cliente;
import com.example.prueba.IService.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cliente")
public class ClienteController {

    private final IClienteService clienteService;

    @PostMapping("")
    public Cliente save(@RequestBody Cliente cliente) { return clienteService.save(cliente);}


    @PutMapping("/{id}")
    public void update(@RequestBody Cliente cliente, @PathVariable int id) { clienteService.update(cliente, id);}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) { clienteService.delete(id);}

    @GetMapping("")
    public List<Cliente> findAll(){return clienteService.listar();}

    @GetMapping("/{id}")
    public Optional<Cliente> findById(@PathVariable int id) {return clienteService.listarId(id);}
}

