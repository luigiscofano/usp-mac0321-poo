# MAC0321 · Eventos, estados, CSV e coleções

> Estudos independentes de orientação a objetos em Java: simulação por eventos, estados de lançamentos, persistência CSV e multiconjuntos, com acervo acadêmico e testes.

![Visão geral de MAC0321 · Eventos, estados, CSV e coleções](assets/capa.svg)

[![Verificação](https://github.com/luigiscofano/usp-mac0321-poo/actions/workflows/verificacao.yml/badge.svg?branch=main)](https://github.com/luigiscofano/usp-mac0321-poo/actions/workflows/verificacao.yml)

[O que faz](#o-que-este-projeto-faz) · [Módulos](#funcionalidades-e-módulos) · [Executar](#como-executar) · [Aprendizagem](#o-que-desenvolvi-e-aprendi) · [Estado atual](#estado-atual) · [Documentação](#documentação-e-navegação)

## O que este projeto faz

Reúne estudos de **Laboratório de Programação Orientada a Objetos — MAC0321**. A aplicação executável simula eventos em um domínio fictício de paciente/médico/droga. Outros módulos oferecem lançamentos com estados temporais, leitura/escrita CSV e um multiconjunto. Um acervo separado preserva a Lista 08 em coautoria com Hugo Spadete.

## Qual problema resolve

Os problemas são organizar comportamentos que mudam com o tempo, separar responsabilidades e conservar dados sem misturar interface, regras e persistência. Os módulos de simulação, financeiro e coleções **não são partes de um sistema clínico ou bancário integrado**. Compartilham o propósito de estudo e a suíte de testes.

## Funcionalidades e módulos

### 1. Agenda de eventos

`Evento` tem instante imutável e uma ação. `Controlador` usa fila de prioridade e ordem de inserção para tratar empates de forma determinística. Agendar no passado ou voltar o relógio é erro. O módulo é reutilizado na simulação, mas pode ser estudado sozinho. Operações de agenda custam O(log e), com e eventos pendentes.

### 2. Simulação de paciente, médico e droga

A demonstração executa 300 minutos, ordenando surto, consulta e evolução a cada minuto. Paciente guarda o estado; médico decide pelas regras da simulação; droga aplica o efeito definido. O log exibe minuto, temperatura, PAC e medicação. **São regras fictícias de software, sem uso clínico ou validade médica.** Não representam um modelo fisiológico validado.

### 3. Lançamentos e padrão State

Um lançamento possui ID, descrição, valor decimal e data. O estado é calculado com `Clock`: valor negativo gera `Invalido`; data anterior a hoje gera `Executado`; hoje/futuro gera `Planejado`. Executados bloqueiam edição. O relógio explícito permite repetir um teste em qualquer dia, sem persistir um rótulo que fique desatualizado com o calendário.

### 4. Persistência CSV

`LancamentosCsv` conserva `id,descricao,valor,data`, aceita campos com vírgulas, aspas e quebras de linha, rejeita IDs repetidos e exclui inválidos ao salvar. Usa arquivo temporário e tenta substituição atômica, com fallback quando o sistema não oferece suporte. Este módulo é uma biblioteca, sem menu ou aplicativo financeiro completo.

### 5. Multiconjunto e acervo

O módulo novo `Multiconjunto` armazena frequências: adicionar x duas vezes deve ser diferente de adicionar uma. Sua igualdade considera contagens, rejeitando `null`. O [acervo](acervo/README.md) contém 13 fontes da Lista 08, com GUI e testes históricos próprios. Esse acervo não é compilado pela suíte nova; o fatorial e os wrappers mantêm limitações documentadas.

## Como executar

**Requisitos:** JDK 17 ou superior, com `java` e `javac` no PATH; Python 3.12 para o verificador. O JRE sozinho não contém o compilador. Não há dependência de Maven ou Gradle na suíte nova.

```sh
git clone https://github.com/luigiscofano/usp-mac0321-poo.git
cd usp-mac0321-poo
```

Confira o ambiente:

```sh
java -version
javac -version
python --version
```


### Compilar, testar e abrir a demonstração

```sh
python scripts/verificar.py
java -cp build/classes poo.simulacao.Simulador
```

O primeiro comando compila os módulos novos e executa `PooTest`; a evidência vai para `build/testes.log` e `build/validacao.json`. O segundo imprime o log da simulação. Não abre janelas e não exige conexão com serviços externos.

### Experimentar State e CSV

Depois da compilação, inicie `jshell --class-path build/classes` e cole:

```java
import poo.financeiro.*;
import java.time.*;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
var relogio = Clock.fixed(Instant.parse("2026-10-31T12:00:00Z"), ZoneOffset.UTC);
var item = new Lancamento("demo-1", "Livro, edição especial", new BigDecimal("19.90"), LocalDate.of(2026, 11, 1));
System.out.println(item.estado(relogio).getClass().getSimpleName());
LancamentosCsv.salvar(Path.of("build/exemplo.csv"), List.of(item), relogio);
System.out.println(LancamentosCsv.carregar(Path.of("build/exemplo.csv")).size());
```

O estado esperado é `Planejado`; a leitura deve retornar um item. A vírgula na descrição deve ser preservada. O diretório `build/` já existe após o verificador. Use `/exit` para sair do JShell. A [fixture CSV](examples/lancamentos.csv) e os testes oferecem outros casos.

### Experimentar multiplicidades

No mesmo JShell:

```java
import poo.colecoes.Multiconjunto;
var conjunto = new Multiconjunto<String>();
conjunto.adicionar("x"); conjunto.adicionar("x");
System.out.println(conjunto.quantidade("x"));
```

O resultado deve ser `2`. Para executar a GUI e os testes históricos da Lista 08, siga o [repositório original](https://github.com/luigiscofano/MAC0321-Lista08), com suas dependências próprias.

## O que desenvolvi e aprendi

As implementações e a documentação desta edição relacionam modelagem de domínio, composição, eventos, estados derivados, imutabilidade, precisão decimal, parsing e testes determinísticos. O acervo conserva a coautoria e não é apresentado como código novo de uma única pessoa.

**Objetivos pedagógicos:** explicar a ordem de eventos empatados; testar a mudança de estado após a passagem de um dia; fazer round-trip de CSV com aspas e linhas internas; mostrar por que HashSet não conserva multiplicidade. [Atividades e rubrica](docs/OBJETIVOS_PEDAGOGICOS.md).

## Estado atual

| Disponível | Limite / evolução |
| --- | --- |
| Agenda estável e demonstração por eventos | O controlador genérico não impede um consumidor de agendar infinitamente no mesmo instante |
| State com Clock, BigDecimal e CSV | Sem aplicativo financeiro completo, DAOs históricos ou esquema original reconstruído |
| Multiconjunto por frequências | API pequena, voltada aos exercícios |
| Suíte nova e CI multiplataforma | GUI e testes históricos têm validação separada |

## Validação e limites

`PooTest` exercita os contratos dos módulos novos, incluindo tempo, estados, CSV e coleções. A [validação](docs/VALIDACAO.md) e a [arquitetura](docs/ARQUITETURA.md) detalham os casos e limites. A execução da simulação não oferece orientação médica; salvar um CSV não equivale a um sistema financeiro de produção.

## Documentação e navegação

| Caminho | Conteúdo |
| --- | --- |
| [src/](src/README.md) | Pacotes eventos, simulação, financeiro e coleções |
| [Guias por assunto](docs/topicos/README.md) | Roteiros de estudo por módulo |
| [tests/](tests/README.md) / [examples/](examples/README.md) | Testes e fixture CSV |
| [acervo/](acervo/README.md) | Lista 08 e seus créditos |
| [docs/](docs/README.md) | Contratos, instalação, pedagogia, apresentação e evidências |
| [Branches](docs/BRANCHES.md) / [PRs](docs/PUBLICACAO.md) | Entregas organizadas |


## Autoria, referências e licença

**Luigi Scofano de Araujo · Engenharia de Computação, Poli-USP · USP 13680334.** Fontes recuperados, componentes de terceiros e trabalhos em grupo mantêm os créditos indicados nos arquivos e na documentação.

[Direitos e créditos](DIREITOS_E_CREDITOS.md) · [Procedência](docs/PROCEDENCIA.md) · [Como contribuir](CONTRIBUTING.md) · [Mudanças](CHANGELOG.md)

## Tópicos do repositório

`java` · `object-oriented-programming` · `discrete-event-simulation` · `state-pattern` · `csv` · `collections` · `software-testing` · `usp` · `educational`
