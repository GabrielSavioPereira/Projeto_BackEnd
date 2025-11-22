package com.projeto_backend.controle_gastos.dtos;

import jakarta.validation.constraints.*;
import com.projeto_backend.controle_gastos.models.TipoCategoria;
import java.util.UUID;

public class CategoriaRequestDTO {

    @NotBlank
    private String nome;

    @NotNull
    private TipoCategoria tipo;

    @NotNull
    private UUID usuarioId;

    // getters e setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public TipoCategoria getTipo() { return tipo; }
    public void setTipo(TipoCategoria tipo) { this.tipo = tipo; }
    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }
}