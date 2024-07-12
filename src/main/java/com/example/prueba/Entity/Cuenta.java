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

    @Column(name = "tipoCuenta", length = 17, nullable = false )
    private String tipoCuenta;

    @Column(name = "numeroCuenta", length = 9, nullable = false )
    private int numeroCuenta;

    @Column ( name = "estado", length = 20, nullable = false )
    private String estado;

    @Column (name = "saldo", length = 50, nullable = false )
    private long saldo;

    @Column ( name = "GMF", length = 50, nullable = false )
    private long GMF;

    @Column(name = "fechaCreacion", length = 50, nullable = false )
    private String fechaCreacion;

    @Column(name ="fechaMoficacion", length = 50, nullable = false )
    private String fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}

