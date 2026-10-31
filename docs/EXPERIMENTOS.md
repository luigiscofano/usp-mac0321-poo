# Experimentos e critérios de aceitação

## Exercício guiado

Execute os exemplos de [INSTALACAO.md](INSTALACAO.md) e anote a previsão antes de rodar. A saída só conta como evidência depois de comparada com o contrato de [ARQUITETURA.md](ARQUITETURA.md).

## Casos de fronteira e referência

Testes de ordem estável, eventos passados, limites temporais, efeito de tratamento fictício, determinismo, transições State, bloqueio de edição, CSV com aspas e novas linhas, exclusão de inválidos e multiplicidades.

Leia a suíte e escolha um caso que quebra uma solução ingênua. Explique o motivo, reduza a entrada ao menor contraexemplo e proponha uma correção sem alterar o contrato silenciosamente. Use referência independente quando disponível.

## O que não concluir

GUI Swing e testes antigos fora da suíte nova; regras da simulação são fictícias; EP1/EP2 completos não autenticados; persistência nova cobre apenas lançamentos.

Não use tempo isolado ou gráfico atraente como prova de correção. Registre ambiente, entrada, saída, versão e limitações relevantes.
