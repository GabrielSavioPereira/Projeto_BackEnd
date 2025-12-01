package com.projeto_backend.controle_gastos.controllers;

import com.projeto_backend.controle_gastos.Enums.TipoTransacao;
import com.projeto_backend.controle_gastos.dtos.TransacaoRequest;
import com.projeto_backend.controle_gastos.dtos.TransacaoResponse;
import com.projeto_backend.controle_gastos.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping
    public ResponseEntity<TransacaoResponse> create(@RequestBody TransacaoRequest dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    public Page<TransacaoResponse> getAll(
            @RequestParam UUID usuarioId,
            @RequestParam(required = false) TipoTransacao tipo,
            @RequestParam(required = false) LocalDate inicio,
            @RequestParam(required = false) LocalDate fim,
            Pageable pageable
    ) {
        return service.getAll(usuarioId, tipo, inicio, fim, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoResponse> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransacaoResponse> update(@PathVariable UUID id, @RequestBody TransacaoRequest dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/importar")
    public ResponseEntity<?> importarPlanilha(@RequestParam("file") MultipartFile file) {
        int total = service.importarPlanilha(file);
        return ResponseEntity.ok("Importação concluida com sucesso!");
    }
}
