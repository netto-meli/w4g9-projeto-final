# MERCADO LIVRE - FRESCOS
## Projeto Final
### Objetivo
O objetivo deste projeto final é implementar uma API REST no âmbito do slogan e aplicar
os conteúdos trabalhados durante o BOOTCAMP MELI. (Git, Java, Spring, Armazenamento,
Qualidade e Segurança).

_Kickoff do projeto:_ ***26/01/2022***

#### Enunciado
O Mercado Livre é a empresa líder em comércio eletrônico da LATAM, com operações
em 18 países. A MELI (como é conhecida por sua sigla na Bolsa de Valores de NY) quer
expandir seus negócios para incluir em sua listagem (oferta) produtos FRESCOS. Hoje a MELI
já vende produtos alimentícios, mas quer se aventurar a poder vender produtos que precisam
de refrigeração; chamados produtos frescos. Implica novos desafios na forma de armazenar,
transportar e comercializar os produtos, uma vez que é feito de uma forma totalmente
diferente. O modelo de negócio atualmente implementado em relação à forma como
tratamos os nossos produtos (armazenamento, transporte e comercialização) é incompatível
com os produtos frescos da indústria alimentar, portanto temos o desafio de cumprir estes
novos requisitos de forma a atender às necessidades deste novo mercado.

Todos os produtos, para serem armazenáveis, transportáveis e comercializáveis,
devem ter informações comuns como data de validade e número do lote, e cada tipo de
produto também possui algumas informações específicas.

A empresa gere os envios por diversos meios, podendo um envio conter um
determinado número de produtos frescos, refrigerados ou congelados, de forma a não
ultrapassar a capacidade do transportador e também chegar em óptimas condições ao
cliente final.

A experiência que a MELI quer proporcionar é a seguinte: Um usuário (comprador,
neste caso) deve poder listar os produtos na página do Marketplace (site de compra / venda
do Mercado Livre). Quando você estiver interessado em um produto da lista, pode inserir o
VIP do produto (Página de Visualização do Item), ver os detalhes dos produtos e
opcionalmente selecionar a quantidade desejada e adicioná-los ao seu carrinho de compras.
Obviamente, o comprador deve poder continuar adicionando produtos ao carrinho e, quando
desejar, proceder ao checkout para adiantar a compra. A compra de um usuário comprador
pode ter incluído produtos de diferentes tipos, características e, especificamente falando de
frescos, esses produtos podem precisar ser manuseados e mantidos de diferentes maneiras
(diferentes temperaturas, etc.) de forma que a forma de agrupá-los para seu envio está
ajustado às necessidades desses produtos e que não cheguem ao destino em más
condições.

Por outro lado, quando os Vendedores (supermercados, empresas do setor
alimentício, atacadistas, etc.) nos enviam a mercadoria para armazená-la em nosso depósito,
precisamos acondicioná-la no local correto para que as exigências deste produto sejam
atendidas. O armazém de abastecimento (armazém MELI) terá diferentes zonas frigoríficas a
diferentes temperaturas e com diferentes características, sendo muito importante guardar o
stock desses produtos em local adequado para que se mantenham em bom estado.

A MELI não possui um único armazém de fullfilment, mas sim distribui os seus vários
armazéns em diferentes áreas geográficas para facilitar uma distribuição mais rápida e
eficiente, para que os produtos possam estar em diferentes armazéns. Para simplificar o
problema, uma venda será sempre para produtos que saem do mesmo depósito (embora na
realidade nem sempre seja esse o caso e você tenha que transferir o estoque de um lugar
para outro e depois montar o sellOrder).

Na medida em que ocorrem as vendas no Marketplace de produtos que temos em
estoque no Fulfillment, um colaborador do almoxarifado deve procurar os produtos que
compõem o sellOrder de compra e aproximá-los de um setor onde a embalagem é montada
para transporte. Este processo de coleta dos produtos de onde eles são armazenados é
chamado de processo de coleta. Uma vez que os produtos são montados no setor onde a
embalagem ou embalagens são montadas, passamos ao que chamamos de processo de
embalagem, que nada mais é do que decidir quantas embalagens e como esses produtos
serão distribuídos. Para os frescos surgem novas exigências uma vez que é provável que os
produtos não sejam enviados em caixas mas sim em sacos ou gavetas refrigeradas de
acordo com as necessidades comuns dos produtos que integram a encomenda.

Uma vez embalados os produtos, os pacotes são despachados com o transporte para
que sigam no trajeto de distribuição.

---
Seu desafio é nos ajudar a continuar democratizando o comércio eletrônico e transformando
a LATAM, criando os artefatos necessários para permitir as seguintes funcionalidades:

