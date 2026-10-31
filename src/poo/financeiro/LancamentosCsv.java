package poo.financeiro;

import java.io.*;
import java.math.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.*;
import java.util.*;

public final class LancamentosCsv {
  private static final List<String> CABECALHO = List.of("id", "descricao", "valor", "data");

  public static void salvar(Path destino, List<Lancamento> itens, Clock relogio)
      throws IOException {
    Set<String> ids = new HashSet<>();
    StringBuilder s = new StringBuilder(Csv.linha(CABECALHO));
    for (Lancamento l : itens) {
      if (!ids.add(l.id())) throw new IllegalArgumentException("ID duplicado");
      if (l.estado(relogio).persistivel())
        s.append(
            Csv.linha(
                List.of(l.id(), l.descricao(), l.valor().toPlainString(), l.data().toString())));
    }
    Path absoluto = destino.toAbsolutePath();
    Path temp = Files.createTempFile(absoluto.getParent(), "ledger-", ".tmp");
    try {
      Files.writeString(temp, s, StandardCharsets.UTF_8);
      try {
        Files.move(
            temp, absoluto, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
      } catch (AtomicMoveNotSupportedException e) {
        Files.move(temp, absoluto, StandardCopyOption.REPLACE_EXISTING);
      }
    } finally {
      Files.deleteIfExists(temp);
    }
  }

  public static List<Lancamento> carregar(Path origem) throws IOException {
    String s = Files.readString(origem, StandardCharsets.UTF_8);
    if (s.startsWith("\ufeff")) s = s.substring(1);
    List<List<String>> rows = Csv.ler(s);
    if (rows.isEmpty() || !rows.get(0).equals(CABECALHO))
      throw new IllegalArgumentException("Cabeçalho incompatível");
    List<Lancamento> out = new ArrayList<>();
    Set<String> ids = new HashSet<>();
    for (int i = 1; i < rows.size(); i++) {
      List<String> r = rows.get(i);
      if (r.size() != 4 || !ids.add(r.get(0)))
        throw new IllegalArgumentException("Linha inválida ou ID duplicado: " + (i + 1));
      out.add(
          new Lancamento(r.get(0), r.get(1), new BigDecimal(r.get(2)), LocalDate.parse(r.get(3))));
    }
    return List.copyOf(out);
  }
}
