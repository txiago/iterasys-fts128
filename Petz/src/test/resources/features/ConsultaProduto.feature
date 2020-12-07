#language: pt
#encoding: utf8

  Funcionalidade: Consultar Produto
    Como um cliente eventual, gostaria de consultar a disponibilidade
    e preço de alguns produtos que tenho interesse em adquirir

    Cenário: Consulta Produto Fixo com Enter
      Dado que acesso o site da Petz
      Quando procuro por "Ração" e pressiono ENTER
      Entao exibe a lista de produtos relacionados a "Ração"
      Quando seleciono o primeiro produto da lista
      Entao verifico a marca como "Royal Canin" com preco normal de "R$ 232,69" e "R$ 209,42" para assinantes


    Cenário: Consulta Produto Fixo com Clique na Lupa
      Dado que acesso o site da Petz
      Quando procuro por "Ração" e clico na Lupa
      Entao exibe a lista de produtos relacionados a "Ração"
      Quando seleciono o primeiro produto da lista
      Entao verifico a marca como "Royal Canin" com preco normal de "R$ 232,69" e "R$ 209,42" para assinantes

    Cenário: Consulta Produto Fixo que Não Existe com Enter
      Dado que acesso o site da Petz
      Quando procuro por "fsfgsfgsgfs" e pressiono ENTER
      Entao exibe uma lista de produtos aproximados e a mensagem de que nao encontrou "fsfgsfgsgfs"

    Cenário: Consulta Produto Fixo com Menos de 3 Letras
      Dado que acesso o site da Petz
      Quando procuro por "ab" e pressiono Enter
      Entao exibe uma caixa de alerta dizendo que precisa preencher pelo menos 3 letras no termo