package com.mercadolibre.w4g9projetofinal.test.unit;

import com.mercadolibre.w4g9projetofinal.entity.Advertise;
import com.mercadolibre.w4g9projetofinal.entity.OrderItem;
import com.mercadolibre.w4g9projetofinal.entity.Product;
import com.mercadolibre.w4g9projetofinal.entity.SellOrder;
import com.mercadolibre.w4g9projetofinal.entity.Transporter;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.exceptions.BusinessException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.SellOrderRepository;
import com.mercadolibre.w4g9projetofinal.repository.TransporterRepository;
import com.mercadolibre.w4g9projetofinal.service.TransporterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransporterServiceTest {

    @Test
    public void verificaConsultaDeTodosEntregadores(){
        // <-- ARRANGE -->
        List<Transporter> entregadores = new ArrayList<>();
        // Mocks - Class
        TransporterRepository transporterRepository = Mockito.mock(TransporterRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        // Mock - Actions
        Mockito.when(transporterRepository.findAll()).thenReturn(entregadores);
        // Service
        TransporterService ts = new TransporterService(sellOrderRepository,transporterRepository);

        // <-- ACT -->
        List<Transporter> entrtegadoresRetornado = ts.findAll();

        // <-- ASSERTION -->
        Assertions.assertEquals(entregadores,entrtegadoresRetornado);
    }

    @Test
    public void verificaConsultaDeUmEntregador(){
        // <-- ARRANGE -->
        Transporter entregador = new Transporter();
        // Mocks - Class
        TransporterRepository transporterRepository = Mockito.mock(TransporterRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        // Mock - Actions
        Mockito.when(transporterRepository.findById(1L)).thenReturn(Optional.of(entregador));
        // Service
        TransporterService ts = new TransporterService(sellOrderRepository,transporterRepository);

        // <-- ACT -->
        Transporter entrtegadorRetornado = ts.findById(1L);

        // <-- ASSERTION -->
        Assertions.assertEquals(entregador,entrtegadorRetornado);
    }

    @Test
    public void verificaConsultaDeUmEntregadorQuNaoExiste(){
        // <-- ARRANGE -->
        // Mocks - Class
        TransporterRepository transporterRepository = Mockito.mock(TransporterRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        // Mock - Actions
        Mockito.when(transporterRepository.findById(1L)).thenReturn(Optional.empty());
        // Service
        TransporterService ts = new TransporterService(sellOrderRepository,transporterRepository);

        // <-- ACT -->
        ObjectNotFoundException excecaoEsperada = Assertions.assertThrows(
                ObjectNotFoundException.class,
                () -> ts.findById(1L) //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage().contains("Transporter not found"));
    }

    @Test
    public void verificaConsultaDosEntregadoresFiltradosPorStatusDeDisponibilidade(){
        // <-- ARRANGE -->
        List<Transporter> entregadores = new ArrayList<>();
        // Mocks - Class
        TransporterRepository transporterRepository = Mockito.mock(TransporterRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        // Mock - Actions
        Mockito.when(transporterRepository.findByInRoute(Mockito.anyBoolean())).thenReturn(entregadores);
        // Service
        TransporterService ts = new TransporterService(sellOrderRepository,transporterRepository);

        // <-- ACT -->
        List<Transporter> entrtegadores1Retornado = ts.findByStatus(true);
        List<Transporter> entrtegadores2Retornado = ts.findByStatus(false);

        // <-- ASSERTION -->
        Assertions.assertEquals(entregadores,entrtegadores1Retornado);
        Assertions.assertEquals(entregadores,entrtegadores2Retornado);
    }

    @Test
    public void verificaInclusaoDeEntregador(){
        // <-- ARRANGE -->
        Transporter entregador = new Transporter();
        // Mocks - Class
        TransporterRepository transporterRepository = Mockito.mock(TransporterRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        // Mock - Actions
        Mockito.when(transporterRepository.save(entregador)).thenReturn(entregador);
        // Service
        TransporterService ts = new TransporterService(sellOrderRepository,transporterRepository);

        // <-- ACT -->
        Transporter entrtegadorRetornado = ts.insert(entregador);

        // <-- ASSERTION -->
        Assertions.assertEquals(entregador,entrtegadorRetornado);
    }

    @Test
    public void verificaAlteracaoDeEntregador_QuandoEntregadorEstaIndisponivel(){
        // <-- ARRANGE -->
        Transporter entregador = new Transporter();
        entregador.setId(1L);
        entregador.setInRoute(true);
        // Mocks - Class
        TransporterRepository transporterRepository = Mockito.mock(TransporterRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        // Mock - Actions
        // Service + Spy
        TransporterService ts = new TransporterService(sellOrderRepository,transporterRepository);
        TransporterService tsSpy = Mockito.spy(ts);
        Mockito.doReturn(entregador).when(tsSpy).findById(Mockito.anyLong());

        // <-- ACT -->
        BusinessException excecaoEsperada = Assertions.assertThrows(
                BusinessException.class,
                () -> tsSpy.update(entregador) //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage()
                .contains("Cannot update Transporter while in route to Delivery"));
    }

    @Test
    public void verificaAlteracaoDeEntregador_QuandoEntregadorEstaDisponivel() {
        // <-- ARRANGE -->
        Transporter entregador = new Transporter();
        entregador.setId(1L);
        entregador.setInRoute(false);
        // Mocks - Class
        TransporterRepository transporterRepository = Mockito.mock(TransporterRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        // Mock - Actions
        Mockito.when(transporterRepository.save(entregador)).thenReturn(entregador);
        // Service + Spy
        TransporterService ts = new TransporterService(sellOrderRepository,transporterRepository);
        TransporterService tsSpy = Mockito.spy(ts);
        Mockito.doReturn(entregador).when(tsSpy).findById(Mockito.anyLong());

        // <-- ACT -->
        Transporter entregadorRetorno = tsSpy.update(entregador);

        // <-- ASSERTION -->
        Assertions.assertEquals(entregador,entregadorRetorno);
    }

    @Test
    public void verificaExclusaoDeEntregador_QuandoEntregadorEstaIndisponivel(){
        // <-- ARRANGE -->
        Transporter entregador = new Transporter();
        entregador.setInRoute(true);
        // Mocks - Class
        TransporterRepository transporterRepository = Mockito.mock(TransporterRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        // Mock - Actions
        // Service + Spy
        TransporterService ts = new TransporterService(sellOrderRepository,transporterRepository);
        TransporterService tsSpy = Mockito.spy(ts);
        Mockito.doReturn(entregador).when(tsSpy).findById(Mockito.anyLong());

        // <-- ACT -->
        BusinessException excecaoEsperada = Assertions.assertThrows(
                BusinessException.class,
                () -> tsSpy.delete(1L) //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage()
                .contains("Cannot delete Transporter while in route to Delivery"));
    }

    @Test
    public void verificaExclusaoDeEntregador_QuandoEntregadorEstaDisponivel(){
        // <-- ARRANGE -->
        Transporter entregador = new Transporter();
        entregador.setInRoute(false);
        // Mocks - Class
        TransporterRepository transporterRepository = Mockito.mock(TransporterRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        // Mock - Actions
        Mockito.doNothing().when(transporterRepository).delete(entregador);
        // Service + Spy
        TransporterService ts = new TransporterService(sellOrderRepository,transporterRepository);
        TransporterService tsSpy = Mockito.spy(ts);
        Mockito.doReturn(entregador).when(tsSpy).findById(Mockito.anyLong());

        // <-- ACT -->
        tsSpy.delete(1L);

        // <-- ASSERTION -->
        Mockito.verify(transporterRepository, Mockito.times(1))
                .delete(entregador);
    }

    @Test
    public void verificaChamadaDeEntregador_QuandoNemTodosIdsExistemNasEntregas(){
        // <-- ARRANGE -->
        List<Long> listaDeIdsDeEntregas = new ArrayList<>();
        listaDeIdsDeEntregas.add(1L);
        List<SellOrder> entregas = new ArrayList<>();
        // Mocks - Class
        TransporterRepository transporterRepository = Mockito.mock(TransporterRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        // Mock - Actions
        Mockito.when(sellOrderRepository.findAllById(listaDeIdsDeEntregas)).thenReturn(entregas);
        // Service
        TransporterService ts = new TransporterService(sellOrderRepository,transporterRepository);

        // <-- ACT -->
        ObjectNotFoundException excecaoEsperada = Assertions.assertThrows(
                ObjectNotFoundException.class,
                () -> ts.calldelivery(listaDeIdsDeEntregas) //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage().contains("Some Id's doesn't exist."));
    }

    @Test
    public void verificaChamadaDeEntregadorQuandoNaoTemDisponiveis(){
        // <-- ARRANGE -->
        List<Long> listaDeIdsDeEntregas = new ArrayList<>();
        listaDeIdsDeEntregas.add(1L);
        Product product = new Product();
        product.setCategoryRefrigeration(RefrigerationType.COLD);
        Advertise anuncio = new Advertise();
        anuncio.setProduct(product);
        OrderItem item = new OrderItem();
        item.setAdvertise(anuncio);
        item.setQuantity(1);
        List<OrderItem> listaItens = new ArrayList<>();
        listaItens.add(item);
        SellOrder deliver1 = new SellOrder();
        deliver1.setShippingRate(BigDecimal.ZERO);
        deliver1.setOrderItemList(listaItens);
        List<SellOrder> entregas = new ArrayList<>();
        entregas.add(deliver1);
        List<Transporter> listaEntregadores = new ArrayList<>();
        // Mock - Class
        TransporterRepository transporterRepository = Mockito.mock(TransporterRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        // Mock - Actions
        Mockito.when(sellOrderRepository.findAllById(listaDeIdsDeEntregas)).thenReturn(entregas);
        Mockito.when(transporterRepository
                .findByInRouteFalseAndColdMaxQuantityAfterAndFreshMaxQuantityAfterAndFrozenMaxQuantityAfter
                        (Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(listaEntregadores);
        // Service
        TransporterService ts = new TransporterService(sellOrderRepository,transporterRepository);

        // <-- ACT -->
        ObjectNotFoundException excecaoEsperada = Assertions.assertThrows(
                ObjectNotFoundException.class,
                () -> ts.calldelivery(listaDeIdsDeEntregas) //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage().contains("There is no Transporter available, than can carry: 0 Fresh items, 0 Cold items and 0 Frozen items. Please wait, or try to divide the deliveries."));
    }

    @Test
    public void verificaChamadaDeEntregadorQuandoFreteForGratis(){
        // <-- ARRANGE -->
        List<Long> listaDeIdsDeEntregas = new ArrayList<>();
        listaDeIdsDeEntregas.add(1L);
        Product product = new Product();
        product.setCategoryRefrigeration(RefrigerationType.COLD);
        Advertise anuncio = new Advertise();
        anuncio.setProduct(product);
        OrderItem item = new OrderItem();
        item.setAdvertise(anuncio);
        item.setQuantity(1);
        List<OrderItem> listaItens = new ArrayList<>();
        listaItens.add(item);
        SellOrder deliver1 = new SellOrder();
        deliver1.setShippingRate(BigDecimal.ZERO);
        deliver1.setOrderItemList(listaItens);
        List<SellOrder> entregas = new ArrayList<>();
        entregas.add(deliver1);
        List<Transporter> listaEntregadores = new ArrayList<>();
        Transporter entregador = new Transporter();
        listaEntregadores.add(entregador);
        Transporter entregadorEmTransito = new Transporter();
        entregadorEmTransito.setDeliveryOrderList(entregas);
        entregadorEmTransito.setInRoute(true);
        // Mock - Class
        TransporterRepository transporterRepository = Mockito.mock(TransporterRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        // Mock - Actions
        Mockito.when(sellOrderRepository.findAllById(listaDeIdsDeEntregas)).thenReturn(entregas);
        Mockito.when(transporterRepository.save(Mockito.any())).thenReturn(entregadorEmTransito);
        Mockito.when(transporterRepository
                .findByInRouteFalseAndColdMaxQuantityAfterAndFreshMaxQuantityAfterAndFrozenMaxQuantityAfter
                        (Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(listaEntregadores);
        // Service
        TransporterService ts = new TransporterService(sellOrderRepository,transporterRepository);

        // <-- ACT -->
        Transporter entregadorRetornado = ts.calldelivery(listaDeIdsDeEntregas);

        // <-- ASSERTION -->
        Assertions.assertEquals(entregadorEmTransito,entregadorRetornado);
    }

    @Test
    public void verificaChamadaDeEntregadorQuandoFreteNaoForGratis() {
        // <-- ARRANGE -->
        List<Long> listaDeIdsDeEntregas = new ArrayList<>();
        listaDeIdsDeEntregas.add(1L);
        Product product = new Product();
        product.setCategoryRefrigeration(RefrigerationType.COLD);
        Advertise anuncio = new Advertise();
        anuncio.setProduct(product);
        OrderItem item = new OrderItem();
        item.setAdvertise(anuncio);
        item.setQuantity(1);
        List<OrderItem> listaItens = new ArrayList<>();
        listaItens.add(item);
        SellOrder deliver1 = new SellOrder();
        deliver1.setShippingRate(BigDecimal.TEN);
        deliver1.setOrderItemList(listaItens);
        List<SellOrder> entregas = new ArrayList<>();
        entregas.add(deliver1);
        List<Transporter> listaEntregadores = new ArrayList<>();
        Transporter entregador = new Transporter();
        listaEntregadores.add(entregador);
        Transporter entregadorEmTransito = new Transporter();
        entregadorEmTransito.setDeliveryOrderList(entregas);
        entregadorEmTransito.setInRoute(true);
        // Mock - Class
        TransporterRepository transporterRepository = Mockito.mock(TransporterRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        // Mock - Actions
        Mockito.when(sellOrderRepository.findAllById(listaDeIdsDeEntregas)).thenReturn(entregas);
        Mockito.when(transporterRepository.save(Mockito.any())).thenReturn(entregadorEmTransito);
        Mockito.when(transporterRepository
                .findByInRouteFalseAndColdMaxQuantityAfterAndFreshMaxQuantityAfterAndFrozenMaxQuantityAfter
                        (Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(listaEntregadores);
        // Service
        TransporterService ts = new TransporterService(sellOrderRepository, transporterRepository);

        // <-- ACT -->
        Transporter entregadorRetornado = ts.calldelivery(listaDeIdsDeEntregas);

        // <-- ASSERTION -->
        Assertions.assertEquals(entregadorEmTransito, entregadorRetornado);
    }

    @Test
    public void verificaConfirmacaoDelivery_QuandoUmIdInformadoNaoFazParteDaEntrega() {
        // <-- ARRANGE -->
        List<Long> listaDeIdsDeEntregas = new ArrayList<>();
        listaDeIdsDeEntregas.add(1L);
        List<SellOrder> entregas = new ArrayList<>();
        SellOrder delivery = new SellOrder();
        delivery.setId(1L);
        entregas.add(delivery);
        Transporter entregador = new Transporter();
        entregador.setDeliveryOrderList(new ArrayList<>());

        // Mock - Class
        TransporterRepository transporterRepository = Mockito.mock(TransporterRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        // Mock - Actions
        Mockito.when(sellOrderRepository.findAllById(listaDeIdsDeEntregas)).thenReturn(entregas);
        // Service + Spy
        TransporterService ts = new TransporterService(sellOrderRepository, transporterRepository);
        TransporterService tsSpy = Mockito.spy(ts);
        Mockito.doReturn(entregador).when(tsSpy).findById(1L);

        // <-- ACT -->
        ObjectNotFoundException excecaoEsperada = Assertions.assertThrows(
                ObjectNotFoundException.class,
                () -> tsSpy.confirmDelivery(1L,listaDeIdsDeEntregas) //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage()
                .contains("Delivery id: 1 informed, isn't part of the deliveries of the Transporter 1"));

    }

    @Test
    public void verificaConfirmacaoDelivery_QuandoNemTodaEntregaFoiFinalizada() {
        // <-- ARRANGE -->
        List<Long> listaDeIdsDeEntregas = new ArrayList<>();
        listaDeIdsDeEntregas.add(1L);
        List<SellOrder> entregas = new ArrayList<>();
        SellOrder delivery = new SellOrder();
        delivery.setId(1L);
        entregas.add(delivery);
        Transporter entregador = new Transporter();
        entregador.setSalary(BigDecimal.ONE);
        entregador.setPaymentForDelivery(BigDecimal.ONE);
        entregador.setDeliveryOrderList(new ArrayList<>());
        SellOrder delivery1 = new SellOrder();
        SellOrder delivery2 = new SellOrder();
        delivery1.setId(1L);
        delivery2.setId(1L);
        entregador.getDeliveryOrderList().add(delivery1);
        entregador.getDeliveryOrderList().add(delivery2);
        // Mock - Class
        TransporterRepository transporterRepository = Mockito.mock(TransporterRepository.class);
        SellOrderRepository sellOrderRepository = Mockito.mock(SellOrderRepository.class);
        // Mock - Actions
        Mockito.when(transporterRepository.save(entregador)).thenReturn(entregador);
        Mockito.when(sellOrderRepository.findAllById(listaDeIdsDeEntregas)).thenReturn(entregas);
        Mockito.when(sellOrderRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
        // Service + Spy
        TransporterService ts = new TransporterService(sellOrderRepository, transporterRepository);
        TransporterService tsSpy = Mockito.spy(ts);
        Mockito.doReturn(entregador).when(tsSpy).findById(1L);

        // <-- ACT -->
        Transporter entregadorRetornado = tsSpy.confirmDelivery(1L,listaDeIdsDeEntregas);

        // <-- ASSERTION -->
        Assertions.assertEquals(entregador,entregadorRetornado);
    }
}
