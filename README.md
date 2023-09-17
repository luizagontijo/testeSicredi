# **Desafio técnico - Sicredi**

## **Informações mínimas do projeto**
### Ferramentas utilizadas
Java version:1.8.0_291

### Dependências:
RestAssured version: 3.0.0 <br>
Junit version: 4.12 <br>
Apache-Maven: 3.9.2

## Como executar
Para executar todos os testes, digitar os seguintes comandos na raiz do projeto:<br>
`mvn test`

Para executar apenas uma classe específica, digitar os seguintes comandos na raiz do projeto:<br>
`mvn test -Dtest=classname`

Para gerar o relatório, digitar os seguintes comandos após rodar os testes:<br>
`allure serve allure-results `

## Plano de teste e estratégia de testes
### Plano de Testes (o que será testado)
* Buscar o status da aplicação (GET /test)<br>
* Buscar usuário para autenticação (GET /users)<br>
* Criação de token para Autenticação (POST /auth/login)<br>
* Buscar produtos com autenticação (GET /auth/products)<br>
* Criação de produto (POST /products/add)<br>
* Buscar todos os produtos (GET /products)<br>
* Buscar apenas um produto por id (GET /products/{id})<br>

### Estratégia de testes (como será testado)
**A classe StatusTest valida o status da aplicação.<br>**
CT 01 - Verificar o Status da Aplicação

**A classe BuscarUsuariosTest valida a lista de usuários para autenticação na aplicação e se cada um deles tem o campo usuário e senha preenchidos.<br>**
CT 02 - Buscar lista de usuários na API<br>
CT 03 - Buscar campo \"username\" na lista de usuários na API<br>
CT 04 - Buscar campo \"password\" na lista de usuários na API

**A classe GerarTokenTest valida a criação de um token e resposta corpo da resposta.**<br>
CT 05 - Status ao gerar token<br>
CT 06 - Corpo da resposta ao gerar token

**A classe BuscarProdutosAutenticadoTest valida que a API retorna uma lista de produtos que não esteja vazia para usuário autenticado.<br>**
CT 07 - Buscar Produtos na API com token válido<br>
CT 08 - Buscar Produtos na API sem token<br>
CT 09 - Buscar Produtos na API com token expirado

**A classe CadastrarProdutosTest valida o cadastro de produtos na API.**<br>
CT 10 - Cadastrar Produtos na API

**A classe BuscaProdutosTest valida que a API retorna uma lista de produtos que não esteja vazia para usuário não autenticado.<br>**
CT 11 - Buscar Produtos na API sem autenticação

**A classe BuscarProdutosPorIdTest valida o retorno da API para o produto de ID igual a 1.<br>**
CT 12 - Buscar Produtos por ID na API<br>
CT 13 - Buscar Produtos por ID inexistente na API

## Bugs
Foram identificados dois cenários com bug:
1. **CT 09 - Buscar Produtos na API com token expirado<br>**
**Comportamento esperado<br>**
Receber no corpo da respostas os seguintes dados:<br>
_"name": "JsonWebTokenError"<br>
"message": "Invalid/Expired Token!"<br>_
**Comportamento autal<br>**
Estão sendo recebidos os seguintes dados:<br>
_"name": " TokenExpiredError"<br>
"message": " Token Expired!"_


2. **CT 05 - Status ao gerar token**<br>
**Comportamento esperado<br>**
Ao ter sucesso na requisição, receber o código de status 201<br>
**Comportamento autal<br>**
Ao ter sucesso na requisição, é recebido o código de status 200


## Melhorias
Na funcionalidade de Cadastro de Produtos poderia ser exigida autenticação do usuário para realizar a ação, evitando assim cadastro indevido de produtos.