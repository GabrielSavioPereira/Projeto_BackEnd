package com.projeto_backend.controle_gastos.dtos;

import com.projeto_backend.controle_gastos.models.TipoInvestimento;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class InvestimentoDTO {

    private UUID id;

    @NotBlank(message = "O nome do ativo é obrigatório.")
    private String nome;

    @NotNull(message = "O tipo do investimento é obrigatório.")
    private TipoInvestimento tipo;

    @NotNull(message = "O valor aplicado é obrigatório.")
    @DecimalMin(value = "0.01", message = "O valor aplicado deve ser maior que zero.")
    private BigDecimal valorAplicado;

    @NotNull(message = "O rendimento é obrigatório.")
    @DecimalMin(value = "0.00", message = "O rendimento não pode ser negativo.")
    private BigDecimal rendInvest;

    @NotNull(message = "A data de aplicação é obrigatória.")
    private LocalDate dataAplicacao;

    @NotNull(message = "O ID do usuário é obrigatório.")
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
