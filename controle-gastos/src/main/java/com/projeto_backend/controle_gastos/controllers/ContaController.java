package com.projeto_backend.controle_gastos.controllers;

import com.projeto_backend.controle_gastos.dtos.ContaRequestDto;
import com.projeto_backend.controle_gastos.dtos.ContaResponseDto;
import com.projeto_backend.controle_gastos.services.ContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }


    // criar conta
    @PostMapping("/{usuarioId}")
    public ResponseEntity<ContaResponseDto> criarConta(
            @PathVariable UUID usuarioId,
            @RequestBody ContaRequestDto dto) {

        ContaResponseDto contaCriada = contaService.criarConta(usuarioId, dto);

        return ResponseEntity.ok(contaCriada);
    }

    // listar contas

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<ContaResponseDto>> ListarContas(
            @PathVariable UUID usuarioId) {
        List<ContaResponseDto> contas = contaService.listarContas(usuarioId);

        return ResponseEntity.ok(contas);
    }

    // consultar saldo

    @GetMapping("/{usuarioId}/saldo-total")
    public ResponseEntity<BigDecimal> calcularSaldo(
            @PathVariable UUID usuarioId) {

        BigDecimal saldo = contaService.calcularSaldo(usuarioId);

        return ResponseEntity.ok(saldo);
    }
}
