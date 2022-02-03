package com.mercadolibre.w4g9projetofinal.test.unit;

import com.mercadolibre.w4g9projetofinal.entity.Representative;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.entity.enums.RepresentativeJob;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.RepresentativeRepository;
import com.mercadolibre.w4g9projetofinal.repository.SellerRepository;
import com.mercadolibre.w4g9projetofinal.service.RepresentativeService;
import com.mercadolibre.w4g9projetofinal.service.SellerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RepresentativeServiceTest {

    @Test
    public void verificaListaDeRepresentativesCadastrados() {

        //arrrange
        Representative r1 = new Representative(null, "felipe.13sd3", "Marcos Sá", "email1@gmail.com", "151515", RepresentativeJob.LIDER);
        Representative r2 = new Representative(null, "felipe.13dsd3", "Marcos Sá", "email2@gmail.com", "151515", RepresentativeJob.SUPERVISOR);
        Representative r3 = new Representative(null, "felipe.1efe33", "Marcos Sá", "email3@gmail.com", "151515", RepresentativeJob.LIDER);
        Representative r4 = new Representative(null, "felipe.1ww33", "Marcos Sá", "email4@gmail.com", "151515", RepresentativeJob.SUPERVISOR);
        Representative r5 = new Representative(null, "felipe.13fdf3", "Marcos Sá", "email5@gmail.com", "151515", RepresentativeJob.LIDER);
        List<Representative> list = new ArrayList<>(Arrays.asList(r1, r2, r3, r4, r5));

        RepresentativeRepository mockRepresentativeRepository = Mockito.mock(RepresentativeRepository.class);
        Mockito.when(mockRepresentativeRepository.findAll()).thenReturn(list);

        RepresentativeService representativeService = new RepresentativeService(null, mockRepresentativeRepository);

        //Action
        List<Representative> listRepresentative = representativeService.findAll();

        //Assertion
        assertEquals(list, listRepresentative);


    }

    @Test
    public void verificaBuscaPorId() {

        //arrange
        Representative r1 = new Representative(1L, "felipe.13sd3", "Marcos Sá", "email1@gmail.com", "151515", RepresentativeJob.LIDER);
        Representative r2 = new Representative(2L, "felipe.13dsd3", "Marcos Sá", "email2@gmail.com", "151515", RepresentativeJob.SUPERVISOR);

        RepresentativeRepository mockRepresentativeRepository = Mockito.mock(RepresentativeRepository.class);
        Mockito.when(mockRepresentativeRepository.findById(2L)).thenReturn(java.util.Optional.of(new Representative(2L, "felipe.13dsd3", "Marcos Sá", "email2@gmail.com", "151515", RepresentativeJob.SUPERVISOR)));

        RepresentativeService representativeService = new RepresentativeService(null, mockRepresentativeRepository);

        //Action
        Representative representative = representativeService.findById(2L);

        ObjectNotFoundException excesaoEsperada = assertThrows(ObjectNotFoundException.class,() -> representativeService.findById(1L));

        //Assertation
        assertEquals(r2, representative);
        assertTrue(excesaoEsperada.getMessage().equals("Representante não encontrado! Por favor verifique o id."));
    }


}
