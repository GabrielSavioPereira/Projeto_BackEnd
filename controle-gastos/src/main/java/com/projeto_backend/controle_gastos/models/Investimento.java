package com.projeto_backend.controle_gastos.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "investimentos")
public class Investimento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoInvestimento tipo;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorAplicado;

    @Column(precision = 10, scale = 2)
    private BigDecimal rendInvest;

    private LocalDate dataAplicacao;

    @Column(name = "usuario_id")
    private UUID usuarioId;

    // Getters e Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public TipoInvestimento getTipo() { return tipo; }
    public void setTipo(TipoInvestimento tipo) { this.tipo = tipo; }

    public BigDecimal getValorAplicado() { return valorAplicado; }
    public void setValorAplicado(BigDecimal valorAplicado) { this.valorAplicado = valorAplicado; }

    public BigDecimal getRendInvest() { return rendInvest; }
    public void setRendInvest(BigDecimal rendInvest) { this.rendInvest = rendInvest; }

    public LocalDate getDataAplicacao() { return dataAplicacao; }
    public void setDataAplicacao(LocalDate dataAplicacao) { this.dataAplicacao = dataAplicacao; }

    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }
}
