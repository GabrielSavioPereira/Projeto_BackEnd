package com.projeto_backend.controle_gastos.mappers;


import com.projeto_backend.controle_gastos.dtos.ContaRequestDto;
import com.projeto_backend.controle_gastos.dtos.ContaResponseDto;
import com.projeto_backend.controle_gastos.models.Conta;
import com.projeto_backend.controle_gastos.models.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ContaMapper {

    public Conta toEntity(ContaRequestDto dto, Usuario usuario){
        Conta conta = new Conta();
        conta.setNomeConta(dto.getNomeConta());
        conta.setSaldo(dto.getSaldo());
        conta.setUsuario(usuario);
        return conta;
    }

    public ContaResponseDto toResponse(Conta conta){
        ContaResponseDto dto = new ContaResponseDto();
        dto.setIdConta(conta.getIdConta());
        dto.setNomeConta(conta.getNomeConta());
        dto.setSaldo(conta.getSaldo());
        dto.setUsuarioId(conta.getUsuario().getId());
        return dto;
    }
}
