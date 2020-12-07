#language: pt
#encoding: utf8
  Funcionalidade: Cadastrar e Realizar Login de Usuarios
    Com um cliente eventual, gostaria de realizar o cadastro de usário na loja e
    posteriormente realizar o login
    - Site Petz
    - Criar teste que gera pelo menos 3 usuários através de massa de Teste (validar a criação)
    - Dados dos usuários fictícios podem ser criados via gerador de pessoas em 4devs.com e os emails através de um email de 10 minutos, como o maildrop.cc, entre outros
    - Criar outro teste para o login dos 3 usuários, sendo pelo menos 1 teste positivo (entrar) e 1 negativo (senha invalida)
    - Implementar os prints no script

    Esquema do Cenário: Realizar o Cadastro de Usuario
      Dado que acesso o site da Petz.com.br
      Quando clico em ENTRAR
      Entao clico em Criar Conta e preencho os campos de cadastro "<nome>" "<email>" "<DDD>" "<celular>" "<sexo>" "<dataNascimento>" "<CPF>" "<senha>" e  clico no botao Criar Conta
      Entao verifico se a conta foi criada
      Exemplos:
        | nome | email | DDD | celular | sexo | dataNascimento | CPF | senha |
#        | Breno Raul Moreira | brenoraulmoreira_brenoraulmoreira@maildrop.cc | 31 | 98421-3674 | M | 04/06/1941 | 376.044.867-46 | 6GPjMtBFat |
#        | Rafaela Elaine Porto | rrafaelaelaineporto@maildrop.cc | 62 | 98447-9634 | F | 01/08/1971 | 953.177.841-82 | hR71bojPGt |
#        | Vera Tânia Simone da Paz | vverataniasimonedapaz@maildrop.cc | 71 | 99596-8940 | F | 07/03/1984 | 643.122.757-34 | 3DgPKti5UQ |
        | Tatiane Clara Alícia Galvão | tatianeclaraaliciagalvao..tatianeclaraaliciagalvao@maildrop.cc | 68 | 99333-6900 | F | 08/05/1981 | 541.878.355-63 | YqejjOc50B |
        | Yago Francisco Campos | yagofranciscocampos__yagofranciscocampos@maildrop.cc | 83 | 98493-3178 | M | 19/04/1946 | 115.542.452-22 | b59BadP1Cf |
        | Emilly Cecília Corte Real | emillyceciliacortereal__emillyceciliacortereal@maildrop.cc | 54 | 98842-7416 | F | 27/08/1945 | 617.540.628-14 | wswdNPf3eU |
        | Luiza Luzia Gabrielly Mendes | luizaluziagabriellymendes..luizaluziagabriellymendes@maildrop.cc | 79 | 98129-7035 | F | 19/07/1960 | 502.039.445-93 | d4mr47nZYO |


    Esquema do Cenario: Realizar o Login de Usuario
      Dado que acesso o site da Petz.com.br
      Quando clico em ENTRAR
      E preencho os campos de "<email>" "<senha>" e clico no botao Entrar
      Entao verifico se o primeiro nome "<primeiroNome>" do usuario logado eh exibido
      Exemplos:
      | primeiroNome | email | senha |
      | Breno | brenoraulmoreira_brenoraulmoreira@maildrop.cc | 6GPjMtBFat |
      | Rafaela | rrafaelaelaineporto@maildrop.cc | hR71bojPGt |
      | Vera | vverataniasimonedapaz@maildrop.cc | 3DgPKti5UQ |

    Esquema do Cenario: Realizar o Login de Usuario com Dados Incorretos
      Dado que acesso o site da Petz.com.br
      Quando clico em ENTRAR
      E preencho os campos de "<email>" "<senha>" com algum valor incorreto e clico no botao Entrar
      Entao verifico se o texto Dados Incorretos eh exibido
      Exemplos:
        | email | senha |
        | brenoraulmoreira_brenoraulmoreira@maildrop.cc | 6GPjMtBFats |
        | rrafaelaelaineporto@maildrop.cc | hR71bojPGts |
        | vverataniasimonedapaz@maildrop.cc | 3DgPKti5UQs |

