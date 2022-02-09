# Programação Java
## Requisito 1
### // Especificações de Requisitos

#### Requerimientos US:
#### ml-insert-batch-in-fulfillment-warehouse-01

**Importante:**
As histórias do usuário são contadas do ponto de vista do representante do armazém com base
em suas necessidades. Os serviços são expostos a partir do armazém de fullfilment. Os contratos
referem-se à História do Usuário.

Sinônimos do Representante: supervisor, líder.

## Insira o lote no armazém de atendimento
### User Story

|                                                   User Story Code: ml-insert-batch-in-fulfillment-warehouse-01                                                   | Horas estimadas |
|:----------------------------------------------------------------------------------------------------------------------------------------------------------------:|:---------------:|
|                                                   **User Story Name:Insira o lote no armazém de atendimento**                                                    |                 |
| **COMO** _Representante_ do armazém de distribuição, **QUERO** inserir um lote de produtos no armazém de distribuição **PARA** registrar a existência de estoque ||

| **CENÁRIO 1:** O produto de um vendedor é registrado.    |
|:---------------------------------------------------------|
| **DESDE** o produto de um Vendedor é registrado          |
| **E** que o armazém é válido                             |
| **E** que o representante pertence ao armazém            |
| **E** que o setor é válido                               |
| **E** que o setor corresponde ao tipo de produto         |
| **E** que o setor tenha espaço disponível                |
| **QUANDO** o representante entra no lote                 |
| **ENTÃO** o registro de compra é criado                  |
| **E** o lote é atribuído a um setor                      |
| **E** o representante é associado ao registro de estoque |

| VALIDAÇÃO                                                               |
|-------------------------------------------------------------------------|
| * Autentique-se como representante e acesse os terminais.               |
| * Registre o lote no setor correspondente.                              |
| * Verifique se o setor de warehouse está sendo registrado corretamente. |

#### Nota:
* Ter as informações necessárias para entender em que setor deve ser armazenada a
mercadoria para que fique em bom estado enquanto estiver no almoxarifado e para que
se possa mostrar ao colaborador que vai procurar o produto (picking).

##### Representação JSON:
<details><summary>Request</summary><p>

```JSON
{
  "inbound_order": {
    "order_number": "int",
    "order_date": "LocalDate",
    "section": {
      "section_code": "string",
      "warehouse_code": "string"
    },
    "batch_stock": [
      {
        "batch_number": "int",
        "advertise_id": "String",
        "current_temperature": "Float",
        "minimum_temperature": "Float",
        "initial_quantity": "int",
        "current_quantity": "int",
        "manufacturing_date": "LocalDate",
        "manufacturing_time": "LocalDateTime",
        "due_date": "LocalDate"
      }
    ]
  }
}
```
</p>
</details>

<details><summary>Response</summary><p>

```JSON
{ "batchStock": [
      {
        "batch_number":"int",
        "product_nd":"String",
        "current_temperature":"Float",
        "minimum_temperature":"Float",
        "initial_quantity":"int",
        "current_quantity":"int",
        "manufacturing_Date":"LocalDate",
        "manufacturing_Time":"LocalDateTime",
        "due_date":"LocalDate"
      }
]}
```
</p>
</details>

### Contratos relativos a User Story
| HTTP | Modelo de URI                        | Descrição                                                                                                            | US-code |
|------|--------------------------------------|----------------------------------------------------------------------------------------------------------------------|---|
| POST | /api/v1/fresh-products/inboundorder/ | Cadastre um lote com o estoque de produtos que o compõe. Devolva o lote criado com o código de status "201 CREATED". | ml-insert-batch-in-fulfillment-warehouse-01 |
| PUT  | /api/v1/fresh-products/inboundorder/ | Caso o lote já exista e deva ser atualizado. <br>Devolva o estoque atualizado com o código de status "201 CREATED".  | ml-insert-batch-in-fulfillment-warehouse-01 |

> Observação:
Contemple outros tipos de erros.
Use o script de carregamento
Trabalhe com o token de acesso para o sellOrder como um cliente autenticado.