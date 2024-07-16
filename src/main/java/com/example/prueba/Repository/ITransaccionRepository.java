package com.example.prueba.Repository;

import com.example.prueba.Entity.Transaccion;
import org.springframework.data.repository.CrudRepository;

public interface ITransaccionRepository extends CrudRepository<Transaccion, Long> {
}
