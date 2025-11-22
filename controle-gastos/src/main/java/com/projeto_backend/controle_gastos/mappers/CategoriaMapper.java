package com.projeto_backend.controle_gastos.mappers;

import org.mapstruct.*;
import com.projeto_backend.controle_gastos.models.Categoria;
import com.projeto_backend.controle_gastos.models.Usuario;
import com.projeto_backend.controle_gastos.dtos.CategoriaRequestDTO;
import com.projeto_backend.controle_gastos.dtos.CategoriaResponseDTO;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    @Mapping(target = "usuario", expression = "java(usuarioFromId(dto.getUsuarioId()))")
    Categoria toEntity(CategoriaRequestDTO dto);

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "usuario.nome", target = "usuarioNome")
    CategoriaResponseDTO toDto(Categoria entity);

    default Usuario usuarioFromId(java.util.UUID id) {
        if (id == null) return null;
        Usuario u = new Usuario();
        u.setId(id);
        return u;
    }
}
