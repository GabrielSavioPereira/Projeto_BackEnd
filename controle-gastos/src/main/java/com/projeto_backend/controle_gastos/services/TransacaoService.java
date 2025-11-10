package com.projeto_backend.controle_gastos.services;

import com.projeto_backend.controle_gastos.Enums.TipoTransacao;
import com.projeto_backend.controle_gastos.dtos.TransacaoRequest;
import com.projeto_backend.controle_gastos.dtos.TransacaoResponse;
import com.projeto_backend.controle_gastos.mappers.TransacaoMapper;
import com.projeto_backend.controle_gastos.models.Transacoes;
import com.projeto_backend.controle_gastos.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository repository;

    public TransacaoResponse create(TransacaoRequest dto) {
        Transacoes transacoes= TransacaoMapper.toEntity(dto);
        Transacoes saved = repository.save(transacoes);
        return TransacaoMapper.toResponse(saved);
    }

    public Page<TransacaoResponse> getAll(UUID usuarioId, TipoTransacao tipo, LocalDate inicio, LocalDate fim, Pageable pageable) {
        Page<Transacoes> page = repository.findByUsuarioIdAndTipoAndData(usuarioId, tipo, inicio, fim, pageable);
        return page.map(TransacaoMapper::toResponse);
    }

    public TransacaoResponse getOne(UUID id) {
        Transacoes t = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Transação não encontrada"));
        return TransacaoMapper.toResponse(t);
    }

    public TransacaoResponse update(UUID id, TransacaoRequest dto) {
        Transacoes t = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Transação não encontrada"));

        t.setDescricao(dto.descricao());
        t.setValor(dto.valor());
        t.setData(dto.dataMovimentacao());
        t.setTipo(dto.tipo());
        t.setContaId(dto.contaId());
        t.setCategoriaId(dto.categoriaId());
        t.setUsuarioId(dto.usuarioId());

        return TransacaoMapper.toResponse(repository.save(t));
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
