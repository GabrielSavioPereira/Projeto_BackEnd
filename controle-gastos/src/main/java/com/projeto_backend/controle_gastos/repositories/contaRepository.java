package com.projeto_backend.controle_gastos.repositories;

import com.projeto_backend.controle_gastos.models.Conta;
import com.projeto_backend.controle_gastos.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface contaRepository extends JpaRepository<Conta, UUID> {


    // busca todas as contas de um usuario
    List<Conta> findByUsuario(Usuario usuario);

    // busca contas pelo nome
    List<Conta> findByNomeConta(String nomeConta);

    // busca a conta pelo id e usuario
    Optional<Conta> findByIdContaAndUsuario(UUID idConta, Usuario usuario);
}
