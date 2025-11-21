package com.projeto_backend.controle_gastos.mappers;
import com.projeto_backend.controle_gastos.dtos.InvestimentoDTO;
import com.projeto_backend.controle_gastos.models.Investimento;

public class InvestimentoMapper {

    public static Investimento toEntity(InvestimentoDTO dto) {
        if (dto == null) return null;
        Investimento investimento = new Investimento();
        investimento.setId(dto.getId());
        investimento.setNome(dto.getNome());
        investimento.setTipo(dto.getTipo());
        investimento.setValorAplicado(dto.getValorAplicado());
        investimento.setRendInvest(dto.getRendInvest());
        investimento.setDataAplicacao(dto.getDataAplicacao());
        investimento.setUsuarioId(dto.getUsuarioId());
        return investimento;
    }

    public static InvestimentoDTO toDto(Investimento investimento) {
        if (investimento == null) return null;
        InvestimentoDTO dto = new InvestimentoDTO();
        dto.setId(investimento.getId());
        dto.setNome(investimento.getNome());
        dto.setTipo(investimento.getTipo());
        dto.setValorAplicado(investimento.getValorAplicado());
        dto.setRendInvest(investimento.getRendInvest());
        dto.setDataAplicacao(investimento.getDataAplicacao());
        dto.setUsuarioId(investimento.getUsuarioId());
        return dto;
    }
}
