package com.projeto_backend.controle_gastos.repositories;

import com.projeto_backend.controle_gastos.Enums.TipoTransacao;
import com.projeto_backend.controle_gastos.models.Transacoes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface TransacaoRepository extends JpaRepository<Transacoes, UUID> {
    Page<Transacoes> findByUsuarioIdAndTipoAndData(
            UUID usuarioId,
            TipoTransacao tipo,
            LocalDate dataInicio,
            LocalDate dateFim,
            Pageable pageable
    );
}
