package com.example.prueba.Repository;

import com.example.prueba.Entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransaccionRepository extends JpaRepository <Transaccion, Long> {
}
