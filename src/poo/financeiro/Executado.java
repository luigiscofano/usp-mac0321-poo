package poo.financeiro;

public final class Executado implements EstadoLancamento {
  public String nome() {
    return "Executado";
  }

  public boolean permiteEdicao() {
    return false;
  }
}
