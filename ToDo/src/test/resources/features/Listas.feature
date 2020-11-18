# language: pt
# encoding: utf8
  Funcionalidade: Listas
    Criar e manter listas
    Persona: Thais
    Tipo: Analista Financeiro / Usuario Frequente
  Cenario: Criar uma lista
    Dado que eu acesso o site Microsof To Do
    Quando clico no icone do usuario
    Então o site realiza o login e exibiu a pagina do To Do
    Quando clico em Nova Lista
    E preencho "Lista de Compras" e pressiono Enter
    Então exibe a Lista de Compras vazia

#todo: Cenário Alterar uma Lista
  Cenario Alterar uma Lista
    Dado que estou logado na tela principal do MicroSoft Todo e existe pelo menos uma lista cadastrada
    Quando clico em uma lista
    E clico no botão opções da Lista
    Então são exibidos as opções para alterar a lista


#todo: Cenário Consultar uma Lista
    Cenario: Consultar uma Lista
      Dado que estou logado no Microsoft ToDo
      Quando clico e escrevo o nome de uma lista que procuro
      Então um ou mais listas são exibidos na lista de itens

#todo: Cenário Excluir uma Lista
    Cenario: Excluir uma Lista
      Dado que estou logado no Microsoft ToDo e possuo pelo menos uma lista cadastrada
      Quando clico com o botão direito do mouse em uma lista e é exibida uma lista com a opção de excluir a lista
      E clico em Apagar Lista
      E clico em Apagar Lista na mensagem de confirmação
      Então a lista é apagada

#todo: Compartilhar uma Lista
    Cenario: Compartilhar uma lista
      Dado que estou logado no microsoft ToDo e existe uma lista cadastrada
      Quando clico com o botão direito do mouse em uma lista
      E clico em Compartilhar Lista
      E clico em Criar Link de Convite
      Então é exibido o link para compartilhar a lista


