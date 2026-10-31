package poo.financeiro;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

public final class Lancamento {
  private final String id;
  private String descricao;
  private BigDecimal valor;
  private LocalDate data;

  public Lancamento(String id, String descricao, BigDecimal valor, LocalDate data) {
    if (id == null || id.isBlank()) throw new IllegalArgumentException("ID vazio");
    this.id = id;
    atualizar(descricao, valor, data);
  }

  private void atualizar(String descricao, BigDecimal valor, LocalDate data) {
    Objects.requireNonNull(descricao);
    Objects.requireNonNull(valor);
    Objects.requireNonNull(data);
    // Valida todos os argumentos antes de alterar o objeto.
    this.descricao = descricao;
    this.valor = valor;
    this.data = data;
  }

  public EstadoLancamento estado(Clock relogio) {
    if (valor.signum() < 0) return new Invalido();
    return data.isBefore(LocalDate.now(relogio)) ? new Executado() : new Planejado();
  }

  public void editar(String descricao, BigDecimal valor, LocalDate data, Clock relogio) {
    if (!estado(relogio).permiteEdicao())
      throw new UnsupportedOperationException("Lançamento executado é imutável");
    atualizar(descricao, valor, data);
  }

  public String id() {
    return id;
  }

  public String descricao() {
    return descricao;
  }

  public BigDecimal valor() {
    return valor;
  }

  public LocalDate data() {
    return data;
  }
}
