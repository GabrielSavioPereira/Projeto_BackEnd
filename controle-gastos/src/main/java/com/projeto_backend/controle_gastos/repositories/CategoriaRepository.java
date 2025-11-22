package com.projeto_backend.controle_gastos.repositories;

import com.projeto_backend.controle_gastos.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
    List<Categoria> findAllByUsuarioId(UUID usuarioId);
}
