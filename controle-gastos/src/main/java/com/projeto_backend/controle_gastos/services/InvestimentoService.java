package com.projeto_backend.controle_gastos.services;


import com.projeto_backend.controle_gastos.models.Investimento;
import com.projeto_backend.controle_gastos.repositories.InvestimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvestimentoService {

    private final InvestimentoRepository repository;

    public InvestimentoService(InvestimentoRepository repository) {
        this.repository = repository;
    }

    public List<Investimento> listarTodos(UUID usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public Optional<Investimento> buscarPorId(UUID id) {
        return repository.findById(id);
    }

    public Investimento salvar(Investimento investimento) {
        return repository.save(investimento);
    }

    public void deletar(UUID id) {
        repository.deleteById(id);
    }
}
