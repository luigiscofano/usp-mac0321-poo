package poo.financeiro;

public sealed interface EstadoLancamento permits Planejado, Executado, Invalido {
  String nome();

  default boolean permiteEdicao() {
    return true;
  }

  default boolean persistivel() {
    return true;
  }
}
