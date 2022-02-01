#### Modelagem de Classe e ER

## Versão Final

```mermaid
classDiagram
User <|-- Buyer
User <|-- Seller
User <|-- Representative
Advertise o-- Seller
Advertise --* Product
Advertise <-- OrderItem
Batch <-- Advertise
Batch *-- InboundOrder 
Buyer <-- SellOrder
InboundOrder o-- Seller
InboundOrder o-- Representative
InboundOrder o-- Section
OrderItem *-- SellOrder
Section *-- Warehouse
Transporter ..> Product
class Advertise {
        +Long id
        +String description
        +Product product
        +Seller seller
        +BigDecimal price
        +AdvertiseStatus status
        +boolean freeShipping
}
class Batch {
    +Long id
+int initialQuantity
+int currentQuantity
+float currentTemperature
+float minTemperature
+LocalDate dueDate
+LocalDate manufacturingDate
+LocalDateTime manufacturingTime
+Advertise advertise
+InboundOrder inboundOrder
}
class Buyer {
    *String address
}
class InboundOrder {
    +Long id
+LocalDate orderDate
+Seller seller
+Representative representative
+List<Batch> batchList
+Section section
}
class OrderItem {
    +Long id;
+int quantity
+Advertise advertise
+SellOrder sellOrder
}
class Product {
    +Long id
+String name
+String description
+float minTemperature
+float maxTemperature
+RefrigerationType categoryRefrigeration
}
class Representative {
    +RepresentativeJob job
}
class Section {
    +Long id
+Warehouse warehouse
+String name
+RefrigerationType refrigerationType
+int currentStock
+int stockLimit
+float minTeperature
+float maxTeperature
+List<InboundOrder> inboundOrderList
}
class Seller {+List<Advertise> advertiseList}
class SellOrder {+Long id
+Buyer buyer
+boolean cart
+List<OrderItem> orderItemList
+BigDecimal shippingRate
+BigDecimal totalValue
}
class Transporter {
+Long id
+String name
+List<Product> freshList
+List<Product> coldList
+List<Product> frozenList}
class User {
+Long id
+String name
+String email
+String password
+Set<Integer> profiles}
class Warehouse {
    +Long id 
    +String nome
    +String location 
}
```