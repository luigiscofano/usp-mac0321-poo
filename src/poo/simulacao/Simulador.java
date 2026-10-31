package poo.simulacao;

import java.util.*;
import poo.eventos.*;

public final class Simulador {
  public static List<String> executar(int duracao, boolean medicar) {
    if (duracao < 0 || duracao > 100000)
      throw new IllegalArgumentException("Duração fora do limite");
    Controlador c = new Controlador();
    Paciente p = new Paciente();
    Medico m = new Medico();
    Droga d = new Droga(2, 30000);
    List<String> log = new ArrayList<>();
    // Cada minuto: surto, consulta e depois avanço do relógio fisiológico fictício.
    for (int t = 0; t <= duracao; t++) {
      final int minuto = t;
      c.inserir(
          new Evento(t) {
            public void executar(Controlador ctrl) {
              if (!p.vivo()) return;
              if (minuto > 0 && minuto % 120 == 0) {
                p.surto(5, 60000);
                log.add(minuto + " surto");
              }
              if (minuto % 10 == 0) {
                boolean aplicou = medicar && m.consultar(p, d);
                log.add(
                    String.format(
                        Locale.ROOT,
                        "%d T=%.1f PAC=%d medicou=%s",
                        minuto,
                        p.temperatura(),
                        p.pac(),
                        aplicou));
              }
              if (minuto < duracao) {
                p.avancarMinuto();
                if (!p.vivo()) log.add((minuto + 1) + " obito no modelo");
              }
            }
          });
    }
    c.executarAte(duracao);
    return List.copyOf(log);
  }

  public static void main(String[] args) {
    executar(300, true).forEach(System.out::println);
  }
}
