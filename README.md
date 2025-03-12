# Album Manager App

Este é um aplicativo Android desenvolvido em Kotlin com o objetivo de facilitar o gerenciamento e organização da coleção de álbuns de música. O app foi criado para resolver o desafio pessoal de acompanhar quais álbuns já foram adquiridos e quais ainda estão faltando na minha coleção. O aplicativo permite registrar informações detalhadas sobre os álbuns e oferece a conveniência de armazená-los em um banco de dados na nuvem.

## Objetivo do Projeto

O projeto foi desenvolvido com o propósito de aprender Kotlin e aplicar conceitos modernos de desenvolvimento Android, como o uso de Jetpack Compose e ViewModel. A motivação para a criação do app surgiu da necessidade de organizar uma coleção pessoal de álbuns de diferentes artistas, facilitando o acompanhamento de quais álbuns já foram adquiridos e quais estão faltando.

O aplicativo permite adicionar, atualizar e excluir álbuns de maneira prática e intuitiva, com dados armazenados em um banco de dados na nuvem, oferecendo acessibilidade e segurança ao usuário.

## Funcionalidades

- **Tela principal**: Exibe uma lista de álbuns com a opção de marcar ou desmarcar álbuns como "possuidos".
- **Tela de atualização de álbum**: Permite adicionar novos álbuns, atualizar as informações de álbuns existentes ou excluir álbuns.
  - **Adicionar álbum**: Insira o título, artista, ano e URL da capa do álbum.
  - **Atualizar álbum**: Modifique as informações de título, artista, ano e URL da capa de um álbum existente.
  - **Excluir álbum**: Exclua um álbum informando o título.
- **Navegação entre telas**: Usando o Jetpack Navigation, o usuário pode navegar facilmente entre a tela principal e a tela de atualização.

## Tecnologias Usadas

- **Kotlin**: Linguagem de programação principal.
- **Jetpack Compose**: Para a construção da interface de usuário de maneira declarativa.
- **ViewModel**: Para gerenciar o estado do aplicativo de forma eficiente e desacoplada.
- **Navigation Compose**: Para navegação entre telas dentro do app.
- **Material3**: Para componentes modernos da interface com uma aparência consistente e atraente.

## Como Rodar o Projeto

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/fitzinn/Collections
