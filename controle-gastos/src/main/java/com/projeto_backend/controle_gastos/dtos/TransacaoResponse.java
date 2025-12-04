package com.projeto_backend.controle_gastos.dtos;

import com.projeto_backend.controle_gastos.Enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransacaoResponse(
        UUID id,
        String descricao,
        BigDecimal valor,
        LocalDate data,
        TipoTransacao tipo,
        UUID contaId,
        UUID categoriaId,
        UUID usuarioId) {}
