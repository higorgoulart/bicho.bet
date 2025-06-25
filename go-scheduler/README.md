# Go-Scheduler | Bicho.bet

Este serviço faz parte do sistema _Bicho.bet_, desenvolvido no ano de 2024 para a disciplina de Back-end da graduação em Engenharia de Software da UNISATC.

*O serviço foi refatorado para uma avaliação da disciplina de Clean Code da graduação em Engenharia de Software da UNISATC em 05/2025*

## Instalação

Para executar o serviço é necessário subir todo o sistema _Bicho.bet_. Mais detalhes podem ser encontrados na README.md principal do projeto <a href="https://github.com/higorgoulart/bicho.bet/blob/main/README.md">aqui</a>.


## Funcionalidades

As principais funcionalidades do serviço são:

- Gerar resultados de jogos em aberto;
- Premiar apostas;
- Aplicar juros a devedores da plataforma;

## Refatoração
A estratégia de refatoração utilizada foi aplicada para solucionar os seguintes problemas identificados:


### Problemas

1. Ausência de testes
2. Erros não verificados
3. Lógica de negócio centralizada em funções isoladas num único arquivo
4. Uso de estrutura condicional **_switch_** para designar multiplicadores de grupo
5. Regras de aposta ou multiplicadores estão engessadas no código
6. Ausência de CI


### Solução proposta

1. Implementação do framework **_Testify_**
2. Implementação do linter **_golangci-lint_**
3. Aplicar o padrão de arquitetura **_Service Layer_**
4. Implementar **_Strategy Pattern_**
5. Reescrever seguindo o conceito **_(Open/Closed Principle)_**
6. Implementação do **_GitHub Actions_** disponível no repositório

### Interface Fluente

Foram encontrados os seguintes motivos para não implementar interfaces fluentes:

- As funções são majoritariamente atômicas, no caso executam uma ação completa, como calcular prêmio, atualizar dívida, etc.

- Interfaces Fluentes são melhores para configuração e não para métodos que executam efeitos colaterais (como commits em banco, logs, etc.), pois podem dificultar o controle de erros e rollback.

- A implementação de interfaces fluentes podem aumentar a complexidade sem ganho real, já que as operações não são naturalmente encadeáveis.

## Autores

<a href="https://github.com/GabrielGuinzani">Gabriel Ferreira</a>
·
<a href="https://github.com/guilherme-savio">Guilherme Savio</a>
·
<a href="https://github.com/higorgoulart">Higor Goulart</a>