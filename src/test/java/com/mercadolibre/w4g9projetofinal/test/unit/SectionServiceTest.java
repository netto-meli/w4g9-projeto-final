package com.mercadolibre.w4g9projetofinal.test.unit;

import com.mercadolibre.w4g9projetofinal.entity.InboundOrder;
import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.SectionRepository;
import com.mercadolibre.w4g9projetofinal.service.SectionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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

        //Vai tentar deletar uma Section que nao existe e vai lançar uma exception
        Mockito.doThrow(new ObjectNotFoundException(null))
                .when(repositoryMock).deleteById(Mockito.anyLong());
        //Tente deletar uma Section qualquer
        try {
            sectionService.delete(Mockito.anyLong());
            //Verifico se ouve interação com o repositoryMock.deleteById, se houve então tudo funcionando!
            Mockito.verify(repositoryMock).deleteById(Mockito.anyLong());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    void sectionfindByInboundOrderId(){
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

        ObjectNotFoundException expectedException = assertThrows(ObjectNotFoundException.class, () -> sectionService.findByInboundOrderId(3L));

        assertTrue(expectedException.getMessage().contains("Setor nao encontrado através do ID da Inbound Order"));
        Assertions.assertEquals(sectionResult,section);
    }

}