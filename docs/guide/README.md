# Bootcamp-Meli-W4-G9-Projeto-Integrador
Projeto Integrador - Grupo 9 - Wave 4 - Bootcamp Meli
## ATIVIDADE
Uma plataforma de vendas de produtos online deseja melhorar as opções de pesquisa e filtragem dos seus produtos. Para isso, decidiu implementar um motor de busca que, a partir das opções que o usuário determina, devolve o(s) produto(s) que lhes corresponde. Obs.: Os produtos devem ser cadastrados a partir de um payload e armazenados num arquivo Json.

Para fazer isso, será necessário desenvolver uma API que forneça:
1. Cadastrar uma lista de produtos.
2. Uma lista de todos os produtos disponíveis.
3. Uma lista de produtos filtrados por categoria.
4. Uma lista que permite a combinação de qualquer um dos filtros.
```
Exemplo: categoria + frete grátis.
```

Por outro lado, visto que se pretende uma boa experiência do usuário no que diz respeito à forma de apresentação dos produtos, será necessário que os resultados fornecidos pela API possam ser ordenados por qualquer um dos seguintes critérios:
+ Alfabético (crescente e decrescente)
+ Preço mais alto
+ Menor preço

Ao mesmo tempo, é necessária uma API que forneça:

5. Possibilidade de envio de pedido de compra. A partir disso, o preço total da requisição feita pode ser recebido como resposta.
+ Considere, para cada uma dessas solicitações, os possíveis "status-code" que podem ser retornados.
```
+ Se um produto que não existe for solicitado, retorne o código de status correspondente.
+ Se houver um problema com o servidor e a conexão não puder ser feita, 
  o código de status correspondente deve ser retornado.
```

## EXTRA
A plataforma afirmou que no futuro gostaria de conseguir realizar o desenvolvimento dos seguintes requisitos como uma melhoria:

6. Para cada solicitação de compra é necessário realizar o controle de estoque disponível.
```
Se forem solicitadas 4 unidades de um produto e houver apenas duas, 
coloque as restrições e avisos correspondentes.
```
7. Permite a utilização de um “carrinho de compras” onde para cada pedido de compra existe um valor total acumulado e devolvido ao usuário.
```
Se um produto de R$ 900 foi enviado em um pedido de compra
e outro produto de R$ 300 foi enviado em outro, 
devo receber a soma dos dois (R$ 1200) como resposta.
```
Ao mesmo tempo, sugere o desenvolvimento de uma nova API que permita o seguinte:

8. Cadastre novos clientes. Para isso, devem ser realizados os controles necessários, por exemplo: cliente existente, cliente com dados incompletos, etc.
9. Obter uma lista completa de todos os clientes.
10. Obter uma lista de todos os clientes filtrados por Estado.

Além disso, a plataforma afirmou que concorda em receber sugestões de melhorias dos desenvolvedores da solução, portanto os convidamos a incluir todas as melhorias que considerem que possam impulsionar o projeto desenvolvido.

