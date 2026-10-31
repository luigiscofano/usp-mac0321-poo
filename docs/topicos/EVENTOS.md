# Eventos e simulação

`Evento` fornece instante imutável e executar(Controlador). O controlador agenda por (instante, ordem de inserção), preservando empates de forma determinística. Inserir no passado ou voltar o relógio gera erro.

```sh
python scripts/verificar.py
java -cp build/classes poo.simulacao.Simulador
```

Na demonstração, cada minuto processa primeiro o surto, depois a consulta de múltiplos de dez e depois o avanço do modelo. O programa usa regras fictícias e não é um modelo clínico.

**Atividade:** agende três eventos no mesmo instante e preveja a ordem. Em seguida, tente inserir um evento anterior ao relógio atual e explique a rejeição. Relacione a decisão ao teste de estabilidade da fila.

Os limiares e parâmetros da simulação estão em [ARQUITETURA.md](../ARQUITETURA.md). O controlador organiza execução; Paciente, Medico e Droga guardam regras do domínio.
