package com.mercadolibre.w4g9projetofinal.test.unit;

public class InboundOrderServiceTest {





    /*

    @Test
    public void verificaRemocaoDeItensDeUmAnuncioDoCarrinho(){
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
        OrderItem itemDoPedido = new OrderItem(idOrderItem,2,advertise,cart);
        List<OrderItem> listaDeItensDoPedido = new ArrayList<>();
        listaDeItensDoPedido.add(itemDoPedido);
        cart.setOrderItemList(listaDeItensDoPedido);
        // Spy Entity
        SellOrder soSpy = Mockito.spy(cart);
        Mockito.doReturn(itemDoPedido).when(soSpy).getOrderItem(advertise);
        Mockito.doNothing().when(soSpy).calcTotalValueOrder();
        // Mocks - Class
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        BuyerService buyerService = Mockito.mock(BuyerService.class);
        BatchService batchServce = Mockito.mock(BatchService.class);
        AdvertiseService advertiseService = Mockito.mock(AdvertiseService.class);
        // Mock - Actions
        Mockito.when(advertiseService.findById(idAdvertise)).thenReturn(advertise);
        Mockito.when(sellOrderRepository.save(Mockito.any(SellOrder.class))).thenReturn(cart);
        // Service + Spy
        CartService cs = new CartService(sellOrderRepository, buyerService, batchServce, advertiseService);
        CartService csSpy = Mockito.spy(cs);
        Mockito.doReturn(soSpy).when(csSpy).getCart(idBuyer);

        // <-- ACT -->
        SellOrder carrinhoNovo = csSpy.removeAdvertiseItemsFromCart(idBuyer, idAdvertise, 1);

        // <-- ASSERTION -->
        Assertions.assertEquals(cart,carrinhoNovo);
    }

     */
}
