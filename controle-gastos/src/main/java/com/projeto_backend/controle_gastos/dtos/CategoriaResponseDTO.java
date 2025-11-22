package com.projeto_backend.controle_gastos.dtos;

import com.projeto_backend.controle_gastos.models.TipoCategoria;
import java.util.UUID;

public class CategoriaResponseDTO {

    private UUID id;
    private String nome;
    private TipoCategoria tipo;
    private UUID usuarioId;
    private String usuarioNome;

    // getters e setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public TipoCategoria getTipo() { return tipo; }
    public void setTipo(TipoCategoria tipo) { this.tipo = tipo; }
    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }
    public String getUsuarioNome() { return usuarioNome; }
    public void setUsuarioNome(String usuarioNome) { this.usuarioNome = usuarioNome; }
}
