# Programação Java
## Requisito 6
### // Especificações de Requisitos

#### Requerimientos US:
#### [ml-sections-06](https://netto-meli.github.io/w4g9-projeto-final/guide/projeto_integrador/US-6/Requisito_6_-_Felipe_Bontempo.pdf) <-(clique para acessar PDF)

**Importante:**
As histórias de usuários são narradas do ponto de vista do comprador com base em
suas necessidades. Os serviços são expostos a partir do Marketplace para serem
consumidos pelo comprador que os solicita. Os contratos referem-se à História do Usuário.

## Gerenciar setores, busca, atualização, criação, etc
### User Story


|                                                                 User Story Code: ml-sections-01                                                      | Horas estimadas |
|:----------------------------------------------------------------------------------------------------------------------------------------------------:|:---------------:|
|                                              **User Story Name: Representante pode manipular setores**                                               |       35        |
| **COMO** Representante do armazém, **QUERO** listar todos os setores **PARA** escolher um setor adequado para armazenar produtos que recém chegaram. |                 |

| **CENÁRIO:** quando os Vendedores (supermercados, empresas do setor alimentício, atacadistas, etc.) nos enviam a mercadoria para armazená-la em nosso depósito, o representante tem como tarefa escolher entre uma lista de setores o que melhor se enquadra a mercadoria que chegou. |
|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **DADO QUE** o produto de um Vendedor é registrado.                                                                                                                                                                                                                                   |
| **E** que o armazém é válido                                                                                                                                                                                                                                                          |
| **E** que o representante pertence ao armazém                                                                                                                                                                                                                                         |
| **E** que o representante precisa localizar o setor adequado                                                                                                                                                                                                                          |
| **QUANDO** o representante precisa localizar um setor adequado para o lote                                                                                                                                                                                                            |
| **ENTÃO o representante faz** pesquisa nos setores do armazém                                                                                                                                                                                                                         |

| VALIDAÇÃO                                                 |
|-----------------------------------------------------------|
| * Autentique-se como representante e acesse os terminais. | 
| * Validação de parâmetros obrigatórios.                   |


##### Representação JSON:
<details><summary>Response</summary><p>

```JSON
[
    {
        "id": 2,
        "name": "Sector 005",
        "refrigeration_type": "FROZEN",
        "warehouse_code": 1,
        "stock_limit": 77,
        "current_stock": 10,
        "min_teperature": 0.3,
        "max_teperature": 0.2
    },
    {
        "id": 3,
        "name": "Sector 005",
        "refrigeration_type": "FROZEN",
        "warehouse_code": 1,
        "stock_limit": 77,
        "current_stock": 10,
        "min_teperature": 0.3,
        "max_teperature": 0.2
    }
]
```
</p></details>


### Contratos relativos a User Story

| HTTP   | Planilha URI                        | Descripcion                  | US-code        |
|--------|-------------------------------------|------------------------------|----------------|
| GET    | /api/v1/fresh-products/section      | Retorna uma lista de section | ml-sections-01 |
| GET    | /api/v1/fresh-products/section/{id} | Retorna uma section pelo ID  | ml-sections-01 |
| POST   | /api/v1/fresh-products/section      | Cadastrar uma nova section   | ml-sections-01 |
| PUT    | /api/v1/fresh-products/section/{id} | Atualiza uma section pelo ID | ml-sections-01 |
| DELETE | /api/v1/fresh-products/section/{id} | Deleta uma section pelo ID   | ml-sections-01 |

> Observação:
Contemple outros tipos de erros.
Use o script de carregamento
Trabalhe com o token de acesso para o pedido como um representante autenticado.