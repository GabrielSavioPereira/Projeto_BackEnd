package com.projeto_backend.controle_gastos.services;

import com.projeto_backend.controle_gastos.dtos.LoginRequest;
import com.projeto_backend.controle_gastos.dtos.UsuarioRequest;
import com.projeto_backend.controle_gastos.dtos.UsuarioResponse;
import com.projeto_backend.controle_gastos.mappers.UsuarioMapper;
import com.projeto_backend.controle_gastos.models.Usuario;
import com.projeto_backend.controle_gastos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UUID registrar(UsuarioRequest dto){
        Usuario usuario = UsuarioMapper.toEntity(dto);
        repository.save(usuario);
        return usuario.getId();
    }

    public UsuarioResponse login(LoginRequest dto){

        Usuario usuario = repository.findByEmail(dto.email());

        if(usuario == null){
            return null;
        }

        if (!usuario.getSenha().equals(dto.senha())) {
            return null;
        }

        return UsuarioMapper.toResponse(usuario);

    }

    public UsuarioResponse atualizarUsuario(UUID id, UsuarioRequest dto){
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setCpf(dto.cpf());
        usuario.setDataNasc(dto.dataNasc());
        repository.save(usuario);
        return UsuarioMapper.toResponse(usuario);
    }

}
