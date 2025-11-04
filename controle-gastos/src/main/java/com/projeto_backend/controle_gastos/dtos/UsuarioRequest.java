package com.projeto_backend.controle_gastos.dtos;

import java.time.LocalDate;

public record UsuarioRequest(
        String nome,
        String email,
        String senha,
        String cpf,
        LocalDate dataNasc
) {
}
