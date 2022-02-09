package com.mercadolibre.w4g9projetofinal.test.unit;

import com.mercadolibre.w4g9projetofinal.entity.*;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.exceptions.BusinessException;
import com.mercadolibre.w4g9projetofinal.exceptions.CartManagementException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.BatchRepository;
import com.mercadolibre.w4g9projetofinal.service.BatchService;
import com.mercadolibre.w4g9projetofinal.service.SectionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.*;

public class BatchServiceTest {

    @Test
    public void verificaLoteQuandoNaoPertenceAoAnuncio() {
        // <-- ARRANGE -->
        Long idAdvertise = 1L;
        // Mocks - Class
        BatchRepository batchRepository = Mockito.mock(BatchRepository.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        // Mock - Actions
        Mockito.when(batchRepository.findByAdvertise_Id(idAdvertise)).thenReturn(Optional.empty());
        // Service
        BatchService bs = new BatchService(batchRepository, sectionService);

        // <-- ACT -->
        CartManagementException excecaoEsperada = Assertions.assertThrows(
                CartManagementException.class,
                () -> bs.verifyStock(idAdvertise, 1) //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage().contains("Lote referente ao anuncio 1 não encontrado"));
    }

    @Test
    public void verificaLoteQuandoTentaRetirarMaisItensDoQueOsDoEstoque() {
        // <-- ARRANGE -->
        Long idAdvertise = 1L;
        Batch batch = new Batch(null, 10, 10, 10F, 10F,
                null, null, null, null, null);
        // Mocks - Class
        BatchRepository batchRepository = Mockito.mock(BatchRepository.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        // Mock - Actions
        Mockito.when(batchRepository.findByAdvertise_Id(idAdvertise)).thenReturn(Optional.of(batch));
        // Service
        BatchService bs = new BatchService(batchRepository, sectionService);

        // <-- ACT -->
        CartManagementException excecaoEsperada = Assertions.assertThrows(
                CartManagementException.class,
                () -> bs.verifyStock(idAdvertise, 20) //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage()
                .contains("Imposssível realizar compra, pois o Produto null tem somente 10 itens em estoque, "
                        + "e você está tentando comprar 20 itens."));
    }

    @Test
    public void verificaLoteQuandoPertenceAoAnuncio() {
        // <-- ARRANGE -->
        Long idAdvertise = 1L;
        Batch batch = new Batch(null, 10, 10, 10F, 10F,
                null, null, null, null, null);
        // Mocks - Class
        BatchRepository batchRepository = Mockito.mock(BatchRepository.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        // Mock - Actions
        Mockito.when(batchRepository.findByAdvertise_Id(idAdvertise)).thenReturn(Optional.of(batch));
        // Service
        BatchService bs = new BatchService(batchRepository, sectionService);

        // <-- ACT -->
        Batch loteRetornado = bs.verifyStock(idAdvertise, 1);

        // <-- ASSERTION -->
        Assertions.assertEquals(batch, loteRetornado);
    }

    @Test
    public void verificaAtualizacaoNoRepositorioDeUmaListaDeLotes() {
        // <-- ARRANGE -->
        Long idAdvertise = 1L;
        Section setor = new Section();
        InboundOrder inboundOrder = new InboundOrder();
        Batch batch = new Batch();
        Advertise advertise = new Advertise();
        advertise.setId(1L);
        List<OrderItem> itemList = new ArrayList<>();
        OrderItem orderItem = new OrderItem(null, 1, advertise, null);
        itemList.add(orderItem);
        // Spy Entity
        inboundOrder.setSection(setor);
        batch.setInboundOrder(inboundOrder);
        // Mocks - Class
        BatchRepository batchRepository = Mockito.mock(BatchRepository.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        // Mock - Actions
        Mockito.when(batchRepository.findByAdvertise_Id(idAdvertise)).thenReturn(Optional.of(batch));
        Mockito.when(sectionService.save(Mockito.any())).thenReturn(new Section());
        // Service + Spy
        BatchService bs = new BatchService(batchRepository, sectionService);
        BatchService bsSpy = Mockito.spy(bs);
        Mockito.doReturn(batch).when(bsSpy).verifyStock(Mockito.anyLong(), Mockito.anyInt());

        // <-- ACT -->
        bsSpy.updateStock(itemList);

        // <-- ASSERTION -->
        Mockito.verify(batchRepository, Mockito.times(1))
                .saveAll(Mockito.any());
    }

    @Test
    public void verificaBuscaDeLotesPorIdDeUmProduto() {
        // <-- ARRANGE -->
        Long idProduct = 1L;
        List<Batch> batchList = new ArrayList<>();
        // Mocks - Class
        BatchRepository batchRepository = Mockito.mock(BatchRepository.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        // Mock - Actions
        Mockito.when(batchRepository.findByProduct_Id(idProduct)).thenReturn(batchList);
        // Service
        BatchService bs = new BatchService(batchRepository, sectionService);

        // <-- ACT -->
        List<Batch> listaLotes = bs.findByProductId(idProduct);

        // <-- ASSERTION -->
        Assertions.assertEquals(batchList, listaLotes);
    }

    @Test
    public void verificaBuscaDeLotesPorSetor_QuandoNaoExisteNenhumComDataDeValidadeInformada() {
        // <-- ARRANGE -->
        Long idSetor = 1L;
        // Mocks - Class
        BatchRepository batchRepository = Mockito.mock(BatchRepository.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        // Mock - Actions
        Mockito.when(batchRepository.findByDueDateBeforeAndSectionId(Mockito.anyLong(), Mockito.any()))
                .thenReturn(new ArrayList<>());
        // Service
        BatchService bs = new BatchService(batchRepository, sectionService);

        // <-- ACT -->
        ObjectNotFoundException excecaoEsperada = Assertions.assertThrows(
                ObjectNotFoundException.class,
                () -> bs.findByDueDateBeforeAndSectionId(1, idSetor) //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage().contains("There is no Batch for this Filter."));
    }

    @Test
    public void verificaBuscaDeLotesPorSetor_QuandoExisteComDataDeValidadeInformada() {
        // <-- ARRANGE -->
        Long idSetor = 1L;
        Section setor = new Section(idSetor, null, null, RefrigerationType.COLD,
                1, 1, 1F, 1F, null);
        List<Batch> list = new ArrayList<>();
        Batch b = new Batch();
        list.add(b);
        Map<Batch, RefrigerationType> batches = new HashMap<>();
        batches.put(b, RefrigerationType.COLD);
        // Mocks - Class
        BatchRepository batchRepository = Mockito.mock(BatchRepository.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        // Mock - Actions
        Mockito.when(batchRepository.findByDueDateBeforeAndSectionId(Mockito.anyLong(), Mockito.any()))
                .thenReturn(list);
        Mockito.when(sectionService.findById(idSetor)).thenReturn(setor);
        // Service
        BatchService bs = new BatchService(batchRepository, sectionService);

        // <-- ACT -->
        Map<Batch, RefrigerationType> lotes = bs.findByDueDateBeforeAndSectionId(1, idSetor);

        // <-- ASSERTION -->
        Assertions.assertEquals(batches, lotes);
    }

    @Test
    public void verificaBuscaDeLotesPorTipoDeRefrigeracao_QuandoNaoExisteNenhumComDataDeValidadeInformada() {
        // <-- ARRANGE -->
        // Mocks - Class
        BatchRepository batchRepository = Mockito.mock(BatchRepository.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        // Mock - Actions
        Mockito.when(batchRepository.findByDueDateBeforeAndRefrigerationType(Mockito.any(), Mockito.any()))
                .thenReturn(new ArrayList<>());
        // Service
        BatchService bs = new BatchService(batchRepository, sectionService);

        // <-- ACT -->
        ObjectNotFoundException excecaoEsperada = Assertions.assertThrows(
                ObjectNotFoundException.class,
                () -> bs.findByDueDateBeforeAndRefrigerationType(1, "RF", "asc") //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage().contains("There is no Batch for this Filter."));
    }

    @Test
    public void verificaBuscaDeLotesPorTipoDeRefrigeracao_QuandoExisteComDataDeValidadeInformada_OrdemInformadaErrada() {
        // <-- ARRANGE -->
        Batch b1 = new Batch();
        List<Batch> listDesordenada = new ArrayList<>();
        listDesordenada.add(b1);
        // Mocks - Class
        BatchRepository batchRepository = Mockito.mock(BatchRepository.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        // Mock - Actions
        Mockito.when(batchRepository.findByDueDateBeforeAndRefrigerationType(Mockito.any(), Mockito.any()))
                .thenReturn(listDesordenada);
        // Service
        BatchService bs = new BatchService(batchRepository, sectionService);

        // <-- ACT -->
        BusinessException excecaoEsperada = Assertions.assertThrows(
                BusinessException.class,
                () -> bs.findByDueDateBeforeAndRefrigerationType(1, "RF", "aYsc") //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage().contains("Order param acceptable: ''asc'' and ''desc''"));
    }

    @Test
    public void verificaBuscaDeLotesPorTipoDeRefrigeracao_QuandoExisteComDataDeValidadeInformada_OrdemCrescente() {
        // <-- ARRANGE -->
        LocalDate lc1 = LocalDate.now();
        LocalDate lc2 = LocalDate.now().plusDays(3);
        LocalDate lc3 = LocalDate.now().plusDays(10);
        Batch b1 = new Batch();
        b1.setDueDate(lc1);
        Batch b2 = new Batch();
        b2.setDueDate(lc2);
        Batch b3 = new Batch();
        b3.setDueDate(lc3);
        List<Batch> listDesordenada = new ArrayList<>();
        listDesordenada.add(b2);
        listDesordenada.add(b1);
        listDesordenada.add(b3);
        Map<Batch, RefrigerationType> listOrdenada = new HashMap<>();
        listOrdenada.put(b1, RefrigerationType.COLD);
        listOrdenada.put(b2, RefrigerationType.COLD);
        listOrdenada.put(b3, RefrigerationType.COLD);
        // Mocks - Class
        BatchRepository batchRepository = Mockito.mock(BatchRepository.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        // Mock - Actions
        Mockito.when(batchRepository.findByDueDateBeforeAndRefrigerationType(Mockito.any(), Mockito.any()))
                .thenReturn(listDesordenada);
        // Service
        BatchService bs = new BatchService(batchRepository, sectionService);

        // <-- ACT -->
        Map<Batch, RefrigerationType> lista = bs
                .findByDueDateBeforeAndRefrigerationType(1, "RF", "asc");

        // <-- ASSERTION -->
        Assertions.assertEquals(listOrdenada.toString(),lista.toString());
        // toString -> pois Map é considerado igual mesmo desordenado
    }

    @Test
    public void verificaBuscaDeLotesPorTipoDeRefrigeracao_QuandoExisteComDataDeValidadeInformada_OrdemDecrescente() {
        // <-- ARRANGE -->
        LocalDate lc1 = LocalDate.now();
        LocalDate lc2 = LocalDate.now().plusDays(3);
        LocalDate lc3 = LocalDate.now().plusDays(10);
        Batch b1 = new Batch();
        b1.setDueDate(lc1);
        Batch b2 = new Batch();
        b2.setDueDate(lc2);
        Batch b3 = new Batch();
        b3.setDueDate(lc3);
        List<Batch> listDesordenada = new ArrayList<>();
        listDesordenada.add(b2);
        listDesordenada.add(b1);
        listDesordenada.add(b3);
        Map<Batch, RefrigerationType> listOrdenada = new HashMap<>();
        listOrdenada.put(b3, RefrigerationType.COLD);
        listOrdenada.put(b2, RefrigerationType.COLD);
        listOrdenada.put(b1, RefrigerationType.COLD);
        // Mocks - Class
        BatchRepository batchRepository = Mockito.mock(BatchRepository.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        // Mock - Actions
        Mockito.when(batchRepository.findByDueDateBeforeAndRefrigerationType(Mockito.any(), Mockito.any()))
                .thenReturn(listDesordenada);
        // Service
        BatchService bs = new BatchService(batchRepository, sectionService);

        // <-- ACT -->
        Map<Batch, RefrigerationType> lista =
                bs.findByDueDateBeforeAndRefrigerationType(1, "RF", "desc");

        // <-- ASSERTION -->
        Assertions.assertEquals(listOrdenada.toString(),lista.toString());
        // toString -> pois Map é considerado igual mesmo desordenado
    }
}
