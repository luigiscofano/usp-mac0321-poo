import java.math.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;
import poo.colecoes.*;
import poo.eventos.*;
import poo.financeiro.*;
import poo.simulacao.*;

public final class PooTest {
  static void exigir(boolean c) {
    if (!c) throw new AssertionError();
  }

  static void falha(Class<? extends Throwable> tipo, Runnable acao) {
    try {
      acao.run();
    } catch (Throwable e) {
      if (tipo.isInstance(e)) return;
      throw new AssertionError(e);
    }
    throw new AssertionError("Não falhou");
  }

  public static void main(String[] args) throws Exception {
    List<Integer> ordem = new ArrayList<>();
    Controlador c = new Controlador();
    for (int i : new int[] {3, 1, 2}) {
      final int v = i;
      c.inserir(
          new Evento(5) {
            public void executar(Controlador x) {
              ordem.add(v);
            }
          });
    }
    c.executarAte(5);
    exigir(ordem.equals(List.of(3, 1, 2)));
    falha(IllegalArgumentException.class, () -> c.executarAte(4));
    falha(
        IllegalArgumentException.class,
        () ->
            c.inserir(
                new Evento(4) {
                  public void executar(Controlador x) {}
                }));
    Paciente p = new Paciente();
    p.surto(5, 60000);
    for (int i = 0; i < 29; i++) p.avancarMinuto();
    exigir(p.vivo());
    p.avancarMinuto();
    exigir(!p.vivo());
    p = new Paciente();
    p.surto(5, 0);
    for (int i = 0; i < 60; i++) p.avancarMinuto();
    exigir(p.vivo());
    p.avancarMinuto();
    exigir(!p.vivo());
    Paciente tratado = new Paciente();
    tratado.surto(5, 60000);
    exigir(new Medico().consultar(tratado, new Droga(2, 30000)));
    for (int i = 0; i < 100; i++) tratado.avancarMinuto();
    exigir(tratado.vivo());
    exigir(Simulador.executar(300, true).equals(Simulador.executar(300, true)));
    Clock clock = Clock.fixed(Instant.parse("2026-10-31T12:00:00Z"), ZoneOffset.UTC);
    LocalDate hoje = LocalDate.now(clock);
    Lancamento l = new Lancamento("1", "Vírgula, aspas \" e\nlinha", new BigDecimal("12.30"), hoje);
    exigir(l.estado(clock) instanceof Planejado);
    l.editar(l.descricao(), new BigDecimal("-1"), hoje, clock);
    exigir(l.estado(clock) instanceof Invalido);
    l.editar(l.descricao(), new BigDecimal("12.30"), hoje.minusDays(1), clock);
    exigir(l.estado(clock) instanceof Executado);
    falha(UnsupportedOperationException.class, () -> l.editar("x", BigDecimal.ONE, hoje, clock));
    Path temp = Files.createTempFile("poo-test-", ".csv");
    try {
      LancamentosCsv.salvar(
          temp, List.of(l, new Lancamento("2", "Inválido", new BigDecimal("-1"), hoje)), clock);
      List<Lancamento> copia = LancamentosCsv.carregar(temp);
      exigir(
          copia.size() == 1
              && copia.get(0).descricao().equals(l.descricao())
              && copia.get(0).valor().equals(l.valor()));
    } finally {
      Files.delete(temp);
    }
    for (List<String> campos :
        List.of(List.of("", ","), List.of("a\nb", "\"", "á"), List.of("x", "")))
      exigir(Csv.ler(Csv.linha(campos)).equals(List.of(campos)));
    falha(IllegalArgumentException.class, () -> Csv.ler("\"x"));
    falha(IllegalArgumentException.class, () -> Csv.ler("\"x\"y"));
    Multiconjunto<String> a = new Multiconjunto<>(), b = new Multiconjunto<>();
    a.adicionar("x");
    b.adicionar("x");
    exigir(a.equals(b));
    a.adicionar("x");
    exigir(!a.equals(b) && a.quantidade("x") == 2);
    System.out.println(
        "PASS: eventos estaveis, limites temporais, simulacao deterministica, State, CSV e"
            + " multiplicidades");
  }
}
