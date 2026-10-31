<p align="center"><img src="assets/capa.svg" alt="MAC0321 · Laboratório de Programação Orientada a Objetos" width="100%"></p>

# MAC0321 · Laboratório de Programação Orientada a Objetos

**Eventos, estado e persistência: contratos explicáveis e verificáveis.**

[![Verificação](https://github.com/luigiscofano/usp-mac0321-poo/actions/workflows/verificacao.yml/badge.svg?branch=main)](https://github.com/luigiscofano/usp-mac0321-poo/actions/workflows/verificacao.yml)

Portfólio de **Luigi Scofano de Araujo · Engenharia de Computação, Poli-USP · USP 13680334**. Organização e complementos de 2026 com assistência de IA, distinguindo acervo recuperado e implementações novas. [Procedência](docs/PROCEDENCIA.md).

## Objetivos pedagógicos em destaque

- **Separar responsabilidades** — Agendar Evento em vez de Runnable.
- **Garantir determinismo** — Desempatar por ordem de inserção.
- **Aplicar State** — Representar Planejado, Executado e Invalido como tipos.
- **Controlar dependência temporal** — Consultar Clock injetado.
- **Preservar dados** — Fazer round-trip CSV de textos complexos.
- **Distinguir conjunto e multiconjunto** — Comparar frequências.

**[Atividades, evidências e rubrica →](docs/OBJETIVOS_PEDAGOGICOS.md)**

## Execute e experimente

```sh
python scripts/verificar.py
java -cp build/classes poo.simulacao.Simulador
```

Ambiente: **Java 17+ e Python 3.12**. [Instalação detalhada](docs/INSTALACAO.md).

## Encontre cada parte

| Diretório | Conteúdo |
| --- | --- |
| [src/](src/README.md) | Código novo organizado por eventos, simulação fictícia, financeiro e coleções. |
| [src/poo/eventos/](src/poo/eventos/README.md) | Evento abstrato e Controlador com fila temporal estável. |
| [src/poo/simulacao/](src/poo/simulacao/README.md) | Paciente, Droga, Medico e Simulador de um modelo fictício discreto. |
| [src/poo/financeiro/](src/poo/financeiro/README.md) | State, Clock, BigDecimal e persistência CSV. |
| [src/poo/colecoes/](src/poo/colecoes/README.md) | Multiconjunto com igualdade por frequências. |
| [tests/](tests/README.md) | Testes de eventos, limites, State, serialização e multiplicidade. |
| [examples/](examples/README.md) | CSV sintético sem dados financeiros reais. |
| [acervo/](acervo/README.md) | 13 arquivos Java recuperados da Lista08 pública, preservando coautoria. |
| [docs/](docs/README.md) | Arquitetura, objetivos, reprodução, apresentação e evidência |

## Verificação e limites

Testes de ordem estável, eventos passados, limites temporais, efeito de tratamento fictício, determinismo, transições State, bloqueio de edição, CSV com aspas e novas linhas, exclusão de inválidos e multiplicidades.

GUI Swing e testes antigos fora da suíte nova; regras da simulação são fictícias; EP1/EP2 completos não autenticados; persistência nova cobre apenas lançamentos.

**[Validação →](docs/VALIDACAO.md)** · **[Arquitetura e decisões →](docs/ARQUITETURA.md)**

Use o [roteiro de apresentação](docs/APRESENTACAO.md) e confira os [PRs reais](docs/PUBLICACAO.md). As branches representam organização atual, sem datas retroativas ou revisões fictícias.

[Contribuir](CONTRIBUTING.md) · [Direitos e créditos](DIREITOS_E_CREDITOS.md) · [Referências](docs/REFERENCIAS.md) · [Mudanças](CHANGELOG.md)
