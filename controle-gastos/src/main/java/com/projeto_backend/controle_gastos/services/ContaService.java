package com.projeto_backend.controle_gastos.services;


import com.projeto_backend.controle_gastos.dtos.ContaDeleteResponseDto;
import com.projeto_backend.controle_gastos.dtos.ContaRequestDto;
import com.projeto_backend.controle_gastos.dtos.ContaResponseDto;
import com.projeto_backend.controle_gastos.mappers.ContaMapper;
import com.projeto_backend.controle_gastos.models.Conta;
import com.projeto_backend.controle_gastos.models.Usuario;
import com.projeto_backend.controle_gastos.repositories.UsuarioRepository;
import com.projeto_backend.controle_gastos.repositories.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ContaMapper contaMapper;

    // cadastrar conta

    public ContaResponseDto criarConta(UUID usuarioId, ContaRequestDto dto){

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        Conta conta = contaMapper.toEntity(dto, usuario);

        contaRepository.save(conta);

        return contaMapper.toResponse(conta);
    }

    // listar contas

    public List<ContaResponseDto> listarContas(UUID usuarioId){

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        List<Conta> contas = contaRepository.findByUsuario(usuario);

        return contas.stream()
                .map(contaMapper::toResponse)
                .toList();
    }

    // Calcular saldo Total

    public BigDecimal calcularSaldo(UUID usuarioId){

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        List<Conta> contas = contaRepository.findByUsuario(usuario);

        return contas.stream()
                .map(Conta::getSaldo)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public ContaDeleteResponseDto deletarConta(UUID usuarioId, UUID contaId){


        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Busca a conta pelo ID e va o dono
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));


        if (!conta.getUsuario().getId().equals(usuario.getId())) {
            throw new RuntimeException("A conta não pertence a este usuário");
        }


        ContaDeleteResponseDto dto = new ContaDeleteResponseDto();
        dto.setIdConta(conta.getIdConta());
        dto.setNomeConta(conta.getNomeConta());
        dto.setSaldo(conta.getSaldo());
        dto.setIdUsuario(usuario.getId());
        dto.setNomeUsuario(usuario.getNome());


        contaRepository.delete(conta);

        return dto;
    }

}
