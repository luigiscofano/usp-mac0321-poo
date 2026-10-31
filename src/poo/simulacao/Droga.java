package poo.simulacao;

public record Droga(double reducaoTemperatura, int aumentoPac) {
  public Droga {
    if (!Double.isFinite(reducaoTemperatura) || reducaoTemperatura < 0 || aumentoPac < 0)
      throw new IllegalArgumentException("Parâmetros inválidos");
  }
}
