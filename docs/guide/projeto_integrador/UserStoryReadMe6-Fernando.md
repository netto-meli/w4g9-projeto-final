# Programação Java
## Requisito 6
### // Especificações de Requisitos

#### Requerimientos US:
#### ml-delivery-orders-01

**Importante:**
As histórias dos usuários são contadas do ponto de vista do representante e com base em suas necessidades.
Os serviços são expostos a partir do armazém de atendimento. Os contratos referem-se à História do Usuário.

Sinônimos do Representante: supervisor, líder.

## Gerenciar Entregadores e suas Entregas
### User Story


|                                                           User Story Code: ml-delivery-orders-01                                                            | Horas estimadas |
|:-----------------------------------------------------------------------------------------------------------------------------------------------------------:|:---------------:|
|                                                 **User Story Name: Gerenciar Entregadores e suas Entregas**                                                 |       12        |
| **COMO** _Representante_ **QUERO** adicionar _Entregadores_ na Base de Dados **PARA** que eles possam fazer entregas das Ordens de Pedido dos _Compradores_ |                 |

| **CENÁRIO 1:** O Entregador é registrado.                  |
|:-----------------------------------------------------------|
| **DESDE** o Representante esteja autorizado                |
| **E** que o Entregador tenha um meio de condução           |
| **E** que o meio de condução tenha placa                   |
| **E/OU** que o haja espaço para produtos frescos            |
| **E/OU** que o haja espaço para produtos refrigerados      |
| **E/OU** que o haja espaço para produtos congelados        |
| **QUANDO** o Representante aciona o cadastro do Entregador |
| **ENTÃO** um Entregador é cadastrado                       |
| **E** seu status fica disponivel para entrega              |

| **CENÁRIO 2:** Um Entregador chamado para entrega.                           |
|:-----------------------------------------------------------------------------|
| **DESDE** o Entregador não esteja fazendo uma entrega                        |
| **E** que o haja espaço para produtos frescos da lista de pedidos            |
| **E** que o haja espaço para produtos refrigerados da lista de pedidos       |
| **E** que o haja espaço para produtos congelados da lista de pedidos      |
| **QUANDO** o Representante aciona a busca de Entregadores                    |
| **ENTÃO** um Entregador estipulado para a entrega dos pedidos                |
| **E** seu estatus fica como indisponível para entrega ("Em Rota de Entrega") |
| **E** recebe como pagamento 25% do valor do frete do pedido.                 |

| VALIDAÇÃO                                                  |
|------------------------------------------------------------|
| * Autentique-se como Representante e acesse os terminais.  |
| * Validação de parâmetros obrigatórios.                    |
| * Calculo do valor do frete total dos pedidos              |
| * Para Frete for gratis, o entregador recebe um valor fixo |

##### Representação JSON:
<details><summary>Request</summary><p>

Referente ao Entregador
```JSON
{
  "name": "String",
  "car_model": "String",
  "car_plate": "string",
  "fresh_max_quantity": "int",
  "frozen_max_quantity": "int",
  "cold_max_quantity": "int"
}
```

Referente a Lista de Entregas a serem feitas
```JSON
[
  {
    "id": "int"
  }
]
```
</p></details>

<details><summary>Response</summary><p>

```JSON
{
  "id": "int",
  "name": "Strting",
  "car_model": "String",
  "car_plate": "String",
  "delivery_order_list": [
    {
      "id": "int",
      "buyer_id": "int",
      "order_item_response_dtolist": [
        {
          "quantity": "int",
          "id_advertise": "int"
        }
      ],
      "order_status": "String",
      "shipping_rate": "float",
      "total_value": "float"
    }
  ],
  "in_route": "boolean",
  "payment_for_delivery": "float",
  "salary": "float"
}
```
</p></details>

### Contratos relativos a User Story
| HTTP   | Modelo de URI                                                | Descrição                                                                                                                | US-code               |
|--------|--------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------|-----------------------|
| POST   | api/v1/fresh-products/delivery                               | Cadastre um Emtregador. Devolva o Entregador criado com o código de status "201 CREATED".                                | ml-delivery-orders-01 |
| PUT    | api/v1/fresh-products/delivery/[idEntregador]                | Altere um Emtregador. Devolva o Entregador alterado com o código de status "200 OK".                                     | ml-delivery-orders-01 |
| DELETE | api/v1/fresh-products/delivery/[idEntregador]                | Exclua um Emtregador. Devolva o código de status "200 OK".                                                               | ml-delivery-orders-01 |
| GET    | api/v1/fresh-products/delivery/[idEntregador]                | Veja um Entregador. <br>Se o Entregador não existir, deve retornar um "404 Not Found".                                   | ml-delivery-orders-01 |
| GET    | api/v1/fresh-products/delivery/                              | Veja uma lista de Entregadores. <br>Se a lista não existir, ela deve retornar um "404 Not Found".                        | ml-delivery-orders-01 |
| GET    | api/v1/fresh-products/delivery/byStatus/?isInRoute=[boolean] | Veja uma lista de Entregadores, filtrados por estar ou não em rota de entrega.                                           | ml-delivery-orders-01 |
| POST   | api/v1/fresh-products/delivery/delivery                      | Solicite Entregador para delivery de Pedidos. <br>Se não houver entregador disponivel, deve retornar um "404 Not Found". | ml-delivery-orders-01 |
| PUT    | api/v1/fresh-products/delivery/delivery/[idEntregador]       | Verifica Pedidos entregues do Entregador.                                                                                | ml-delivery-orders-01 |

> Observação:<br>
Contemple outros tipos de erros.<br>
Use o script de carregamento<br>
Trabalhe com o token de acesso para o Representante autenticado.<br>
O Entregador que estiver em rota de Delivery, não pode ser Alterado nem Excluído, e não pode receber mais Ordens de Pedidos.