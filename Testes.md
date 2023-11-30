# Plano de Teste - Cadastro
## Objetivo
O objetivo deste plano de teste é garantir a correta implementação e funcionamento do módulo de cadastro do sistema. Serão testados os validadores de documentos, e-mails, endereços e nomes, além do cadastro de pessoas físicas (PF) e pessoas jurídicas (PJ) no sistema.

## Localização
O arquivo de testes pode ser encontrado no caminho: src/test/java/cadastro/CadastroTest.java

## Teste 1: Verificar se a classe ValidaDoc é final
Descrição: Verificar se a classe ValidaDoc é final, ou seja, não pode ser instanciada.

` #0000ff `Entrada: `#000000`N/A

Saída esperada: A classe ValidaDoc deve ser final.

## Teste 2: Verificar se a classe ValidaNome é final
Descrição: Verificar se a classe ValidaNome é final, ou seja, não pode ser instanciada.

Entrada: N/A

Saída esperada: A classe ValidaNome deve ser final.

## Teste 3: Verificar se a classe ValidaEndereco é final
Descrição: Verificar se a classe ValidaEndereco é final, ou seja, não pode ser instanciada.

Entrada: N/A

Saída esperada: A classe ValidaEndereco deve ser final.

## Teste 4: Verificar se a classe ValidaEmail é final
Descrição: Verificar se a classe ValidaEmail é final, ou seja, não pode ser instanciada.

Entrada: N/A

Saída esperada: A classe ValidaEmail deve ser final.

## Teste 5: Verificar se a classe ValidaDoc valida corretamente CPF e CNPJ
Descrição: Verificar se a classe ValidaDoc valida corretamente CPF e CNPJ.

Entrada:

CPF válido: "70399622098"

CNPJ válido: "78322050000182"

CPF inválido: "12345678901"

CNPJ inválido: "12345678901234"

Saída esperada: Para CPF e CNPJ válidos, o retorno deve ser verdadeiro. Para CPF e CNPJ inválidos, o retorno deve ser falso.

## Teste 6: Verificar se a classe ValidaEmail valida corretamente o email
Descrição: Verificar se a classe ValidaEmail valida corretamente o email.

Entrada:

E-mail válido: "teste@gmail.com"

E-mail inválido: "testegmail"

Saída esperada: Para e-mail válido, o retorno deve ser verdadeiro. Para e-mail inválido, o retorno deve ser falso.

## Teste 7: Verificar se a classe ValidaEndereco valida corretamente o endereço
Descrição: Verificar se a classe ValidaEndereco valida corretamente o endereço.

Entrada:

Endereço válido: "Av Nilo Pecanha 3539"

Endereço inválido: "Av Nilo Pecanha 3539_1"

Saída esperada: Para endereço válido, o retorno deve ser verdadeiro. Para endereço inválido, o retorno deve ser falso.

## Teste 8: Verificar se a classe ValidaNome valida corretamente o nome
Descrição: Verificar se a classe ValidaNome valida corretamente o nome.

Entrada:

Nome válido: "Jose Alberto"

Nomes inválidos: "Jose_Beto86""

Saída esperada: Para nome válido, o retorno deve ser verdadeiro. Para nomes inválidos, o retorno deve ser falso.

## Teste 9: Verificar o cadastro de PJ é realizado com sucesso
Descrição: Verificar se o cadastro de pessoa jurídica é realizado com sucesso.

Entrada:

Documento: "55079581000190"

Nome: "Hospital Teste"

Endereço: "Rua Teste 123"

E-mail: "hospitalteste@hotmail.com"

Senha: "123456"

Saída esperada: O código de status HTTP retornado deve ser 202 (ACCEPTED).

## Teste 10: Verificar CNPJ já existente em banco
Descrição: Verificar se o sistema impede o cadastro de um CNPJ já existente em banco.

Entrada:

Documento: "78322050000182" (CNPJ já existente)

Nome: "Hospital Teste"

Endereço: "Rua Teste 123"

E-mail: "hospitalteste@hotmail.com"

Senha: "123456"

Saída esperada: O código de status HTTP retornado deve ser 400 (BAD REQUEST) e a mensagem de erro deve ser "CNPJ existente".

## Teste 11: Verificar inserção de CNPJ inválido
Descrição: Verificar se o sistema impede a inserção de um CNPJ inválido.

Entrada:

Documento: "12345678901234" (CNPJ inválido)

Nome: "Hospital Teste"

Endereço: "Rua Teste 123"

E-mail: "hospitalteste@hotmail.com"

Senha: "123456"

Saída esperada: O código de status HTTP retornado deve ser 400 (BAD REQUEST) e a mensagem de erro deve ser "CNPJ inválido".

## Teste 12: Verificar inserção de nome inválido na PJ
Descrição: Verificar se o sistema impede a inserção de um nome inválido para pessoa jurídica.

