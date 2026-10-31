package poo.financeiro;

import java.util.*;

/** CSV com vírgula, aspas duplicadas, campos multilinha e CRLF/LF. */
public final class Csv {
  public static String linha(List<String> campos) {
    return campos.stream()
            .map(c -> "\"" + c.replace("\"", "\"\"") + "\"")
            .reduce((a, b) -> a + "," + b)
            .orElse("")
        + "\r\n";
  }

  public static List<List<String>> ler(String texto) {
    List<List<String>> linhas = new ArrayList<>();
    List<String> linha = new ArrayList<>();
    StringBuilder campo = new StringBuilder();
    boolean aspas = false, fechado = false, iniciou = false;
    for (int i = 0; i < texto.length(); i++) {
      char ch = texto.charAt(i);
      if (aspas) {
        if (ch == '"') {
          if (i + 1 < texto.length() && texto.charAt(i + 1) == '"') {
            campo.append('"');
            i++;
          } else {
            aspas = false;
            fechado = true;
          }
        } else campo.append(ch);
        continue;
      }
      if (ch == '"') {
        if (iniciou || fechado) throw new IllegalArgumentException("Aspas em posição inválida");
        aspas = true;
        iniciou = true;
      } else if (ch == ',') {
        linha.add(campo.toString());
        campo.setLength(0);
        fechado = false;
        iniciou = false;
      } else if (ch == '\n' || ch == '\r') {
        if (ch == '\r' && i + 1 < texto.length() && texto.charAt(i + 1) == '\n') i++;
        linha.add(campo.toString());
        linhas.add(List.copyOf(linha));
        linha.clear();
        campo.setLength(0);
        fechado = false;
        iniciou = false;
      } else {
        if (fechado) throw new IllegalArgumentException("Conteúdo depois de aspas finais");
        campo.append(ch);
        iniciou = true;
      }
    }
    if (aspas) throw new IllegalArgumentException("Campo sem aspas finais");
    if (iniciou || fechado || !linha.isEmpty()) {
      linha.add(campo.toString());
      linhas.add(List.copyOf(linha));
    }
    return List.copyOf(linhas);
  }
}
