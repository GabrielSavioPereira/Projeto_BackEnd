package com.projeto_backend.controle_gastos.dtos;

import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.util.UUID;


@Getter
@Setter
public class ContaDeleteResponseDto {
     private UUID idConta;
     private String nomeConta;
     private BigDecimal saldo;

     private UUID idUsuario;
     private String nomeUsuario;
}
