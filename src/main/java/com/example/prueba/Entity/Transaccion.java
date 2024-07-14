package com.example.prueba.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="transaccion")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private Double monto;
    private LocalDateTime fecha;

    @ManyToOne
    private Cuenta cuentaOrigen;

    @ManyToOne
    private Cuenta cuentaDestino;

}
