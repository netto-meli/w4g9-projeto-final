# Programação Java

## Requisito 5

### // Especificações de Requisitos

#### Requerimientos US:

#### ml-check-batch-stock-due-date-01

**Importante:**
As histórias do usuário são contadas do ponto de vista do representante do warehouse com base
em suas necessidades. Os serviços são expostos a partir do armazém de atendimento. Os contratos
referem-se à História do Usuário.

Sinônimos do Representante: supervisor, líder.

## Consultar a data de validade por lote

### User Story

|                                                            User Story Code: ml-check-batch-stock-due-date-01                                                            | Horas estimadas |
|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:---------------:|
|                                                       **User Story Name: Consultar a data de validade por lote**                                                        ||
| **COMO** _Representante_ **QUERO** poder consultar os produtos em estoque que estão prestes a expirar no almoxarifado, a fim de aplicar alguma ação comercial com eles. ||

| **CENÁRIO 1:** um produto do vendedor é registrado.                                                                                 |
|:------------------------------------------------------------------------------------------------------------------------------------|
| **DESDE** o produto de um Vendedor é registrado                                                                                     |
| **E** que o armazém é válido                                                                                                        |
| **E** que o representante pertence ao armazém                                                                                       |
| **QUANDO** o representante insere o número de dias                                                                                  |
| **ENTÃO** uma lista de produtos é exibida com uma data de validade entre a data atual e a data futura (data atual + dias inseridos) |
| **E** que o produto tem um número de lote                                                                                           |
| **E** que o produto é filtrado por número de lote                                                                                   |
| **E** que o produto é filtrado por data de validade                                                                                 |
| **E** que o produto seja filtrado de acordo com a categoria dos produtos (frescos, congelados,refrigerados)                         |

| VALIDAÇÃO                                                    |
|--------------------------------------------------------------|
| * Autentique-se como representante e acesse os terminais.    |   
| * O produto não deve aparecer no setor errado.               |    
| * O produto deve aparecer em lotes diferentes                |     
| * A data de validade deve estar dentro do intervalo inserido |

##### Representação JSON:

<details><summary>Response</summary><p>

```JSON
{
  "batch_stock": [
    {
      "batch_number": "int",
      "product_id": "String",
      "product_type_id": "String",
      "due_date": "LocalDate",
      "quantity": "int"
    }
  ]
}
```
</p></details>

### Contratos relativos a User Story

| HTTP | Modelo de URI                                                                                           | Descrição                                                                                                                                                                          | US-code                          |
|------|---------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------|
| GET  | /api/v1/fresh-products/due-date?queryparam=[number of days]&queryparam=[section]                        | Obtenha todos os lotes armazenados em um setor de um armazém ordenados por sua data de vencimento.                                                                                 | ml-check-batch-stock-due-date-01 |
| GET  | /api/v1/fresh-products/due-date/list?queryparam=[number of days]&queryparam=[category]&queryparam=[asc] | Obtenha uma lista dos lotes pedidos por prazo de validade, que pertencem a uma determinada categoria de produto.<br>Category:<br>FS = Fresco<br>RF = Refrigerado<br>FF = Congelado | ml-check-batch-stock-due-date-01 |

> Observação:
Contemple outros tipos de erros.
Use o script de carregamento
Trabalhe com o token de acesso para o pedido como um cliente autenticado.