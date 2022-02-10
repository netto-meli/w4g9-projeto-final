package com.mercadolibre.w4g9projetofinal.test.unit;

import com.mercadolibre.w4g9projetofinal.entity.*;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.exceptions.BusinessException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.exceptions.SectionManagementException;
import com.mercadolibre.w4g9projetofinal.repository.SectionRepository;
import com.mercadolibre.w4g9projetofinal.service.SectionService;
import com.sun.xml.bind.v2.TODO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SectionServiceTest {

    @Test
    void verificaSeSectionEstaSendoGravada() {
        SectionRepository repositoryMock = Mockito.mock(SectionRepository.class);

        Section section = Mockito.mock(Section.class);
        section.setId(1L);
        //Retorno Section MOCK
        Mockito.when(repositoryMock.findById(1L)).thenReturn(Optional.of(section));
        SectionService sectionService = new SectionService(repositoryMock);
        Section sectionResult = sectionService.findById(1L);
        //Salvo Section
        Mockito.when(repositoryMock.save(sectionResult)).thenReturn(section);
        Section sectionTest = sectionService.save(section);
        Assertions.assertEquals(section, sectionTest);
        Assertions.assertEquals(sectionResult, section);
    }

    @Test
    void verificaSeSectionEstaSendoAtualizada() {
        SectionRepository repositoryMock = Mockito.mock(SectionRepository.class);

        Section section = new Section();
        section.setId(1L);
        section.setName("mechanic");
        //Insere Section
        Mockito.when(repositoryMock.findById(1L)).thenReturn(Optional.of(section));
        SectionService sectionService = new SectionService(repositoryMock);
        Section sectionResult = sectionService.findById(1L);

        //Atualizo o nome
        sectionResult.setName("Teste 316");
        //Salvo a Section alterada no banco

        //Quando sectionService.update chamar o 'salve' do repositoryMock, eu devolvo uma Section
        Mockito.when(repositoryMock.save(sectionResult)).thenReturn(sectionResult);
        Section sectionTest = sectionService.update(sectionResult);

        //Verifico se o nome foi atualizado.
        Assertions.assertEquals("Teste 316", sectionTest.getName());
    }

    @Test
    void verificaSeSectionEstaSendoDeletada() {
        SectionRepository repositoryMock = Mockito.mock(SectionRepository.class);
        SectionService sectionService = new SectionService(repositoryMock);
        Section section = new Section();
        section.setId(1L);

        //Vai tentar deletar uma Section que nao existe e vai lançar uma exception
        Mockito.doNothing().when(repositoryMock).delete(Mockito.any());
        Mockito.when(repositoryMock.findById(1L)).thenReturn(Optional.of(section));
        //Tente deletar uma Section qualquer

        sectionService.delete(1L);
        //Verifico se ouve interação com o repositoryMock.deleteById, se houve então tudo funcionando!
        Mockito.verify(repositoryMock).delete(Mockito.any());
    }

    @Test
    void verificaRetornoDeListaDeSections() {
        SectionRepository repositoryMock = Mockito.mock(SectionRepository.class);
        SectionService sectionService = new SectionService(repositoryMock);

        Section sec1 = new Section();
        Section sec2 = new Section();
        Section sec3 = new Section();
        sec1.setId(1L);
        sec2.setId(2L);
        sec3.setId(3L);
        List<Section> sectionList = new ArrayList<>();
        sectionList.add(sec1);
        sectionList.add(sec2);
        sectionList.add(sec3);

        Mockito.when(repositoryMock.findAll()).thenReturn(sectionList);
        List<Section> result = sectionService.findAll();
        Assertions.assertNotNull(result.get(0));
    }

    @Test
    void verificaORetornoDeUmaSectionPeloId() {
        SectionRepository repositoryMock = Mockito.mock(SectionRepository.class);
        SectionService sectionService = new SectionService(repositoryMock);

        Section section = new Section();
        section.setId(1L);

        Mockito.when(repositoryMock.findById(1L)).thenReturn(Optional.of(section));
        Section sectionResult = sectionService.findById(1L);

        //O restorno nao pode ser null
        Assertions.assertNotNull(section);

        //A section precisa ter o ID 1
        Assertions.assertEquals(1L, sectionResult.getId());
    }

    @Test
    void sectionfindByInboundOrderId() {
        SectionRepository repositoryMock = Mockito.mock(SectionRepository.class);
        SectionService sectionService = new SectionService(repositoryMock);

        Section section = new Section();
        section.setId(1L);
        List<InboundOrder> list = new ArrayList<>();
        InboundOrder inboundOrder = new InboundOrder();
        inboundOrder.setId(1L);
        list.add(inboundOrder);
        section.setInboundOrderList(list);

        Mockito.when(repositoryMock.findByInboundOrder_Id(1L)).thenReturn(Optional.of(section));
        // Mockito.when(repositoryMock.findByInboundOrder_Id(1L)).thenThrow(ObjectNotFoundException.class);
        Section sectionResult = sectionService.findByInboundOrderId(1L);

        ObjectNotFoundException expectedException = Assertions.assertThrows(ObjectNotFoundException.class, () -> sectionService.findByInboundOrderId(3L));

        Assertions.assertTrue(expectedException.getMessage().contains("Setor nao encontrado através do ID da Inbound Order"));
        Assertions.assertEquals(sectionResult, section);
    }

    @Test
    public void validaDataDeValidadeDosLotes() {
        //Assert
        Batch b1 = new Batch(1L, 1, 1, 1F, 1F,
                LocalDate.now(), LocalDate.now(),LocalDateTime.now(), null, null );
        Batch b2 = new Batch(2L, 1, 1, 1F, 1F,
                LocalDate.now().plusDays(2), LocalDate.now(),LocalDateTime.now(), null, null );
        Batch b3 = new Batch(3L, 1, 1, 1F, 1F,
                LocalDate.now().minusDays(5), LocalDate.now(),LocalDateTime.now(), null, null );
        List<Batch> list = new ArrayList<>();
        list.add(b1);
        list.add(b2);
        list.add(b3);
        Section section = new Section(1L, null, "name", RefrigerationType.COLD,
                1, 10, 1F, 1F ,null );

        SectionRepository repositoryMock = Mockito.mock(SectionRepository.class);

        SectionService sectionService = new SectionService(repositoryMock);

        SectionManagementException expectedException = assertThrows(SectionManagementException.class, () ->
                sectionService.validateBatchSection(list, section, true));

        assertTrue(expectedException.getMessage().contains("Batch: 1 has a Due Date of "));
        assertTrue(expectedException.getMessage().contains("Batch: 3 has a Due Date of "));
    }

    @Test
    public void validaSetoresDosLotes_QuandoLotesTiveremTemperaturasDiferentes() {
        //Assert
        Batch b1 = new Batch(1L, 1, 1, 1F, 10F,
                LocalDate.now(), LocalDate.now().plusDays(1),LocalDateTime.now(), null, null );
        Batch b2 = new Batch(2L, 1, 1, 1F, 1F,
                LocalDate.now().plusDays(2), LocalDate.now(),LocalDateTime.now(), null, null );
        Batch b3 = new Batch(3L, 1, 1, 1F, -10F,
                LocalDate.now().plusDays(5), LocalDate.now(),LocalDateTime.now(), null, null );
        List<Batch> list = new ArrayList<>();
        list.add(b1);
        list.add(b2);
        list.add(b3);
        Section section = new Section(1L, null, "name", RefrigerationType.COLD,
                1, 10, 1F, 1F ,null );

        SectionRepository repositoryMock = Mockito.mock(SectionRepository.class);

        SectionService sectionService = new SectionService(repositoryMock);

        SectionManagementException expectedException = assertThrows(SectionManagementException.class, () ->
                sectionService.validateBatchSection(list, section, true));

        assertTrue(expectedException.getMessage().contains("Batch number(s): 1, 3, does not belong to the Section Informed."));
    }

    @Test
    public void validaSetoresDosLotes_QuandoNaoTemEspaco() {
        //Assert
        Batch b1 = new Batch(1L, 1, 1, 1F, 1F,
                LocalDate.now().plusDays(7), LocalDate.now().plusDays(1),LocalDateTime.now(), null, null );
        Batch b2 = new Batch(2L, 1, 1, 1F, 1F,
                LocalDate.now().plusDays(2), LocalDate.now(),LocalDateTime.now(), null, null );
        Batch b3 = new Batch(3L, 1, 1, 1F, 1F,
                LocalDate.now().plusDays(5), LocalDate.now(),LocalDateTime.now(), null, null );
        List<Batch> list = new ArrayList<>();
        list.add(b1);
        list.add(b2);
        list.add(b3);
        Section section = new Section(1L, null, "nameSetor", RefrigerationType.COLD,
                1, 1, 1F, 1F ,null );

        SectionRepository repositoryMock = Mockito.mock(SectionRepository.class);

        SectionService sectionService = new SectionService(repositoryMock);



        SectionManagementException expectedException = assertThrows(SectionManagementException.class, () ->
                sectionService.validateBatchSection(list, section, true));

        assertTrue(expectedException.getMessage().contains("Setor: nameSetor, doesn't have stock space available for this update"));
    }

    @Test
    public void validaSetoresDosLotes() {
        //Assert
        Batch b1 = new Batch(1L, 1, 1, 1F, 1F,
                LocalDate.now().plusDays(7), LocalDate.now().plusDays(1),LocalDateTime.now(), null, null );
        Batch b2 = new Batch(2L, 1, 1, 1F, 1F,
                LocalDate.now().plusDays(2), LocalDate.now(),LocalDateTime.now(), null, null );
        Batch b3 = new Batch(3L, 1, 1, 1F, 1F,
                LocalDate.now().plusDays(5), LocalDate.now(),LocalDateTime.now(), null, null );
        List<Batch> list = new ArrayList<>();
        list.add(b1);
        list.add(b2);
        list.add(b3);
        Section section = new Section(1L, null, "name", RefrigerationType.COLD,
                1, 10, 1F, 1F ,null );

        SectionRepository repositoryMock = Mockito.mock(SectionRepository.class);

        SectionService sectionService = new SectionService(repositoryMock);

        Section sectionReturn = sectionService.validateBatchSection(list, section, false);

        Assertions.assertEquals(sectionReturn,section);
    }

    @Test
    public void validaEspacoDoEstoqueDoSetor_QuandoAnunciForDiferente() {
        //Assert
        Advertise advertise = new Advertise();
        Advertise advertise2 = new Advertise();
        advertise.setId(1L);
        advertise2.setId(2L);
        Batch b1 = new Batch(1L, 1, 1, 1F, 1F,
                LocalDate.now().plusDays(7), LocalDate.now().plusDays(1),LocalDateTime.now(), advertise, null );
        Batch b2 = new Batch(2L, 1, 1, 1F, 1F,
                LocalDate.now().plusDays(2), LocalDate.now(),LocalDateTime.now(), advertise, null );
        Batch b2b = new Batch(2L, 1, 1, 1F, 1F,
                LocalDate.now().plusDays(2), LocalDate.now(),LocalDateTime.now(), advertise2, null );
        Batch b3 = new Batch(3L, 1, 1, 1F, 1F,
                LocalDate.now().plusDays(5), LocalDate.now(),LocalDateTime.now(), advertise, null );
        List<Batch> list1 = new ArrayList<>();
        List<Batch> lis2 = new ArrayList<>();
        list1.add(b1);
        list1.add(b2);
        lis2.add(b2b);
        lis2.add(b3);
        Section section = new Section(1L, null, "nameSetor", RefrigerationType.COLD,
                1, 1, 1F, 1F ,null );
        InboundOrder inboundOrder = new InboundOrder();
        inboundOrder.setSection(section);
        inboundOrder.setBatchList(list1);

        SectionRepository repositoryMock = Mockito.mock(SectionRepository.class);

        SectionService sectionService = new SectionService(repositoryMock);

        BusinessException expectedException = assertThrows(BusinessException.class, () ->
                sectionService.updateOldSectionStock(inboundOrder,lis2));

        assertTrue(expectedException.getMessage().contains("Cannot change Advertise of a Batch"));
    }

    @Test
    public void validaEspacoDoEstoqueDoSetor() {
        //Assert
        Advertise advertise = new Advertise();
        advertise.setId(1L);
        Batch b1 = new Batch(1L, 1, 1, 1F, 1F,
                LocalDate.now().plusDays(7), LocalDate.now().plusDays(1),LocalDateTime.now(), advertise, null );
        Batch b2 = new Batch(2L, 1, 1, 1F, 1F,
                LocalDate.now().plusDays(2), LocalDate.now(),LocalDateTime.now(), advertise, null );
        Batch b2b = new Batch(2L, 1, 1, 1F, 1F,
                LocalDate.now().plusDays(2), LocalDate.now(),LocalDateTime.now(), advertise, null );
        Batch b3 = new Batch(3L, 1, 1, 1F, 1F,
                LocalDate.now().plusDays(5), LocalDate.now(),LocalDateTime.now(), advertise, null );
        List<Batch> list1 = new ArrayList<>();
        List<Batch> lis2 = new ArrayList<>();
        list1.add(b1);
        list1.add(b2);
        lis2.add(b2b);
        lis2.add(b3);
        Section section = new Section(1L, null, "nameSetor", RefrigerationType.COLD,
                1, 1, 1F, 1F ,null );
        InboundOrder inboundOrder = new InboundOrder();
        inboundOrder.setSection(section);
        inboundOrder.setBatchList(list1);

        SectionRepository repositoryMock = Mockito.mock(SectionRepository.class);

        SectionService sectionService = new SectionService(repositoryMock);

        List<Batch> listaRetornada = sectionService.updateOldSectionStock(inboundOrder,lis2);
        list1.add(b3);

        assertEquals(list1,listaRetornada);
    }
}