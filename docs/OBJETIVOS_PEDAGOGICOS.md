# Objetivos pedagógicos

**A meta é explicar o mecanismo e demonstrar o resultado.** Cada atividade exige evidência observável.

| Competência | Atividade | Evidência de aprendizagem |
| --- | --- | --- |
| Separar responsabilidades | Agendar Evento em vez de Runnable | Explicar o papel do controlador e o papel do domínio |
| Garantir determinismo | Desempatar por ordem de inserção | Reproduzir a mesma simulação e ordem de eventos |
| Aplicar State | Representar Planejado, Executado e Invalido como tipos | Testar edição, transição e persistência sem inválidos |
| Controlar dependência temporal | Consultar Clock injetado | Testar datas com relógio fixo, sem depender do dia da execução |
| Preservar dados | Fazer round-trip CSV de textos complexos | Conservar aspas, vírgulas, quebras de linha e BigDecimal |
| Distinguir conjunto e multiconjunto | Comparar frequências | Provar que inserir x duas vezes difere de inserir uma vez |

## Sequência de estudo

1. Leia o contrato e resolva um exemplo pequeno à mão.
2. Preveja saída e estado intermediário antes de executar.
3. Confronte sua previsão com o programa.
4. Altere uma entrada de fronteira e justifique o resultado.
5. Explique o invariante violado em um teste negativo.
6. Proponha uma extensão com critério de aceitação.

## Rubrica sugerida

| Critério | Inicial | Adequado | Avançado |
| --- | --- | --- | --- |
| Explicação | Repete a saída | Explica etapas | Justifica invariante e contraexemplo |
| Verificação | Usa um exemplo | Testa fronteiras | Compara com referência independente |
| Limites | Omite hipóteses | Declara domínio | Explica falha e sua detecção |
| Reprodução | Depende do ambiente pessoal | Usa comandos e fixtures | Registra versão, origem e evidência |

Rubrica proposta nesta edição, sem estudo de eficácia pedagógica com participantes ou nota institucional.
