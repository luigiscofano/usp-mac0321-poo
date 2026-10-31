package poo.financeiro;

public final class Invalido implements EstadoLancamento {
  public String nome() {
    return "Invalido";
  }

  public boolean persistivel() {
    return false;
  }
}
