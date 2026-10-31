# State, Clock e CSV

| Condição, em ordem | Estado | Editar? | Persistir? |
| --- | --- | --- | --- |
| Valor negativo | Invalido | Sim | Não |
| Data anterior a hoje | Executado | Não | Sim |
| Hoje ou futuro | Planejado | Sim | Sim |

O estado é calculado com Clock explícito. Uma passagem de dia pode transformar planejamento em execução sem salvar um rótulo temporal obsoleto no CSV.

`LancamentosCsv` conserva id, descrição, BigDecimal e data. O parser trata vírgulas, aspas duplicadas, CRLF/LF e campos multilinha. IDs duplicados são erro. Salvar usa temporário e tenta substituição atômica, com fallback documentado.

**Atividade:** fixe o relógio, crie lançamento para hoje, avance o relógio um dia e justifique a alteração de estado. Faça round-trip de uma descrição com vírgula, aspas e quebra de linha; verifique igualdade do valor decimal.

```sh
python scripts/verificar.py
```

[Contratos completos](../ARQUITETURA.md) · [CSV sintético](../../examples/lancamentos.csv)
