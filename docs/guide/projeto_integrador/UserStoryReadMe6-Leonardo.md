# Programação Java
## Requisito 6
### // Especificações de Requisitos

#### Requerimentos US:
#### [ml-creat-crud-for-buyer-01](https://netto-meli.github.io/w4g9-projeto-final/guide/projeto_integrador/US-6/Requisito_6_CRUD_Buyer_Leonardo.pdf) <-(clique para acessar PDF)

**Importante:**
As histórias de usuários são contadas do ponto de vista do representante, 
vendedor e comprador warehouse com base em suas necessidades. Os serviços são 
expostos a partir da base de dados referente aos compradores que são consumidos 
pelos representantes, comprador e vendedor solicitante. Os contratos referem-se 
à História do Usuário.

## Cadastro do comprador: Adicione, altere, busque e exclua dados.
### User Story


|                                                                                        User Story Code: ml-creat-crud-for-buyer-06                                                                                        | Horas estimadas |
|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:---------------:|
|                                                                                        **User Story Name: Cadastro do comprador**                                                                                         |      20 H       |
| **COMO** vendedor,comprador e representante **QUERO** poder realizar buscas, incluir, deletar e alterar um comprador **PARA** manter a base de compradores sempre atualizada ou consultar seus próprios dados(Comprador). |                 | 

| **CENÁRIO 1:** Um comprador é registrado.                                                                                                                                                                                                 |     
|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **DESDE** o comprador não exista na base atual. **E** que possua um usuário para inseri-lo. **QUANDO** o usuário inclui um novo comprador na base **ENTÃO**, ele poderá ser consultado **E** alterado atualizando o atual **E** excluido. |
                         

| VALIDAÇÃO                                                                                                                                                      |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| * Autentique como vendedor, comprador ou representante e acesse os terminais para cadastrar um comprador.                                                      | 
 | * Para realizar a consulta de todos os compradores é necessário ter um perfil ADM, se não o usuário terá acesso somente a suas informações (usuário logado).   |
| * Para realizar a alteração de dados de um comprador é necessário ter um perfil ADM, se não o usuário terá acesso somente a suas informações (usuário logado). |
| * Para exclusão de um comprador é necessário ter um perfil ADM, se não o usuário terá acesso somente a suas informações (usuário logado).                      |
| * Validação de parâmetros obrigatórios.                                                                                                                        |

##### Representação JSON:
<details><summary>Request</summary><p>

```JSON
{
  "username": "String",
  "name": "String",
  "email": "String",
  "pass": "String",
  "address": "String"
}
```
</p></details>

<details><summary>Response</summary><p>

```JSON
    [
  {
    "id": 1,
    "username": "DhMaury",
    "name": "Maury",
    "email": "DHMaury@gmail.com",
    "address": "Endereco Sul"
  },
  {
    "id": 2,
    "username": "DhKenyo",
    "name": "Kenyo",
    "email": "DHKenyo@gmail.com",
    "address": "Endereco Desc"
  },
  {
    "id": 3,
    "username": "DhMichele",
    "name": "Michele",
    "email": "DHMichele@gmail.com",
    "address": "Endereco Sao Paulo"
  }
]

```
</p></details>

### Contratos relativos a User Story
| HTTP   | Modelo de URI                      | Descrição                                                                                                                | US-code                    |
|--------|------------------------------------|--------------------------------------------------------------------------------------------------------------------------|----------------------------|
| GET    | api/v1/fresh-products/buyer        | Listar todos compradores da base de dados com o retorno código de status "200 ok".                                       | ml-creat-crud-for-buyer-06 |
| GET    | api/v1/fresh-products/buyer/{{id}} | Listar dados de um comprador com base no id informado e com o retorno código de status "200 ok".                         | ml-creat-crud-for-buyer-06 |
| POST   | api/v1/fresh-products/buyer        | Cadastrar um comprador e devolver o código de status "201 - CREATED" e no body as informações do comprador adicionado.   | ml-creat-crud-for-buyer-06 |
| DELETE | api/v1/fresh-products/buyer/{{id}} | Deletar um comprador e devolver o codigo de retorno de status "200 - OK" e no body uma mensagem informando o id excluido | ml-creat-crud-for-buyer-06 |
| PUT    | api/v1/fresh-products/buyer/{{id}} | Alterar um comprador pelo número de id e devolver o código de status "200 - OK" com os dados alterados no body.          | ml-creat-crud-for-buyer-06 |

> Observação:
Contemple outros tipos de erros.
Use o script de carregamento.
Trabalhe com o token de acesso de um usuário autenticado.
