# URNA ELETRÔNICA - CLI

Este projeto foi desenvolvido como parte da disciplina de Programação Orientada a Objetos (POO) no curso de Análise e Desenvolvimento de Sistemas do Instituto Federal do Piauí – Campus Pedro II.

## Descrição

O sistema simula o funcionamento de uma **urna eletrônica** por meio de menus no terminal, possuindo duas interfaces distintas:

- **Interface do Administrador**:
  - Inicia e finaliza o processo de votação.
  - Cadastra usuários e candidatos.
  - Consulta os resultados da eleição.
  - Gerencia os dados do sistema.

- **Interface do Usuário (Eleitor)**:
  - Permite ao eleitor votar em seus candidatos de forma simples e interativa.

O projeto foi construído utilizando **Java**, com ênfase em conceitos de orientação a objetos, como encapsulamento, herança e modularização.

## Estrutura de Diretórios

Abaixo está a estrutura principal do projeto:
```plaintext

URNA_ELETRONICA/
└── src/ 
    ├── auxiliares/  # Clases para melhorar a interacao com os menus
    ├── cargo/       # Gerencia os cargos  
    ├── eleicao/     # Gerencia a eleição
    ├── Interface/   # Classes dos menus do terminal
    ├── registros/   # Listas e Classes para registro de usuarios
    ├── resultados/  # Calculos de majoritário e proporcional
    ├── usuarios/    # Classes de usuário e candidatos
    ├── votacao/     # Gerência o fluxo de votação
    └── Main.java  

```

## Como Usar

1. Clone o repositório em sua máquina local:
   ```bash
   git clonehttps://github.com/VINYCIU51/URNA_ELETRONICA.git
2. Entre no arquivo **main.java** e rode o programa.
3. Ainda dentro do arquivo **main**, existem contas de **usuário** e **admin** para testes.
4. Utilize-se da conta de **admin** para ter uma visão ampla das funcionalidades do projeto. 

## Funcionalidades Implementadas

- Cadastro de eleitores e candidatos.
- Registro de cargos eleitorais.
- Votação nos modos majoritário e proporcional.
- Exibição dos resultados ao final da eleição.
- Autenticação de login.  ( No momento havendo apenas uma conta fixa para **Admin** )
- Controle de início e término da votação.
- Menus interativos com o usuário

## Instituição

> Instituto Federal do Piauí – Campus Pedro II  
> Disciplina: Programação Orientada a Objetos (POO)

---

## Licença

Este projeto é de código aberto e está disponível para reutilização e modificação sob a licença [MIT](https://opensource.org/licenses/MIT).