* Ser capaz de inserir um lote de produtos no armazém de distribuição para registrar
  essa existência no estoque.
* Ter as informações necessárias para entender em que setor deve ser armazenada a
  mercadoria para que fique em bom estado enquanto estiver no almoxarifado e para
  que se possa mostrar ao colaborador que vai procurar o produto (picking) onde está .
* Ser capaz de detectar se há produtos que estão prestes a expirar para tomar alguma
  medida a esse respeito (pode ser devolvê-los ao Vendedor, jogá-los fora ou realizar
  alguma ação comercial específica para liquidá-los).
* Para poder consultar o estoque, listar quais produtos estão em qual armazém e dado
  um produto específico, entender também em qual armazém ele está armazenado.
* Poder cadastrar o sellOrder de compra para que os colaboradores dentro do Fullfilment
  possam montar o (s) sellOrder (s) para despachá-los.

### Diretrizes para planejamento e dinâmica de trabalho
Para a realização do projeto propomos trabalhar com uma Metodologia de Trabalho
(SCRUM) aplicada, ao longo deste processo, de forma a adotar um conjunto de boas
práticas para trabalhar em equipe, de forma colaborativa e obter o melhor resultado
possível dos projetos em desenvolvimento.

> Daily - Planning

No início de cada dia, o Product Owner define os requisitos liberados. Nesse
momento, as tarefas são revisadas, as consultas são feitas e os requisitos são
estimados.

Nos primeiros dias são liberados os requisitos que serão desenvolvidos em grupo e
nos últimos dias será liberado um requisito para ser desenvolvido individualmente.
Os grupos de trabalho são organizados na plataforma Zoom de forma a compartilhar
com a equipe o trabalho que você fez ontem, o que vai fazer hoje, quais os
impedimentos que teve e/ou acredita que irão se manter, para verificar o andamento
dos requisitos e ouvir os outros colegas levando em consideração o que objetivo é,
os obstáculos, as soluções possíveis e como posso ajudar.

Por sua vez, a equipe deve estimar o tempo de desenvolvimento. Os requisitos serão
representados em User Stories e a estimativa deve ser em horas. A estimativa é uma
tarefa paralela ao desenvolvimento, portanto pode ser desenvolvida simultaneamente
entre os diferentes requisitos.

Finalmente, eles devem preencher individualmente os aspectos relacionados à
estimativa e outras questões que pareçam pertinentes a você em relação às tarefas
do seguinte [quiz][quiz].

> Daily Check

No final do dia é hora de verificar o que você fez, se há algo para destacar ou revisar.
Também pedimos que você registre se as estimativas que você previu estão corretas
e se você precisa de algum suporte em particular.

Neste momento, pediremos que você preencha o [quiz][quiz] a seguir novamente para
registrar esses aspectos.

#### Documentação de entregas parciais
1. Execute o commit inicial "Início do Requisito XX", para saber quando o
   requisito começou
2. Execute o commit final "final do Requisito XX", indicando que um requisito foi
   concluído, notificando no board card do seu projeto.
3. No final do requisito, crie uma versão. Por exemplo. Versão 0.0.1

### Entrega final do projeto
A data de entrega e fechamento é até às **9h** do dia **11/01/2022**. Espera-se que as
seguintes produções sejam entregues:

* A entrega deve ser feita via GitHub. Para entregar o projeto, crie um projeto de
  teste no Fury com seu [nome-sobrenome] no ramo "Projeto final". Em seguida,
  execute um push de sua pasta de projeto local para sua pasta no repositório
  GitHub.
* Projeto Git com script de carregamento (projeto individual url)
* Projeto implantado no Fury com banco de dados (url de produção individual)
* Documentação (uma pasta de documentos dentro do repositório git)

### Modalidade de Avaliação
Para a revisão do projeto final, a revisão por pares é proposta em primeira instância.
No dia do encerramento, cada grupo receberá, aleatoriamente, o projeto de outra
equipe para revisar e fornecer o feedback correspondente em um formulário. Por sua
vez, você terá um espaço para realizar individualmente uma avaliação do requisito 6.

Consideramos que o processo de revisão por pares é positivo, pois cada colega aloca
tempo para a revisão de outro projeto, verifica a compilação, o funcionamento e o
código de cada requisito e pode contribuir para a sua melhoria, aprender novas
funcionalidades, reconhecer e validar boas práticas.

---
A revisão por pares irá ocorrer na **tarde** do dia **11/01/2022**.

A apresentação e o encerramento irão ocorrer na **tarde** do dia **11/01/2022**.

[quiz]:https://docs.google.com/forms/d/e/1FAIpQLSdGWccmuJYbr38p5k5iXs6Jqd6szjVDSh2GnrYUh4GuLX_-dw/viewform