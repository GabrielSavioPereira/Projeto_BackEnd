package com.projeto_backend.controle_gastos.controllers;

import com.projeto_backend.controle_gastos.dtos.LoginRequest;
import com.projeto_backend.controle_gastos.dtos.UsuarioRequest;
import com.projeto_backend.controle_gastos.dtos.UsuarioResponse;
import com.projeto_backend.controle_gastos.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponse> registrar(@RequestBody UsuarioRequest dto) {
        return ResponseEntity.ok(service.registrar(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest dto) {
        boolean success = service.login(dto);
        if (success) return ResponseEntity.ok("Login realizado com sucesso");
        else return ResponseEntity.status(401).body("Credencias de login incorretas!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid UsuarioRequest dto) {
        return ResponseEntity.ok(service.atualizarUsuario(id, dto));
    }
}
