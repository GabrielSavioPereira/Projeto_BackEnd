package com.projeto_backend.controle_gastos.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "conta")
public class Conta {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID idConta;

    @Setter
    @Getter
    @Column(name = "nome" , nullable = false)
    private String nomeConta;

    @Setter
    @Getter
    @Column(name = "saldo" ,nullable = false)
    private BigDecimal saldo;


    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;


}
