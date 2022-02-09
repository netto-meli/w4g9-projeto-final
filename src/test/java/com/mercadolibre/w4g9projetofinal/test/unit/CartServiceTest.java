package com.mercadolibre.w4g9projetofinal.test.unit;

import com.mercadolibre.w4g9projetofinal.entity.Advertise;
import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.Buyer;
import com.mercadolibre.w4g9projetofinal.entity.OrderItem;
import com.mercadolibre.w4g9projetofinal.entity.SellOrder;
import com.mercadolibre.w4g9projetofinal.entity.enums.AdvertiseStatus;
import com.mercadolibre.w4g9projetofinal.entity.enums.SellOrderStatus;
import com.mercadolibre.w4g9projetofinal.exceptions.CartManagementException;
import com.mercadolibre.w4g9projetofinal.repository.OrderItemRepository;
import com.mercadolibre.w4g9projetofinal.repository.SellOrderRepository;
import com.mercadolibre.w4g9projetofinal.service.AdvertiseService;
import com.mercadolibre.w4g9projetofinal.service.BatchService;
import com.mercadolibre.w4g9projetofinal.service.BuyerService;
import com.mercadolibre.w4g9projetofinal.service.CartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartServiceTest {

    @Test
    public void verificaExibicaoDoCarrinhoAtualDoClienteQuandoNaoExisteCarrinhoAberto() {
        // <-- ARRANGE -->
        // Comprador
        Long idBuyer = 1L;
        Buyer buyer = new Buyer(idBuyer, null, null, null, null, null);
        // Carrinho zerado
        SellOrder carrinhoVazio = new SellOrder(1L, buyer, SellOrderStatus.CART,
                new ArrayList<>(), BigDecimal.ZERO, BigDecimal.ZERO);
        // Mock - Class
        OrderItemRepository orderItemRepository = Mockito.mock(OrderItemRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        BuyerService buyerService = Mockito.mock(BuyerService.class);
        BatchService batchServce = Mockito.mock(BatchService.class);
        AdvertiseService advertiseService = Mockito.mock(AdvertiseService.class);
        // Mock - Actions
        Mockito.when(sellOrderRepository
                .findSellOrderByBuyer_IdAndOrderStatus(idBuyer, SellOrderStatus.CART)).thenReturn(Optional.empty());
        Mockito.when(buyerService.findById(idBuyer)).thenReturn(buyer);
        Mockito.when(sellOrderRepository.save(Mockito.any(SellOrder.class))).thenReturn(carrinhoVazio);
        // Service
        CartService cs = new CartService(orderItemRepository, sellOrderRepository,
                buyerService, batchServce, advertiseService);

        // <-- ACT -->
        SellOrder so = cs.getCart(idBuyer);

        // <-- ASSERTION -->
        Assertions.assertEquals(carrinhoVazio, so);
    }

    @Test
    public void verificaExibicaoDoCarrinhoatualDoClienteQuandoExisteCarrinhoAberto() {
        // <-- ARRANGE -->
        // Comprador
        Long idBuyer = 1L;
        Buyer buyer = new Buyer(idBuyer, null, null, null, null, null);
        // Carrinho com itens
        SellOrder carrinhoExistente = new SellOrder(1L, buyer, SellOrderStatus.CART,
                new ArrayList<>(), BigDecimal.ZERO, BigDecimal.ZERO);
        // Mock - Class
        OrderItemRepository orderItemRepository = Mockito.mock(OrderItemRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        BuyerService buyerService = Mockito.mock(BuyerService.class);
        BatchService batchServce = Mockito.mock(BatchService.class);
        AdvertiseService advertiseService = Mockito.mock(AdvertiseService.class);
        // Mock - Action
        Mockito.when(sellOrderRepository
                .findSellOrderByBuyer_IdAndOrderStatus(idBuyer, SellOrderStatus.CART))
                .thenReturn(Optional.of(carrinhoExistente));
        // Service
        CartService cs = new CartService(orderItemRepository, sellOrderRepository,
                buyerService, batchServce, advertiseService);

        // <-- ACT -->
        SellOrder so = cs.getCart(idBuyer);

        // <-- ASSERTION -->
        Assertions.assertEquals(carrinhoExistente, so);
    }

    @Test
    public void verificaInclusaoDeZeroItensDoAnuncioNoCarrinhoAtual() {
        // <-- ARRANGE -->
        // Comprador
        Long idBuyer = 1L;
        Long idAdvertise = 1L;
        // Mocks
        OrderItemRepository orderItemRepository = Mockito.mock(OrderItemRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        BuyerService buyerService = Mockito.mock(BuyerService.class);
        BatchService batchServce = Mockito.mock(BatchService.class);
        AdvertiseService advertiseService = Mockito.mock(AdvertiseService.class);
        // Service
        CartService cs = new CartService(orderItemRepository,sellOrderRepository,
                buyerService, batchServce, advertiseService);

        // <-- ACT -->
        CartManagementException excecaoEsperada = Assertions.assertThrows(
                CartManagementException.class,
                () -> cs.addAdvertiseItemsToCart(idBuyer, idAdvertise, 0) //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage().contains("Impossível incluir ZERO produtos no carrinho"));
    }

    @Test
    public void verificaInclusaoDeMaisItensDoAnuncioNoCarrinhoAtual() {
        // <-- ARRANGE -->
        // Comprador
        Long idBuyer = 1L;
        Long idAdvertise = 1L;
        Long idOrderItem = 1L;
        Buyer buyer = new Buyer(idBuyer, null, null, null, null, null);
        Advertise advertise = new Advertise(idAdvertise, null, null,
                null, BigDecimal.ONE, null, true);
        SellOrder carrinhoExistente = new SellOrder(1L, buyer, SellOrderStatus.CART,
                new ArrayList<>(), BigDecimal.ZERO, BigDecimal.ZERO);
        OrderItem itemdoPedido = new OrderItem(idOrderItem,1,advertise,carrinhoExistente);
        List<OrderItem> listaDeItensDoPedido = new ArrayList<>();
        listaDeItensDoPedido.add(itemdoPedido);
        carrinhoExistente.setOrderItemList(listaDeItensDoPedido);
        // Carrinho atualizado
        SellOrder novoCarrinho = new SellOrder(1L, buyer, SellOrderStatus.CART,
                listaDeItensDoPedido, BigDecimal.ZERO, BigDecimal.ZERO);
        // Lote verificado no estoque
        Batch lote = new Batch();
        // Mocks - Class
        OrderItemRepository orderItemRepository = Mockito.mock(OrderItemRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        BuyerService buyerService = Mockito.mock(BuyerService.class);
        BatchService batchServce = Mockito.mock(BatchService.class);
        AdvertiseService advertiseService = Mockito.mock(AdvertiseService.class);
        // Mock - Actions
        Mockito.when(advertiseService.findById(idAdvertise)).thenReturn(advertise);
        Mockito.when(batchServce.verifyStock(Mockito.eq(idAdvertise), Mockito.anyInt())).thenReturn(lote);
        Mockito.when(sellOrderRepository.save(Mockito.any(SellOrder.class))).thenReturn(novoCarrinho);
        // Service + Spy
        CartService cs = new CartService(orderItemRepository, sellOrderRepository,
                buyerService, batchServce, advertiseService);
        CartService csSpy = Mockito.spy(cs);
        Mockito.doReturn(carrinhoExistente).when(csSpy).getCart(idBuyer);

        // <-- ACT -->
        SellOrder carrinhoAtualizado = csSpy.addAdvertiseItemsToCart(idBuyer, idAdvertise, 1);

        // <-- ASSERTION -->
        Assertions.assertEquals(novoCarrinho,carrinhoAtualizado);
    }

    @Test
    public void verificaRemocaoDeItensDeUmAnuncioQueNemEstaoNoCarrinho(){
        // <-- ARRANGE -->
        // Comprador
        Long idBuyer = 1L;
        Long idAdvertise = 1L;
        Buyer buyer = new Buyer(idBuyer, null, null, null, null, null);
        Advertise advertise = new Advertise(idAdvertise, null, null,
                null, null, null, true);
        // Carrinho com itens
        SellOrder cart = new SellOrder(1L, buyer, SellOrderStatus.CART,
                new ArrayList<>(), BigDecimal.ZERO, BigDecimal.ZERO);
        // Mocks - Class
        OrderItemRepository orderItemRepository = Mockito.mock(OrderItemRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        BuyerService buyerService = Mockito.mock(BuyerService.class);
        BatchService batchServce = Mockito.mock(BatchService.class);
        AdvertiseService advertiseService = Mockito.mock(AdvertiseService.class);
        // Mock - Actions
        Mockito.when(advertiseService.findById(idAdvertise)).thenReturn(advertise);
        // Service + Spy
        CartService cs = new CartService(orderItemRepository, sellOrderRepository,
                buyerService, batchServce, advertiseService);
        CartService csSpy = Mockito.spy(cs);
        Mockito.doReturn(cart).when(csSpy).getCart(idBuyer);

        // <-- ACT -->
        CartManagementException excecaoEsperada = Assertions.assertThrows(
                CartManagementException.class,
                () -> csSpy.removeAdvertiseItemsFromCart(idBuyer, idAdvertise, 0) //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage()
                .contains("Impossivel retirar produto que não foi adicionado"));
    }

    @Test
    public void verificaRemocaoDeMaisItensDeUmAnuncioDoQueOsQueEstaoNoCarrinho(){
        // <-- ARRANGE -->
        // Comprador
        Long idBuyer = 1L;
        Long idAdvertise = 1L;
        Long idOrderItem = 1L;
        Buyer buyer = new Buyer(idBuyer, null, null, null, null, null);
        Advertise advertise = new Advertise(idAdvertise, null, null,
                null, null, null, true);
        // Carrinho com itens
        SellOrder cart = new SellOrder(1L, buyer, SellOrderStatus.CART,
                null, BigDecimal.ZERO, BigDecimal.ZERO);
        OrderItem itemDoPedido = new OrderItem(idOrderItem,1,advertise,cart);
        List<OrderItem> listaDeItensDoPedido = new ArrayList<>();
        listaDeItensDoPedido.add(itemDoPedido);
        cart.setOrderItemList(listaDeItensDoPedido);
        // Mocks - Class
        OrderItemRepository orderItemRepository = Mockito.mock(OrderItemRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        BuyerService buyerService = Mockito.mock(BuyerService.class);
        BatchService batchServce = Mockito.mock(BatchService.class);
        AdvertiseService advertiseService = Mockito.mock(AdvertiseService.class);
        // Mock - Actions
        Mockito.when(advertiseService.findById(idAdvertise)).thenReturn(advertise);
        // Service + Spy
        CartService cs = new CartService(orderItemRepository, sellOrderRepository,
                buyerService, batchServce, advertiseService);
        CartService csSpy = Mockito.spy(cs);
        Mockito.doReturn(cart).when(csSpy).getCart(idBuyer);

        // <-- ACT -->
        CartManagementException excecaoEsperada = Assertions.assertThrows(
                CartManagementException.class,
                () -> csSpy.removeAdvertiseItemsFromCart(idBuyer, idAdvertise, 5) //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage()
                .contains("Impossível retirar mais itens de um produto do que os que já estão no carrinho"));
    }

    @Test
    public void verificaRemocaoDeItensDeUmAnuncioDoCarrinho(){
        // <-- ARRANGE -->
        // Comprador
        Long idBuyer = 1L;
        Long idAdvertise = 1L;
        Long idOrderItem = 1L;
        Buyer buyer = new Buyer(idBuyer, null, null, null, null, null);
        Advertise advertise = new Advertise(idAdvertise, null, null,
                null, BigDecimal.ONE, null, false);
        // Carrinho com itens
        SellOrder cart = new SellOrder(1L, buyer, SellOrderStatus.CART,
                null, BigDecimal.ZERO, BigDecimal.ZERO);
        OrderItem itemDoPedido = new OrderItem(idOrderItem,2,advertise,cart);
        List<OrderItem> listaDeItensDoPedido = new ArrayList<>();
        listaDeItensDoPedido.add(itemDoPedido);
        cart.setOrderItemList(listaDeItensDoPedido);
        // Mocks - Class
        OrderItemRepository orderItemRepository = Mockito.mock(OrderItemRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        BuyerService buyerService = Mockito.mock(BuyerService.class);
        BatchService batchServce = Mockito.mock(BatchService.class);
        AdvertiseService advertiseService = Mockito.mock(AdvertiseService.class);
        // Mock - Actions
        Mockito.when(advertiseService.findById(idAdvertise)).thenReturn(advertise);
        Mockito.when(sellOrderRepository.save(Mockito.any(SellOrder.class))).thenReturn(cart);
        // Service + Spy
        CartService cs = new CartService(orderItemRepository, sellOrderRepository,
                buyerService, batchServce, advertiseService);
        CartService csSpy = Mockito.spy(cs);
        Mockito.doReturn(cart).when(csSpy).getCart(idBuyer);

        // <-- ACT -->
        SellOrder carrinhoNovo = csSpy.removeAdvertiseItemsFromCart(idBuyer, idAdvertise, 1);

        // <-- ASSERTION -->
        Assertions.assertEquals(cart,carrinhoNovo);
    }

    @Test
    public void verificaRemocaoDeTodosItensDeUmAnuncioDoCarrinho(){
        // <-- ARRANGE -->
        // Comprador
        Long idBuyer = 1L;
        Long idAdvertise = 1L;
        Long idOrderItem = 1L;
        Buyer buyer = new Buyer(idBuyer, null, null, null, null, null);
        Advertise advertise = new Advertise(idAdvertise, null, null,
                null, BigDecimal.TEN, null, true);
        // Carrinho com itens
        SellOrder carrinhoComItens = new SellOrder(1L, buyer, SellOrderStatus.CART,
                null, BigDecimal.ZERO, BigDecimal.ZERO);
        OrderItem itemDoPedido = new OrderItem(idOrderItem,1,advertise,carrinhoComItens);
        List<OrderItem> listaDeItensDoPedido = new ArrayList<>();
        OrderItem item2DoPedido = new OrderItem(2L,1,advertise,carrinhoComItens);
        listaDeItensDoPedido.add(itemDoPedido);
        listaDeItensDoPedido.add(item2DoPedido);
        carrinhoComItens.setOrderItemList(listaDeItensDoPedido);
        // Listas para comparacao
        List<OrderItem> listaDeItensDoPedidoAntesDaRemocao = new ArrayList<>();
        listaDeItensDoPedidoAntesDaRemocao.add(itemDoPedido);
        listaDeItensDoPedidoAntesDaRemocao.add(item2DoPedido);
        List<OrderItem> listaDeItensDoPedidoAposRemocao = new ArrayList<>();
        listaDeItensDoPedidoAposRemocao.add(item2DoPedido);
        // Mocks - Class
        OrderItemRepository orderItemRepository = Mockito.mock(OrderItemRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        BuyerService buyerService = Mockito.mock(BuyerService.class);
        BatchService batchServce = Mockito.mock(BatchService.class);
        AdvertiseService advertiseService = Mockito.mock(AdvertiseService.class);
        // Mock - Actions
        Mockito.when(advertiseService.findById(idAdvertise)).thenReturn(advertise);
        Mockito.when(sellOrderRepository.save(Mockito.any(SellOrder.class))).thenReturn(carrinhoComItens);
        Mockito.doNothing().when(orderItemRepository).delete(Mockito.any(OrderItem.class));
        // Service + Spy
        CartService cs = new CartService(orderItemRepository, sellOrderRepository,
                buyerService, batchServce, advertiseService);
        CartService csSpy = Mockito.spy(cs);
        Mockito.doReturn(carrinhoComItens).when(csSpy).getCart(idBuyer);

        // <-- ACT -->
        SellOrder carrinhoNovo = csSpy.removeAdvertiseItemsFromCart(idBuyer, idAdvertise, 1);

        // <-- ASSERTION -->
        Assertions.assertEquals(listaDeItensDoPedidoAposRemocao,carrinhoNovo.getOrderItemList());
        Assertions.assertNotEquals(listaDeItensDoPedidoAntesDaRemocao,carrinhoNovo.getOrderItemList());
    }

    @Test
    public void verificaMetodoQueEsvaziaUmCarrinho(){
        // <-- ARRANGE -->
        // Comprador
        Long idBuyer = 1L;
        // Mocks - Class
        OrderItemRepository orderItemRepository = Mockito.mock(OrderItemRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        BuyerService buyerService = Mockito.mock(BuyerService.class);
        BatchService batchServce = Mockito.mock(BatchService.class);
        AdvertiseService advertiseService = Mockito.mock(AdvertiseService.class);
        // Mock - Actions
        Mockito.doNothing().when(sellOrderRepository).deleteByBuyer_IdAndOrderStatus(idBuyer, SellOrderStatus.CART);
        // Service
        CartService cs = new CartService(orderItemRepository, sellOrderRepository,
                buyerService, batchServce, advertiseService);

        // <-- ACT -->
        cs.emptyCart(idBuyer);

        // <-- ASSERTION -->
        Mockito.verify(sellOrderRepository, Mockito.times(1))
                .deleteByBuyer_IdAndOrderStatus(idBuyer, SellOrderStatus.CART);
    }

    @Test
    public void verificaCriacaoDeUmPedidoComUmCarrinhoVazio(){
        // <-- ARRANGE -->
        // Comprador
        Long idBuyer = 1L;
        // Carrinho com itens
        SellOrder carrinho = new SellOrder(1L, null, SellOrderStatus.CART,
                new ArrayList<>(), BigDecimal.ZERO, BigDecimal.ZERO);
        // Mocks - Class
        OrderItemRepository orderItemRepository = Mockito.mock(OrderItemRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        BuyerService buyerService = Mockito.mock(BuyerService.class);
        BatchService batchServce = Mockito.mock(BatchService.class);
        AdvertiseService advertiseService = Mockito.mock(AdvertiseService.class);
        // Service + Spy
        CartService cs = new CartService(orderItemRepository, sellOrderRepository,
                buyerService, batchServce, advertiseService);
        CartService csSpy = Mockito.spy(cs);
        Mockito.doReturn(carrinho).when(csSpy).getCart(idBuyer);

        // <-- ACT -->
        CartManagementException excecaoEsperada = Assertions.assertThrows(
                CartManagementException.class,
                () -> csSpy.createSellOrder(idBuyer) // act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage().contains("Impossível gerar pedido utilizando um carrinho vazio."));
    }

    @Test
    public void verificaInclusaoDeItensDoAnuncioNoCarrinhoAtual() {
        // <-- ARRANGE -->
        // Comprador
        Long idBuyer = 1L;
        Long idAdvertise = 1L;
        Buyer buyer = new Buyer(idBuyer, null, null, null, null, null);
        Advertise advertise = new Advertise(idAdvertise, null, null,
                null, BigDecimal.ONE, null, true);
        SellOrder carrinhoExistente = new SellOrder(1L, buyer, SellOrderStatus.CART,
                new ArrayList<>(), BigDecimal.ZERO, BigDecimal.ZERO);
        List<OrderItem> listaDeItensDoPedido = new ArrayList<>();
        // Carrinho atualizado
        SellOrder novoCarrinho = new SellOrder(1L, buyer, SellOrderStatus.CART,
                listaDeItensDoPedido, BigDecimal.ZERO, BigDecimal.ZERO);
        // Lote verificado no estoque
        Batch lote = new Batch();
        // Mocks - Class
        OrderItemRepository orderItemRepository = Mockito.mock(OrderItemRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        BuyerService buyerService = Mockito.mock(BuyerService.class);
        BatchService batchServce = Mockito.mock(BatchService.class);
        AdvertiseService advertiseService = Mockito.mock(AdvertiseService.class);
        // Mock - Actions
        Mockito.when(advertiseService.findById(idAdvertise)).thenReturn(advertise);
        Mockito.when(batchServce.verifyStock(Mockito.eq(idAdvertise), Mockito.anyInt())).thenReturn(lote);
        Mockito.when(sellOrderRepository.save(Mockito.any(SellOrder.class))).thenReturn(novoCarrinho);
        // Service + Spy
        CartService cs = new CartService(orderItemRepository, sellOrderRepository,
                buyerService, batchServce, advertiseService);
        CartService csSpy = Mockito.spy(cs);
        Mockito.doReturn(carrinhoExistente).when(csSpy).getCart(idBuyer);

        // <-- ACT -->
        SellOrder carrinhoAtualizado = csSpy.addAdvertiseItemsToCart(idBuyer, idAdvertise, 1);

        // <-- ASSERTION -->
        Assertions.assertEquals(novoCarrinho,carrinhoAtualizado);
    }

    @Test
    public void verificaCriacaoDeUmPedido(){
        // <-- ARRANGE -->
        // Comprador
        Long idBuyer = 1L;
        Long idOrderItem = 1L;
        // Carrinho com itens
        SellOrder carrinho = new SellOrder(1L, null, SellOrderStatus.CART,
                null, BigDecimal.ONE, BigDecimal.ZERO);
        SellOrder pedidoCriado = new SellOrder(1L, null, SellOrderStatus.CREATED,
                null, BigDecimal.TEN, BigDecimal.ZERO);
        Advertise anuncio = new Advertise(null, null, null, null, BigDecimal.TEN, AdvertiseStatus.ATIVO, true );
        OrderItem itemDoPedido = new OrderItem(idOrderItem,0,anuncio,null);
        List<OrderItem> listaDeItensDoPedido = new ArrayList<>();
        listaDeItensDoPedido.add(itemDoPedido);
        carrinho.setOrderItemList(listaDeItensDoPedido);
        // Mocks - Class
        OrderItemRepository orderItemRepository = Mockito.mock(OrderItemRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        BuyerService buyerService = Mockito.mock(BuyerService.class);
        BatchService batchServce = Mockito.mock(BatchService.class);
        AdvertiseService advertiseService = Mockito.mock(AdvertiseService.class);
        // Mock - Actions
        Mockito.doNothing().when(batchServce).updateStock(Mockito.anyList());
        Mockito.when(sellOrderRepository.save(Mockito.any(SellOrder.class))).thenReturn(pedidoCriado);
        // Service + Spy
        CartService cs = new CartService(orderItemRepository, sellOrderRepository,
                buyerService, batchServce, advertiseService);
        CartService csSpy = Mockito.spy(cs);
        Mockito.doReturn(carrinho).when(csSpy).getCart(idBuyer);

        // <-- ACT -->
        SellOrder carrinhoNovo = csSpy.createSellOrder(idBuyer);

        // <-- ASSERTION -->
        Assertions.assertEquals(SellOrderStatus.CREATED,carrinhoNovo.getOrderStatus());
    }
}
