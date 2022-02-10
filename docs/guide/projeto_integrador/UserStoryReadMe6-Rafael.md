# Programação Java# Programação Java
## Requisito 6
### // Especificações de Requisitos

#### Requerimientos US:
#### ml-crud-warhouse-06

**Importante:**
As histórias de usuários são narradas do ponto de vista do comprador com base em
suas necessidades. Os serviços são expostos a partir do Marketplace para serem
consumidos pelo comprador que os solicita. Os contratos referem-se à História do Usuário.

## Controle de Armazéns
### User Story


|                                      User Story Code: ml-crud-warhouse-06                                       | Horas estimadas |
|:----------------------------------------------------------------------------------------------------------------------:|:---------------:|
|                             **User Story Name: Controle de Armazéns**                              |                 |
| **COMO** representante **QUERO** buscar dados dos armazéns **PARA** controle dos produtos que neles
estão e passar a informação para o vendedor informar em seu anuncio de venda||

| **CENÁRIO 1:** O armazém é inserido.                  |
|:-----------------------------------------------------------------------|
| **DESDE** o representante tenha autorização                     |
| **E** que o armazém esteja cadastrado                                |
| **E** que o setor esteja cadastrado                                        |
| **E** que o produto esteja cadastrado |
| **QUANDO** o representante pega a informação do armazém |
| **ENTÃO** ele passa a informação para o vendedor colocar no anuncio                |
| **E** verifica se os produtos estão atualizados                        |
|**E** verifica se os produtos estão atualizados


| VALIDAÇÃO                                             |
|-------------------------------------------------------|
| * Autentique-se como representante e acesse os terminais. | 
| * Consulta o armazém                                   |
| * Verifica o a localidade do armazém e quais produtos estão nele.    |

> Observação:
O representante consegue adicionar, atualizar, verificar e deletar um armazém.

##### Representação JSON:
<details><summary>Request</summary><p>

```JSON
{
  "name": "Consulta 1 Armazem",
  "id": "ca189c01-b596-4956-9f6a-73b76683ed65",
  "request": {
    "method": "GET",
    "header": [],
    "url": null
  },
  "response": []
}
```

### Contratos relativos a User Story
| HTTP | Modelo de URI                        | Descrição   |                     | US-code |
|------|-----------------------------------------------------------|----------------------------------------------------
| GET  | /api/v1/fresh-products/warehouse | Procura uma lista de Armazéns.| ml-crud- warhouse-06|
| POST | /api/v1/fresh-products/warehouse| Cadastra um Armazém.           | ml-crud- warhouse-06 |                                                                                                                                                                                                                                                 
| PUT | /api/v1/fresh-products/warehouse/{{id}} | Atualiza um Armazém pelo Id| ml-crud- warhouse-06 |
| DELETE | api/v1/fresh-products/warehouse/{{id}}| Deleta um Armazém pelo Id | ml-crud- warhouse-06 |

