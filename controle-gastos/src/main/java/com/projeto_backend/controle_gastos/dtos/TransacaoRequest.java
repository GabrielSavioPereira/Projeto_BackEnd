package com.projeto_backend.controle_gastos.dtos;

import com.projeto_backend.controle_gastos.Enums.TipoTransacao;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransacaoRequest(
         String descricao,
         BigDecimal valor,
         TipoTransacao tipo,
         LocalDate dataMovimentacao,
         UUID contaId,
         UUID categoriaId,
         UUID usuarioId
        ) {
}