Entrada:

Documento: "16707700000150"

Nome: "Hospital Conceicao_14#" (nome inválido)

Endereço: "Rua Teste 123"

E-mail: "hospitalteste@hotmail.com"

Senha: "123456"

Saída esperada: O código de status HTTP retornado deve ser 400 (BAD REQUEST) e a mensagem de erro deve ser "Formato de nome inválido: use apenas letras".

## Teste 13: Verificar inserção de endereço inválido na PJ
Descrição: Verificar se o sistema impede a inserção de um endereço inválido para pessoa jurídica.

Entrada:

Documento: "03287472000165"

Nome: "Hospital Teste"

Endereço: "Rua Teste 1234*" (endereço inválido)

E-mail: "hospitalteste@hotmail.com"

Senha: "123456"

Saída esperada: O código de status HTTP retornado deve ser 400 (BAD REQUEST) e a mensagem de erro deve ser "Formato de endereço inválido: use apenas letras e números".

## Teste 14: Verificar inserção de email inválido na PJ
Descrição: Verificar se o sistema impede a inserção de um e-mail inválido para pessoa jurídica.

Entrada:

Documento: "50630504000163"

Nome: "Hospital Teste"

Endereço: "Rua Teste 123"

E-mail: "hospitaltestehotmail.com" (e-mail inválido)

Senha: "123456"

Saída esperada: O código de status HTTP retornado deve ser 400 (BAD REQUEST) e a mensagem de erro deve ser "Formato de email inválido".

## Teste 15: Verificar o cadastro de PF é realizado com sucesso
Descrição: Verificar se o cadastro de pessoa física é realizado com sucesso.

Entrada:

Documento: "70399622098"

Nome: "Usuario Teste"

Endereço: "Rua Teste 123"

E-mail: "usuarioteste@hotmail.com"

Senha: "123456"

Saída esperada: O código de status HTTP retornado deve ser 202 (ACCEPTED).

## Teste 16: Verificar CPF já existente em banco
Descrição: Verificar se o sistema impede o cadastro de um CPF já existente em banco.

Entrada:

Documento: "18157310016" (CPF já existente)

Nome: "Usuario Teste"

Endereço: "Rua Teste 123"

E-mail: "usuarioteste@hotmail.com"

Senha: "123456"

Saída esperada: O código de status HTTP retornado deve ser 400 (BAD REQUEST) e a mensagem de erro deve ser "CPF existente".

## Teste 17: Verificar inserção de CPF inválido
Descrição: Verificar se o sistema impede a inserção de um CPF inválido.

Entrada:

Documento: "12345678901" (CPF inválido)

Nome: "Usuario Teste"

Endereço: "Rua Teste 123"

E-mail: "usuarioteste@hotmail.com"

Senha: "123456"

Saída esperada: O código de status HTTP retornado deve ser 400 (BAD REQUEST) e a mensagem de erro deve ser "CPF inválido".

## Teste 18: Verificar inserção de nome inválido na PF
Descrição: Verificar se o sistema impede a inserção de um nome inválido para pessoa física.

Entrada:

Documento: "60546547052"

Nome: "Jose-Alberto20" (nome inválido)

Endereço: "Rua Teste 123"

E-mail: "usuarioteste@hotmail.com"

Senha: "123456"

Saída esperada: O código de status HTTP retornado deve ser 400 (BAD REQUEST) e a mensagem de erro deve ser "Formato de nome inválido: use apenas letras".

## Teste 19: Verificar inserção de endereço inválido na PF
Descrição: Verificar se o sistema impede a inserção de um endereço inválido para pessoa física.

Entrada:

Documento: "34761531002"

Nome: "Usuario Teste"

Endereço: "Rua Teste_8193" (endereço inválido)

E-mail: "usuarioteste@hotmail.com"

Senha: "123456"

Saída esperada: O código de status HTTP retornado deve ser 400 (BAD REQUEST) e a mensagem de erro deve ser "Formato de endereço inválido: use apenas letras e números".

## Teste 20: Verificar inserção de email inválido na PF
Descrição: Verificar se o sistema impede a inserção de um e-mail inválido para pessoa física.

Entrada:

Documento: "57157459000"

Nome: "Usuario Teste"

Endereço: "Rua Teste 123"

E-mail: "usuariotestehotmail.com" (e-mail inválido)

Senha: "123456"

Saída esperada: O código de status HTTP retornado deve ser 400 (BAD REQUEST) e a mensagem de erro deve ser "Formato de email inválido".

## Observações
Os testes podem ser executados em ambiente de teste.
Os testes devem ser repetíveis, ou seja, podem ser executados várias vezes sem afetar uns aos outros ou podem executar separadamente.
Este plano de teste pode ser expandido conforme necessário para cobrir mais cenários de uso.
