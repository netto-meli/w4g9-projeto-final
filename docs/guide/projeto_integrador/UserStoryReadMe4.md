# Programação Java
## Requisito 4
### // Especificações de Requisitos

#### Requerimientos US:
#### ml-check-product-stock-in-warehouses-04

**Importante:**
As histórias dos usuários são contadas do ponto de vista do representante e com base em
suas necessidades. Os serviços são expostos a partir do armazém de atendimento. Os
contratos referem-se à História do Usuário.

Sinônimos do Representante: supervisor, líder.

## Consultar o estoque de um produto em todos os armazéns
### User Story

|                                            User Story Code: ml-check-product-stock-in-warehouses-01                                             | Horas estimadas |
|:-----------------------------------------------------------------------------------------------------------------------------------------------:|:---------------:|
|                                   **User Story Name: Consultar o estoque de um produto em todos os armazéns**                                   |                 |
| **COMO** _Representante_ **QUERO** poder consultar um produto em todos os armazéns **PARA** saber o estoque em cada armazém do referido produto ||

| **CENÁRIO 1:** um produto do vendedor é registrado.         |
|:------------------------------------------------------------|
| **DESDE** o produto de um Vendedor é registrado             |
| **E** que o armazém é válido                                |
| **QUANDO** o representante insere o código do produto       |
| **ENTÃO** a quantidade do produto é exibida em cada armazém |

| VALIDAÇÃO                                                 |
|-----------------------------------------------------------|
| * Autentique-se como representante e acesse os terminais. |

##### Representação JSON:
<details><summary>Response</summary><p>

```JSON
{ "product_id": "String",
  "warehouses": [
      {
        "warehouse_code":"String",
        "total_quantity":"String"
      }
]}
```
</p>
</details>

### Contratos referentes a User Story
| HTTP | Modelo de URI                                                                                                                                       | Descrição                                                                                                                                 | US-code                                 |
|---|-----------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------|
| POST | /api/v1/fresh-products/warehouse/querytype=id product] | Obtenha a quantidade total de produtos por armazém.<br>Se o produto não existe em nenhum depósito, você deve retornar um "404 Not Found". | ml-check-product-stock-in-warehouses-01 |

> Observação:
Contemple outros tipos de erros.
Use o script de carregamento
Trabalhe com o token de acesso para o Pedido como um cliente autenticado.