package com.example.prueba.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor

@Entity
@Table (name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tipoIdentificacion", length = 50, nullable = true)
    private String tipoIdentificacion;


    @Column(name = "numeroIdentificacion", length = 50, nullable = true)
    private int numeroIdentificacion;

    @Column(name = "nombre", length = 30, nullable = true)
    @Size(min = 1)
    private String nombre;

    @Column(name = "apellido", length = 50, nullable = true)
    @Size(min = 1)
    private String apellido;

    @Column(name = "correoElectronico", length = 100, nullable = true)
    @Email
    private String correoElectronico;

    @Column(name = "fechaNacimiento", length = 50, nullable = true)
    private String fechaNacimiento;

    @Column(name ="fechaCreacion", length = 50, nullable = true)
    private String fechaCreacion;

    @Column(nullable = false)
    private LocalDate fechaModificacion;

    @Column(nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Set<Cuenta> cuentas;

}

