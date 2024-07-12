package com.example.prueba.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cuenta")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean tipoCuenta;
    private int numeroCuenta;
    private String estado;
    private long saldo;
    private long GFM;
    private String fechaCreacion;
    private String fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}

