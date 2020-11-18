# language: pt
# encoding: utf8
  Funcionalidade: Lista
    Criar e manter itens em uma lista
    Esquema do Cenário: Incluir itens na lista
      Dado que estou na Lista de Compras
      Quando digito <item> e pressiono Enter
      Então exibe o <item> na lista de Compras
      Exemplos:
        | item              |
        | "macarrao"        |
        | "molho de tomate" |


    Cenario: Alterar Itens na Lista
      Dado que estou na lista de compras e existem itens na lista
      Quando aperto em cima do nome do item
      Então é exibida uma tela com as opções para serem alteradas no item


    Cenario: Consultar itens de uma Lista
      Dado que estou logado no Microsoft ToDo
      Quando clico e escrevo o nome de um item que procuro
      Então um ou mais itens são exibidos na lista de itens


    Cenario: Excluir item de uma Lista
      Dado que estou logado no Microsoft ToDo e possuo pelo menos uma lista cadastrada
      Quando clico com o botão direito do mouse em uma lista e é exibida uma lista com a opção de excluir a lista
      E clico em Apagar Lista
      E clico em Apagar Lista na mensagem de confirmação
      Então a lista é apagada