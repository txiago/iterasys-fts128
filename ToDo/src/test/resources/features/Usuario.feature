# language: pt
# encoding: utf8
  Funcionalidade: Usuario
    Criar, manter e autenticar usuários
    Cenario: Login com Sucesso
      Dado que estou em Microsoft To Do e deslogado
      Quando clico no ícone para realizar o login
      Então exibe a página de login
      Quando preencho o email "correia@iterasys.com.br" e clico em Próximo
      E seleciona a conta pessoal
      E digita a senha "teste123" e clico em Entrar
      Então o site realiza o login e exibe a página do To Do

    Cenario: Login Incorreto
      Dado que estou em Microsoft To Do e deslogado
      Quando clico no ícone para realizar o login
      Então exibe a página de login
      Quando preencho o email "correia@iterasys.com.br" e clico em Próximo
      E seleciona a conta pessoal
      E digita a senha "Atena2020" e clico em Entrar
      Então o site exibe a mensagem de erro: Sua conta ou senha está incorreta. Se você não se lembra de sua senha, redefina-a agora.


    Cenario: Esqueci a Senha
      Dado que estou em Microsoft ToDo deslogado e na tela de inserir o email de usuário
      Quando clico no ícone para realizar o login
      Então Exibe a página de login
      Quando preencho meu email "tximedeiros@gmail.com" e clico em próximo
      E clico em Esqueceu sua Senha?
      E clico em um email  e clico em Get Code
      E um código é enviado para o email
      E insiro o código e pressiono Próximo
      Então são exibidos os campos para inserir a nova senha



    Cenario: Incluir Usuário
      Dado que estou em Microsoft ToDo deslogado
      Quando clico no ícone para realizar login e clico em Criar Conta
      Então exibe a tela para inserir o email do novo usuário
      Quando insiro o email de novo usário e aperto em Próximo
      Então é exibido o campo para inserir a Senha
      E ao apertar próximo é exibida a tela para inserir Nome e Sobrenome
      E ao apertar Próximo é exibida a tela para inserir o país e data de nascimento
      E ao apertar Próximo é exida a tela para inserir o código de verificação enviado para o email do novo usuário
      E ao apertar Próximo é exibida a tela para verificar se é um humano realizando o cadastro
      Então ao apertar Próximo o novo usuário é logado no sistema




