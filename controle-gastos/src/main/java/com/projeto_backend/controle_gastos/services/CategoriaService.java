package com.projeto_backend.controle_gastos.services;

import com.projeto_backend.controle_gastos.dtos.CategoriaRequestDTO;
import com.projeto_backend.controle_gastos.dtos.CategoriaResponseDTO;
import com.projeto_backend.controle_gastos.mappers.CategoriaMapper;
import com.projeto_backend.controle_gastos.models.Categoria;
import com.projeto_backend.controle_gastos.models.Usuario;
import com.projeto_backend.controle_gastos.repositories.CategoriaRepository;
import com.projeto_backend.controle_gastos.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaMapper mapper;

    public CategoriaService(CategoriaRepository categoriaRepository,
                            UsuarioRepository usuarioRepository,
                            CategoriaMapper mapper) {
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> listarPorUsuario(UUID usuarioId) {
        return categoriaRepository.findAllByUsuarioId(usuarioId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public java.util.Optional<CategoriaResponseDTO> buscarPorId(UUID id) {
        return categoriaRepository.findById(id).map(mapper::toDto);
    }

    @Transactional
    public CategoriaResponseDTO salvar(CategoriaRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado: " + dto.getUsuarioId()));

        Categoria categoria = mapper.toEntity(dto);
        categoria.setUsuario(usuario);

        Categoria salvo = categoriaRepository.save(categoria);
        return mapper.toDto(salvo);
    }

    @Transactional
    public void deletar(UUID id) {
        categoriaRepository.deleteById(id);
    }
}
