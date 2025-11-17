package com.projeto_backend.controle_gastos.mappers;

import com.projeto_backend.controle_gastos.dtos.TransacaoRequest;
import com.projeto_backend.controle_gastos.dtos.TransacaoResponse;
import com.projeto_backend.controle_gastos.models.Transacoes;

public class TransacaoMapper {

    public static Transacoes toEntity(TransacaoRequest dto) {
        Transacoes t = new Transacoes();
        t.setDescricao(dto.descricao());
        t.setValor(dto.valor());
        t.setData(dto.dataMovimentacao());
        t.setTipo(dto.tipo());
        t.setContaId(dto.contaId());
        t.setCategoriaId(dto.categoriaId());
        t.setUsuarioId(dto.usuarioId());

        return t;
    }

    public static TransacaoResponse toResponse(Transacoes t){
        return new TransacaoResponse(
                t.getId(),
                t.getDescricao(),
                t.getValor(),
                t.getData(),
                t.getTipo(),
                t.getCategoriaId(),
                t.getUsuarioId(),
                t.getContaId()
        );
    }
}
