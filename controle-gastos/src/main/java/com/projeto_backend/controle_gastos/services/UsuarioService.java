package com.projeto_backend.controle_gastos.services;

import com.projeto_backend.controle_gastos.dtos.LoginRequest;
import com.projeto_backend.controle_gastos.dtos.UsuarioRequest;
import com.projeto_backend.controle_gastos.dtos.UsuarioResponse;
import com.projeto_backend.controle_gastos.mappers.UsuarioMapper;
import com.projeto_backend.controle_gastos.models.Usuario;
import com.projeto_backend.controle_gastos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioResponse registrar(UsuarioRequest dto){
        Usuario usuario = UsuarioMapper.toEntity(dto);
        repository.save(usuario);
        return UsuarioMapper.toResponse(usuario);
    }

    public boolean login(LoginRequest dto){
        Optional<Usuario> usuario = repository.findByEmail(dto.email());
        return usuario.isPresent() && usuario.get().getSenha().equals(dto.senha());
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
