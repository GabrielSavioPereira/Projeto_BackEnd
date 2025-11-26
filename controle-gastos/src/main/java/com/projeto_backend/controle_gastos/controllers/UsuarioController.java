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
    public ResponseEntity<UUID> registrar(@RequestBody UsuarioRequest dto) {
        return ResponseEntity.ok(service.registrar(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest dto) {
        try {
            UsuarioResponse response = service.login(dto);

            if (response == null) {
                return ResponseEntity
                        .status(401)
                        .body("Credenciais de login incorretas!");
            }

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body("Erro interno no servidor: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable UUID id, @RequestBody @Valid UsuarioRequest dto) {
        return ResponseEntity.ok(service.atualizarUsuario(id, dto));
    }
}
