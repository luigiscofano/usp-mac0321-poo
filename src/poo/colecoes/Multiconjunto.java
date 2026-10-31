package poo.colecoes;

import java.util.*;

/** Diferente do HashSet do acervo: aqui a multiplicidade faz parte da igualdade. */
public final class Multiconjunto<T> {
  private final Map<T, Integer> frequencias = new HashMap<>();

  public void adicionar(T item) {
    frequencias.merge(Objects.requireNonNull(item), 1, Math::addExact);
  }

  public int quantidade(T item) {
    return frequencias.getOrDefault(item, 0);
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Multiconjunto<?> m && frequencias.equals(m.frequencias);
  }

  @Override
  public int hashCode() {
    return frequencias.hashCode();
  }
}
