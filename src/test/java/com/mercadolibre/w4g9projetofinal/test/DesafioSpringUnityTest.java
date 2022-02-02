    package com.mercadolibre.w4g9projetofinal.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DesafioSpringUnityTest {

    /*
    Uma lista de todos os produtos disponíveis.
    4 produtos heterogêneos devem ser mokados no teste e então simular uma chamada
    para o controlador correspondente que retorna o array com todos eles. No final, compare
    o resultado com a lista simulada.
     *
    @Test
    public void verificaListaDeProdutos() throws Exception {
        //arrange
        Categoria categoriaFerramentas = new Categoria(1L,"Ferramentas");
        Categoria categoriaOutros = new Categoria(2L,"Outrtos");
        Produto p1 = new Produto(1L,"1",categoriaOutros,"marca",new BigDecimal(10),true,5,10);
        Produto p2 = new Produto(2L,"2",categoriaFerramentas,"marca",new BigDecimal(10),true,5,10);
        Produto p3 = new Produto(3L,"3",categoriaFerramentas,"marca",new BigDecimal(10),true,5,10);
        Produto p4 = new Produto(4L,"4",categoriaOutros,"marca",new BigDecimal(10),true,5,10);
        List<Produto> listaProds = new ArrayList<>(Arrays.asList(p1, p2, p3, p4));

        EstoqueRepository mockStokRepo = Mockito.mock(EstoqueRepository.class);
        Mockito.when(mockStokRepo.listagem()).thenReturn(listaProds);

        ProdutoService prodServ = new ProdutoService(mockStokRepo);

        //act
        List<Produto> listaProdsMetodo = prodServ.listaProduto();

        //assertion
        assertEquals(listaProds, listaProdsMetodo);
    }

    /*
    Uma lista de produtos filtrados por categoria.
4 produtos heterogêneos devem ser mokados, sendo que dois pertencem à categoria
"Ferramentas". Em seguida, simule uma chamada para o controlador correspondente
indicando "Ferramentas" como a categoria de filtragem. Monte uma lista que contenha os
produtos da categoria correta e verifique se os devolvidos pelo controlador são
exatamente os mesmos.
     *
    @Test
    public void verificaListaDeProdutosPorCategoria() throws Exception {
        //arrange
        Long idCategoriaFerramentas = 1L;
        Categoria categoriaFerramentas = new Categoria(idCategoriaFerramentas,"Ferramentas");
        Categoria categoriaOutros = new Categoria(2L,"Outrtos");
        Produto p1 = new Produto(1L,"1",categoriaOutros,"marca",new BigDecimal(10),true,5,10);
        Produto p2 = new Produto(2L,"2",categoriaFerramentas,"marca",new BigDecimal(10),true,5,10);
        Produto p3 = new Produto(3L,"3",categoriaFerramentas,"marca",new BigDecimal(10),true,5,10);
        Produto p4 = new Produto(4L,"4",categoriaOutros,"marca",new BigDecimal(10),true,5,10);
        List<Produto> listaProds = new ArrayList<>(Arrays.asList(p1, p2, p3, p4));
        List<Produto> listaProdsCategoria = new ArrayList<>(Arrays.asList(p2, p3));

        EstoqueRepository mockStokRepo = Mockito.mock(EstoqueRepository.class);
        Mockito.when(mockStokRepo.listagem()).thenReturn(listaProds);

        ProdutoService prodServ = new ProdutoService(mockStokRepo);

        //act
        List<Produto> listaProdsMetodo = prodServ.listaProdutoCategoria(idCategoriaFerramentas);

        //assertion
        assertEquals(listaProdsCategoria, listaProdsMetodo);
    }

    /*
    Alfabético (crescente e decrescente)
4 produtos heterogêneos que estão em uma lista desordenada alfabeticamente devem
ser mokados. Em seguida, simule uma chamada para o controlador correspondente,
indicando que ele os ordena. Em seguida, crie uma lista que contenha os mesmos
produtos, mas solicitados, e verifique se os retornados pelo controlador são exatamente
os mesmos. Neste caso, devem ser realizados dois testes, um em ordem crescente e
outro em ordem decrescente.
     *
    @Test
    public void verificaListaDeProdutosPorOrdemAlfabeticaCrescenteDecrescente() throws Exception {
        //arrange
        Long idCategoriaFerramentas = 1L;
        Categoria categoriaFerramentas = new Categoria(idCategoriaFerramentas,"Ferramentas");
        Categoria categoriaOutros = new Categoria(2L,"Outrtos");
        Produto p1 = new Produto(1L,"G",categoriaOutros,"marca",new BigDecimal(10),true,5,10);
        Produto p2 = new Produto(2L,"Y",categoriaFerramentas,"marca",new BigDecimal(10),true,5,10);
        Produto p3 = new Produto(3L,"B",categoriaFerramentas,"marca",new BigDecimal(10),true,5,10);
        Produto p4 = new Produto(4L,"A",categoriaOutros,"marca",new BigDecimal(10),true,5,10);
        List<Produto> listaProds = new ArrayList<>(Arrays.asList(p1, p2, p3, p4));
        List<Produto> listaProdsOrdemCrescente = new ArrayList<>(Arrays.asList(p4,p3,p1,p2));
        List<Produto> listaProdsOrdemDecrescente = new ArrayList<>(Arrays.asList(p2,p1,p3,p4));

        EstoqueRepository mockStokRepo = Mockito.mock(EstoqueRepository.class);
        Mockito.when(mockStokRepo.listagem()).thenReturn(listaProds);

        ProdutoService prodServ = new ProdutoService(mockStokRepo);

        //act
        List<Produto> listaProdsMetodoDecre = prodServ.listaProdutoOrdenado(0);
        List<Produto> listaProdsMetodoCres = prodServ.listaProdutoOrdenado(1);

        //assertion
        assertEquals(listaProdsOrdemDecrescente, listaProdsMetodoDecre);
        assertEquals(listaProdsOrdemCrescente, listaProdsMetodoCres);
    }

    /*
    Preço mais alto
4 produtos heterogêneos que estão em uma lista não ordenada por preço devem ser
mokados. Em seguida, simule uma chamada para o controlador correspondente
indicando que eles são solicitados por um preço mais alto. Em seguida, crie uma lista que
contenha os mesmos produtos, mas solicitados, e verifique se os retornados pelo
controlador são exatamente os mesmos.
     *
    @Test
    public void verificaListaDeProdutosPorOrdemPrecoMaisAlto() throws Exception {
        //arrange
        Long idCategoriaFerramentas = 1L;
        Categoria categoriaFerramentas = new Categoria(idCategoriaFerramentas,"Ferramentas");
        Categoria categoriaOutros = new Categoria(2L,"Outrtos");
        Produto p1 = new Produto(1L,"G",categoriaOutros,"marca",new BigDecimal(5),true,5,10);
        Produto p2 = new Produto(2L,"Y",categoriaFerramentas,"marca",new BigDecimal(10),true,5,10);
        Produto p3 = new Produto(3L,"B",categoriaFerramentas,"marca",new BigDecimal(8),true,5,10);
        Produto p4 = new Produto(4L,"A",categoriaOutros,"marca",new BigDecimal(1),true,5,10);
        List<Produto> listaProds = new ArrayList<>(Arrays.asList(p1, p2, p3, p4));
        List<Produto> listaProdsOrdemPrecoMaisAlto = new ArrayList<>(Arrays.asList(p2, p3, p1, p4));

        EstoqueRepository mockStokRepo = Mockito.mock(EstoqueRepository.class);
        Mockito.when(mockStokRepo.listagem()).thenReturn(listaProds);

        ProdutoService prodServ = new ProdutoService(mockStokRepo);

        //act
        List<Produto> listaProdsMetodoPrecoMaisAlto= prodServ.listaProdutoOrdenado(3);

        //assertion
        assertEquals(listaProdsOrdemPrecoMaisAlto, listaProdsMetodoPrecoMaisAlto);
    }

    /*
    Menor preço
4 produtos heterogêneos que estão em uma lista não ordenada por preço devem ser
mokados. Em seguida, simule uma chamada para o controlador correspondente,
indicando que eles são ordenados pelo preço mais baixo. Em seguida, crie uma lista que
contenha os mesmos produtos, mas solicitados, e verifique se os retornados pelo
controlador são exatamente os mesmos.
     *
    @Test
    public void verificaListaDeProdutosPorOrdemPrecoMaisBaixo() throws Exception {
        //arrange
        Long idCategoriaFerramentas = 1L;
        Categoria categoriaFerramentas = new Categoria(idCategoriaFerramentas,"Ferramentas");
        Categoria categoriaOutros = new Categoria(2L,"Outrtos");
        Produto p1 = new Produto(1L,"G",categoriaOutros,"marca",new BigDecimal(5),true,5,10);
        Produto p2 = new Produto(2L,"Y",categoriaFerramentas,"marca",new BigDecimal(10),true,5,10);
        Produto p3 = new Produto(3L,"B",categoriaFerramentas,"marca",new BigDecimal(8),true,5,10);
        Produto p4 = new Produto(4L,"A",categoriaOutros,"marca",new BigDecimal(1),true,5,10);
        List<Produto> listaProds = new ArrayList<>(Arrays.asList(p1, p2, p3, p4));
        List<Produto> listaProdsOrdemPrecoMaisBaixo = new ArrayList<>(Arrays.asList(p4, p1, p3, p2));

        EstoqueRepository mockStokRepo = Mockito.mock(EstoqueRepository.class);
        Mockito.when(mockStokRepo.listagem()).thenReturn(listaProds);

        ProdutoService prodServ = new ProdutoService(mockStokRepo);

        //act
        List<Produto> listaProdsMetodoPrecoMaisBaixo= prodServ.listaProdutoOrdenado(2);

        //assertion
        assertEquals(listaProdsOrdemPrecoMaisBaixo, listaProdsMetodoPrecoMaisBaixo);
    }

    /*
    Ao mesmo tempo, é necessária uma API que forneça:
Possibilidade de envio de pedido de compra. A partir disso, o preço total da
solicitação feita pode ser recebido como reembolso.
● Leve em consideração, para cada uma dessas solicitações, os possíveis
"códigos de status" que podem ser retornados.
● Por exemplo:
○ Se um produto que não existe for solicitado, retorne o código de
status correspondente.
○ Se houver um problema com o servidor e a conexão não puder ser
feita, o código de status correspondente deve ser retornado.
4 produtos heterogêneos que estão em uma lista devem ser mokados. Em seguida,
simule uma chamada para o controlador correspondente indicando a compra de dois
deles. Para este caso realizaremos dois testes, um onde os dois produtos adquiridos
existem e é verificado que o preço devolvido está correto, e outro onde um dos produtos
não existe e é verificado que a resposta é a esperada para este caso. Ao executar os
testes, devo também verificar se o código de status que é retornado em cada caso está
correto.
     *
    @Test
    public void verificaFluxoDeCompra() throws Exception {
        //arrange
        Long idCategoriaFerramentas = 1L;
        String idCliente = "1";
        Cliente cliente = new Cliente(1L,"Joao","endereco","sP","123456678" );
        Categoria categoriaFerramentas = new Categoria(idCategoriaFerramentas,"Ferramentas");
        Categoria categoriaOutros = new Categoria(2L,"Outrtos");
        Produto p1 = new Produto(1L,"G",categoriaOutros,"marca",new BigDecimal(5),true,5,10);
        Produto p2 = new Produto(2L,"Y",categoriaFerramentas,"marca",new BigDecimal(10),true,5,10);
        Produto p3 = new Produto(3L,"B",categoriaFerramentas,"marca",new BigDecimal(8),true,5,10);
        Produto p4 = new Produto(4L,"A",categoriaOutros,"marca",new BigDecimal(1),true,5,10);
        List<ItemCarrinho> listaItensCarrrinho = new ArrayList<>();
        Pedido pedido = new Pedido(null, 1L, listaItensCarrrinho, BigDecimal.ZERO, BigDecimal.ZERO);

        ClienteRepository mockCliRepo = Mockito.mock(ClienteRepository.class);
        EstoqueRepository mockStockRepo = Mockito.mock(EstoqueRepository.class);
        PedidoRepository mockPedidRepo = Mockito.mock(PedidoRepository.class);
        Mockito.when(mockPedidRepo.getCarrinho(1L)).thenReturn(pedido);
        Mockito.when(mockStockRepo.get(1L)).thenReturn(p1);
        Mockito.when(mockStockRepo.get(2L)).thenReturn(p2);
        Mockito.when(mockStockRepo.get(3L)).thenThrow(NullPointerException.class);
        Mockito.when(mockCliRepo.get(1L)).thenReturn(cliente);
        Mockito.when(
                mockPedidRepo.adicionarProdutoNoCarrinho(isA(Long.class), isA(String.class),isA(ItemCarrinho.class)))
                .thenReturn(pedido);

        CarrinhoService cartServ = new CarrinhoService(mockCliRepo,mockStockRepo,mockPedidRepo);

        //act
        Pedido pd1 = cartServ.adicionarProdutosNoCarrinho(idCliente,"1","2");
        Pedido pd2 = cartServ.adicionarProdutosNoCarrinho(idCliente,"2","2");

        NullPointerException excecaoEsperada = assertThrows(
                NullPointerException.class,
                () -> cartServ.adicionarProdutosNoCarrinho(idCliente,"3","2") //act
        );

        //assertion
        assertEquals(pd1,pedido);
        assertEquals(pd2,pedido);
        assertTrue(excecaoEsperada.getMessage() == null);
    }*/
}