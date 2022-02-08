# Programação Java
## Requisito 2
### // Especificações de Requisitos

#### Requerimientos US:
#### ml-add-products-to-cart-01

**Importante:**
As histórias de usuários são narradas do ponto de vista do comprador com base em
suas necessidades. Os serviços são expostos a partir do Marketplace para serem
consumidos pelo comprador que os solicita. Os contratos referem-se à História do Usuário.

## Registrar Venda: Adicione o produto ao carrinho de comprasRegistrar Venda: Adicione o produto ao carrinho de compras
### User Story


|                                      User Story Code: ml-add-products-to-cart-01                                       | Horas estimadas |
|:----------------------------------------------------------------------------------------------------------------------:|:---------------:|
|                             **User Story Name: Adicionar produto ao carrinho de compras**                              |                 |
| **COMO** _comprador **QUERO** adicionar produtos ao carrinho de compras do Marketplace **PARA** comprá-los, se desejar ||

| **CENÁRIO 1:** O produto de um vendedor é registrado.                  |
|:-----------------------------------------------------------------------|
| **DESDE** o produto de um Vendedor é registrado                        |
| **E** que o comprador esteja cadastrado                                |
| **E** que o produto tem estoque                                        |
| **E** que o prazo de validade do produto não seja inferior a 3 semanas |
| **QUANDO** o comprador adiciona o produto com a quantidade ao carrinho |
| **ENTÃO** um produto é adicionado ao carrinho de compras               |
| **E** atualiza o estoque atual do produto                              |

| VALIDAÇÃO                                             |
|-------------------------------------------------------|
| * Autentique-se como comprador e acesse os terminais. | 
| * Consultar produto                                   |
| * Adicione um produto ao carrinho do comprador.       |

> Observação:
Os pedidos de compra (purchaseOrder) feitos pelo comprador terão apenas o status de Order (OrderStatus) Cart

##### Representação JSON:
<details><summary>Request</summary><p>

```JSON
{
  "purchase_order": {
    "date": "LocalDate",
    "buyer_id": "String",
    "order_status": {
      "status_code": "String"
    },
    "products": [{
      "product_id": "String",
      "quantity": "int"
    }]
  }
}
```
</p></details>

<details><summary>Response</summary><p>

```JSON
{
  "total_price": "double"
}
```
</p></details>

### Contratos relativos a User Story
| HTTP | Modelo de URI                                             | Descrição                                                                                                                                                                                                                                                                           | US-code |
|------|-----------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---|
| GET  | /api/v1/fresh-products/                                   | Veja uma lista completa de produtos. <br>Se a lista não existir, ela deve retornar um "404 Not Found".                                                                                                                                                                              | ml-add-products-to-cart-01 |
| GET  | /api/v1/fresh-products/list?querytype=[categoría producto] | Veja uma lista de produtos por categoria. <br>category:<br> FS = Fresco <br>RF = Refrigerado <br>FF = Congelado<br> Se a lista não existir, ela deve retornar um "404 Not Found".                                                                                                   | ml-add-products-to-cart-01                                                                                                                                                                                                                                                   |
| POST | /api/v1/fresh-products/orders/                            | Registre um sellOrder com a lista de produtos que compõem o PurchaseOrder. <br>Calcule o preço final e devolva-o juntamente com o código de status "201 CREATED". <br>Se não houver estoque de um produto, notifique a situação retornando um erro por produto, não no nível do sellOrder. | ml-add-products-to-cart-01 |
| GET  | /api/v1/fresh-products/orders/querytype=[idOrder]|| Mostrar produtos no sellOrder. ml-add-products-to-cart-01 |
| PUT | /api/v1/fresh-products/orders/query param=[idOrder] |Modifique o sellOrder existente. torná-lo do tipo de carrinho para modificar | ml-add-products-to-cart-01 |

> Observação:
Contemple outros tipos de erros.
Use o script de carregamento
Trabalhe com o token de acesso para o sellOrder como um cliente autenticado.