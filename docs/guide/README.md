# BootCamp

## Wave 4 - Grupo 9

#### Mercado Livre / DigitalHouse

![BGW4G9](bg.jpg "Background Wave 4 - Grupo 9")

### Endpoints Referentes aos Requisitos
<details><summary>US - 1</summary><p>

| HTTP | LINK                                                     |
|------|----------------------------------------------------------|
| POST | http://localhost:8080/api/v1/fresh-products/inboundorder |
| PUT  | http://localhost:8080/api/v1/fresh-products/inboundorder |

```JSON
{
  "order_number": 1,
  "order_date": "2022-01-28",
  "section": {
    "section_code": "1",
    "warehouse_code": "1"
  },
  "batch_stock": [
    {
      "batch_number": 1,
      "advertise_id": 1,
      "current_temperature": 9.0,
      "minimum_temperature": 1.0,
      "initial_quantity": 2,
      "current_quantity": 2,
      "manufacturing_date": "2022-01-28",
      "manufacturing_time": "2022-01-28T08:28:56.775775",
      "due_date": "2022-01-28"
    }
  ]
}
```
</p></details>

<details><summary>US - 2</summary><p>

| HTTP | Modelo de URI                                                                                    |
|------|--------------------------------------------------------------------------------------------------|
| GET  | http://localhost:8080/api/v1/fresh-products/product                                              |
| GET  | http://localhost:8080/api/v1/fresh-products/product/listCategory/{{categoryProd}}                |
| POST | http://localhost:8080/api/v1/fresh-products/cart/addProduct/{{idBuyer}}?idAdvertise=&qtdProduct= |
| GET  | http://localhost:8080/api/v1/fresh-products/cart/{{idBuyer}}                                     |
| PUT  | http://localhost:8080/api/v1/fresh-products/cart/createSellOrder/{{idBuyer}}                     |


</p></details>

<details><summary>US - 3</summary><p>

| HTTP | LINK                                                                        |
|------|-----------------------------------------------------------------------------|
| GET  | http://localhost:8080/api/v1/fresh-products/product/listBatch/?id=          |
| GET  | http://localhost:8080/api/v1/fresh-products/product/listBatch/{{order}}?id= |

</p></details>

<details><summary>US - 4</summary><p>

| HTTP | LINK                                                                   |
|------|------------------------------------------------------------------------|
| GET  | http://localhost:8080/api/v1/fresh-products/warehouse/byProduct/{{id}} |

</p></details>

<details><summary>US - 5</summary><p>

| HTTP | LINK                                                                                                              |
|------|-------------------------------------------------------------------------------------------------------------------|
| GET  | http://localhost:8080/api/v1/fresh-products/due-date/bySection/{{numberOfDays}}?sectionId=                        |
| GET  | http://localhost:8080/api/v1/fresh-products/due-date/byRefrigeration/{{numberOfDays}}?refrigerationType=&orderBy= |

</p></details>

<details><summary>US - 6</summary><p>

| HTTP | LINK                                                                                                              |
|------|-------------------------------------------------------------------------------------------------------------------|
| GET  | http://localhost:8080/api/v1/fresh-products/due-date/bySection/{{numberOfDays}}?sectionId=                        |
| GET  | http://localhost:8080/api/v1/fresh-products/due-date/byRefrigeration/{{numberOfDays}}?refrigerationType=&orderBy= |

</p></details>




``` plantuml
@startuml
skinparam actorStyle awesome
"Felipe" as flp
"Fernando" as frn
"Leonardo" as lnd
"Marcos" as mcs
"Rafael" as rfl
flp --> (US - 6 - Gerenciamento de Setores)
frn --> (US - 6 - Entregadores e Delivery)
lnd --> (US - 6 - XXXXX)
mcs --> (US - 6 - Receber senha perdida por e-mail)
rfl --> (US - 6 - XXXXXX)
@enduml
```
