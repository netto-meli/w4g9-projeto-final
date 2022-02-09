package com.mercadolibre.w4g9projetofinal.test.unit;

import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.service.SectionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class SectionServiceTest {

    @Mock
    private SectionService sectionServiceMock;

    @Test
    void verificaSeSectionEstaSendoGravada() {
        Section section = Mockito.mock(Section.class);
        section.setId(1L);
        Mockito.when(sectionServiceMock.findById(1L)).thenReturn(section);
        Section sectionResult = sectionServiceMock.findById(1L);
        sectionResult.setId(588L);
        Mockito.when(sectionServiceMock.save(sectionResult)).thenReturn(section);
        Section sectionTest =sectionServiceMock.save(section);
        Assertions.assertNotNull(sectionTest);
    }

    @Test
    void verificaSeSectionEstaSendoAtualizada() {
        Section section = new Section();
        section.setId(1L);
        section.setName("mechanic");
        //Insere Section
        Mockito.when(sectionServiceMock.findById(1L)).thenReturn(section);
        Section sectionResult = sectionServiceMock.findById(1L);
        //Atualizo o nome
        sectionResult.setName("Teste 316");
        Mockito.when(sectionServiceMock.save(sectionResult)).thenReturn(section);
        Section sectionTest = sectionServiceMock.save(sectionResult);
        Mockito.when(sectionServiceMock.update(section)).thenReturn(section);
        sectionServiceMock.update(section);
        //Verifico se o nome foi atualizado.
        Assertions.assertEquals("Teste 316", sectionTest.getName());
        sectionServiceMock.delete(1L);
    }

    @Test
    void verificaSeSectionEstaSendoDeletada() {
        Section section = new Section();
        section.setId(1L);
        Mockito.doNothing().when(sectionServiceMock).delete(section.getId());
        sectionServiceMock.delete(1L);
        Mockito.verify(sectionServiceMock).delete(section.getId());
    }

    @Test
    void verificaRetornoDeListaDeSections() {
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
        Mockito.when(sectionServiceMock.findAll()).thenReturn(sectionList);
        List<Section> result = sectionServiceMock.findAll();
        Assertions.assertNotNull(result.get(0));
    }

    @Test
    void verificaORetornoDeUmaSectionPeloId() {
        Section section = new Section();
        section.setId(1L);
        Mockito.when(sectionServiceMock.findById(1L)).thenReturn(section);
        Section sectionResult = sectionServiceMock.findById(1L);
        Assertions.assertNotNull(section);
        Assertions.assertEquals(1L, sectionResult.getId());
    }
}