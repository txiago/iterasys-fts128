#language: pt
#encoding: utf-8

  Funcionalidade: Consultar Produto
    Como um cliente eventual, gostaria de consultar a disponibilidade e
    preço de alguns prosutos nos quais possuo interesse

    Esquema do Cenario:
      Dado que acesso o site da Cobasi <id>
      Quando procuro por <produto> e pressiono ENTER
      Entao exibe a lista de produtos relacionados a <produto>
      Quando seleciono o <produtoDescricao> da lista
      Entao verifico a marca <marca> com preco normal de <precoNormal> e <precoAssinante> para assinantes
      Exemplos:
        | id | produto |produtoDescricao | marca | precoNormal | precoAssinante |
        | "1" | "Ração" | "Ração Golden Special para Cães Adultos Frango e Carne" |"Golden" | "R$ 115,90" | "R$ 104,31" |
        | "2" | "Petisco" | "Keldog Bifinho de Churrasco Kelco" |"Keldog" | "R$ 2,98" | "R$ 2,68" |