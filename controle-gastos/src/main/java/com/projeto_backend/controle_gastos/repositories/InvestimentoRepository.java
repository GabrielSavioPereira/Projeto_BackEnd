package com.projeto_backend.controle_gastos.repositories;

import java.util.*;
import com.projeto_backend.controle_gastos.models.Investimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestimentoRepository extends JpaRepository<Investimento, UUID> {
    List<Investimento> findByUsuarioId(UUID usuarioId);
}
