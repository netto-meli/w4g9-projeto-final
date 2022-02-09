# Programação Java

## Requisito 3

### // Especificações de Requisitos

#### Requerimientos US:

#### ml-check-product-location-in-warehouse-01

**Importante:**
As histórias do usuário são contadas do ponto de vista do representante do warehouse com base em suas necessidades. Os
serviços são expostos a partir do armazém de atendimento. Os contratos referem-se à História do Usuário.

Sinônimos do Representante: supervisor, líder.

## Verifique a localização de um produto no armazém

### User Story


|                                                      User Story Code: ml-check-product-location-in-warehouse-01                                                      | Horas estimadas |
|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:---------------:|
|                                                **User Story Name: Verifique a localização de um produto no armazém**                                                 ||
| **COMO** _Representante_ **QUERO** poder consultar um produto em stock no armazém **PARA** saber a sua localização num setor e os diferentes lotes onde se encontra. ||

| **CENÁRIO 1:** um produto do vendedor é registrado.                                             |
|:------------------------------------------------------------------------------------------------|
| **DESDE** o produto de um Vendedor é registrado                                                 |
| **E** que o armazém é válido                                                                    |
| **E** que o representante pertence ao armazém                                                   |
| **E** que o setor é válido                                                                      |
| **E** que o setor corresponde ao tipo de produto                                                |
| **E** que o setor é dono do lote                                                                |
| **E** que o lote possui o produto.                                                              |
| **QUANDO** o representante insere o código do produto                                           |
| **ENTÃO** a localização do produto em um setor é exibida                                        |
| **E** que o produto tem um número de lote                                                       |
| **E** que o produto é filtrado (ordenado) por número de lote                                    |
| **E** que o produto é filtrado (ordenado) pela quantidade atual do lote (menor para maior)      |
| **E** E que o produto é filtrado (ordenado) por data de validade (mais antigo para o mais novo) |

| VALIDAÇÃO                                                                  |
|----------------------------------------------------------------------------|
| * Autentique-se como representante e acesse os terminais.                  | 
| * Autentique-se como representante e acesse os terminais.                  |
| * O produto não deve estar vencido ou prestes a expirar (mínimo 3 semanas) |

##### Representação JSON:

<details><summary>Response</summary><p>

```JSON
{
  "section": {
    "section_code": "String",
    "warehouse_code": "String"
  },
  "product_id": "String",
  "batch_stock": [
    {
      "batch_number": "int",
      "current_quantity": "int",
      "due_date": "LocalDate"
    }
  ]
}
```
</p></details>

### Contratos relativos a User Story

| HTTP | Modelo de URI                                                    | Descrição                                                                                                                                                                          | US-code                    |
|------|------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------|
| GET  | /api/v1/fresh-products/list?querytype=[idProducto]               | Veja uma lista de produtos com todos os lotes onde aparece.<br>Se a lista não existir, ela deve retornar um “404 Not Found”.                                                       | ml-check-product-location-in-warehouse-01 |
| GET  | /api/v1/fresh-products/list?querytype=[idProducto]&querytype=[L] | Veja uma lista de produtos com todos os lotes onde aparece.<br>Ordenados por:<br>L = ordenado por lote<br>C = ordenado por quantidade atual<br>F = ordenado por data de vencimento | ml-check-product-location-in-warehouse-01 |

> Observação:
Contemple outros tipos de erros. Trabalhe com o token de acesso para o pedido como um cliente autenticado.