package com.projeto_backend.controle_gastos.controllers;

import com.projeto_backend.controle_gastos.models.Investimento;
import com.projeto_backend.controle_gastos.services.InvestimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/investimentos")
public class InvestimentoController {

    private final InvestimentoService service;

    public InvestimentoController(InvestimentoService service) {
        this.service = service;
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<Investimento>> listarPorUsuario(@PathVariable UUID usuarioId) {
        return ResponseEntity.ok(service.listarTodos(usuarioId));
    }

    @GetMapping("/detalhe/{id}")
    public ResponseEntity<Investimento> buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Investimento> salvar(@RequestBody Investimento investimento) {
        return ResponseEntity.ok(service.salvar(investimento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
