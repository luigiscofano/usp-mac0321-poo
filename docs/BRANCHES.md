# Branches e entregas

Use `main` para clonar e executar a versão consolidada. As branches abaixo preservam o ponto de entrega de cada assunto; depois da integração, podem estar atrás de `main`.

Cada branch está ligada a uma alteração concreta. A organização foi feita nesta edição: os nomes não indicam trabalho em datas antigas nem revisão de outras pessoas.

| Branch | Finalidade | Acesso |
| --- | --- | --- |
| `main` | Código e documentação consolidados | [Abrir](https://github.com/luigiscofano/usp-mac0321-poo/tree/main) |
| `docs/evidencia-da-ci` | Registrar validacao remota e artefatos reproduziveis | [PR #5](https://github.com/luigiscofano/usp-mac0321-poo/pull/5) |
| `docs/objetivos-e-arquitetura` | Documentar objetivos pedagogicos, arquitetura e procedencia | [PR #4](https://github.com/luigiscofano/usp-mac0321-poo/pull/4) |
| `feat/aplicacoes-e-acervo` | Adicionar State, persistencia CSV e acervo Lista08 | [PR #2](https://github.com/luigiscofano/usp-mac0321-poo/pull/2) |
| `feat/fundamentos` | Implementar controlador por eventos e simulacao deterministica | [PR #1](https://github.com/luigiscofano/usp-mac0321-poo/pull/1) |
| `test/verificacao-automatica` | Adicionar testes executaveis e GitHub Actions | [PR #3](https://github.com/luigiscofano/usp-mac0321-poo/pull/3) |
| `docs/eventos-e-simulacao` | Guia de eventos e simulação determinística | [Guia](../docs/topicos/EVENTOS.md) · [Branch](https://github.com/luigiscofano/usp-mac0321-poo/tree/docs/eventos-e-simulacao) |
| `docs/estado-e-persistencia` | Guia de State, tempo e persistência CSV | [Guia](../docs/topicos/STATE_E_CSV.md) · [Branch](https://github.com/luigiscofano/usp-mac0321-poo/tree/docs/estado-e-persistencia) |
| `docs/colecoes-e-acervo` | Guia de multiconjuntos e Lista08 recuperada | [Guia](../docs/topicos/COLECOES_E_ACERVO.md) · [Branch](https://github.com/luigiscofano/usp-mac0321-poo/tree/docs/colecoes-e-acervo) |

## Como continuar

Parta da versão atual de `main`, crie uma branch para uma mudança delimitada e abra um PR explicando comportamento, motivo e validação. Não use branches antigas como base para novos assuntos sem atualizar a base.

```sh
git switch main
git pull --ff-only
git switch -c docs/nova-atividade
```

[Guias por assunto](topicos/README.md) · [Registro de publicação](PUBLICACAO.md) · [Todos os PRs](https://github.com/luigiscofano/usp-mac0321-poo/pulls?q=is%3Apr)
