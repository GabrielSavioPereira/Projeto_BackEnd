package com.projeto_backend.controle_gastos.mappers;

import com.projeto_backend.controle_gastos.dtos.UsuarioRequest;
import com.projeto_backend.controle_gastos.dtos.UsuarioResponse;
import com.projeto_backend.controle_gastos.models.Usuario;

public class  UsuarioMapper {

    public static Usuario toEntity(UsuarioRequest dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setCpf(dto.cpf());
        usuario.setDataNasc(dto.dataNasc());
        return usuario;
    }

    public static UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getDataNasc()
        );
    }
}
