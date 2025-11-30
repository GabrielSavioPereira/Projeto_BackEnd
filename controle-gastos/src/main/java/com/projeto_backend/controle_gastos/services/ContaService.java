package com.projeto_backend.controle_gastos.services;


import com.projeto_backend.controle_gastos.dtos.ContaRequestDto;
import com.projeto_backend.controle_gastos.dtos.ContaResponseDto;
import com.projeto_backend.controle_gastos.mappers.ContaMapper;
import com.projeto_backend.controle_gastos.models.Conta;
import com.projeto_backend.controle_gastos.models.Usuario;
import com.projeto_backend.controle_gastos.repositories.UsuarioRepository;
import com.projeto_backend.controle_gastos.repositories.contaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final contaRepository contaRepository;
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


}
