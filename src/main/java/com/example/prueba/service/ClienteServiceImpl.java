package com.example.prueba.service;

import com.example.prueba.Entity.Cliente;
import com.example.prueba.service.IService.IClienteService;
import com.example.prueba.Repository.IClienteRepository;
import com.example.prueba.Repository.ICuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {

    private final IClienteRepository iClienteRepository;
    private final ICuentaRepository iCuentaRepository;



    public List<Cliente> listar() {
        return (List<Cliente>) iClienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> listarId(long id) {
        return iClienteRepository.findById(id);
    }

    //--------------------------------------------------------------------------------
    @Override
    public Cliente save(Cliente cliente) {
        if (isAdult(cliente.getDate())) {
            return iClienteRepository.save(cliente);
        } else {
            throw new IllegalArgumentException("El cliente debe ser mayor de edad.");
        }
    }

    private boolean isAdult(LocalDate date) {
        return Period.between(date, LocalDate.now()).getYears() >= 18;
    }


    //----------------------------------------------------------------------------------
    public void delete(long id) {

        Optional<Cliente> optionalCliente = iClienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();

            if (existsByCliente(id)) {
                throw new IllegalArgumentException("El cliente tiene cuentas vinculadas y no puede ser eliminado.");
            } else {
                iClienteRepository.delete(cliente);
            }
        } else {
            throw new IllegalArgumentException("Cliente no encontrado.");
        }
    }


    //--------------------------------------------------------------------------------
        @Override
        public void update (Cliente cliente,long id){
            Optional<Cliente> optional = iClienteRepository.findById(id);

            if (optional.isPresent()) {
                Cliente clienteUpdate = optional.get();
                clienteUpdate.setTipoIdentificacion(cliente.getTipoIdentificacion());
                clienteUpdate.setNumeroIdentificacion(cliente.getNumeroIdentificacion());
                clienteUpdate.setNombre(cliente.getNombre());
                clienteUpdate.setApellido(cliente.getApellido());
                clienteUpdate.setCorreoElectronico(cliente.getCorreoElectronico());
                clienteUpdate.setFechaNacimiento(cliente.getFechaNacimiento());
                clienteUpdate.setFechaCreacion(cliente.getFechaCreacion());
                clienteUpdate.setFechaModificacion(cliente.getFechaModificacion());
                iClienteRepository.save(clienteUpdate);
            } else {
                throw new RuntimeException(("No existe registro actual"));
            }
        }

    public boolean existsByCliente(Long idCliente) {
        Optional<Cliente> optionalCliente = iClienteRepository.findById(idCliente);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            return iCuentaRepository.countByCliente(cliente) > 0;
        } else {
            throw new IllegalArgumentException("Cliente no encontrado con ID: " + idCliente);
        }
    }

}
