package com.projeto_backend.controle_gastos.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nome,
        String email,
        String senha,
        String cpf,
        LocalDate dataNasc
) {
}
