package com.mercadolibre.w4g9projetofinal.test.unit;

import com.mercadolibre.w4g9projetofinal.entity.Representative;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;
import com.mercadolibre.w4g9projetofinal.entity.enums.RepresentativeJob;
import com.mercadolibre.w4g9projetofinal.exceptions.ExistingUserException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.RepresentativeRepository;
import com.mercadolibre.w4g9projetofinal.service.RepresentativeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RepresentativeServiceTest {

    /*** <b>US-0001</b>
     * Verifica a lista de Representatives
     */
    @Test
    public void verificaListaDeRepresentativesCadastrados() {

        //arrrange
        Warehouse warehouse = new Warehouse(null, "warehouse1", "DF");

        Representative r1 = new Representative(null, "felipe.13sd3", "Marcos Sá", "email1@gmail.com", "151515", RepresentativeJob.LIDER, warehouse);
        Representative r2 = new Representative(null, "felipe.13dsd3", "Marcos Sá", "email2@gmail.com", "151515", RepresentativeJob.SUPERVISOR, warehouse);
        Representative r3 = new Representative(null, "felipe.1efe33", "Marcos Sá", "email3@gmail.com", "151515", RepresentativeJob.LIDER, warehouse );
        Representative r4 = new Representative(null, "felipe.1ww33", "Marcos Sá", "email4@gmail.com", "151515", RepresentativeJob.SUPERVISOR, warehouse );
        Representative r5 = new Representative(null, "felipe.13fdf3", "Marcos Sá", "email5@gmail.com", "151515", RepresentativeJob.LIDER, warehouse );
        List<Representative> list = new ArrayList<>(Arrays.asList(r1, r2, r3, r4, r5));

        RepresentativeRepository mockRepresentativeRepository = Mockito.mock(RepresentativeRepository.class);
        Mockito.when(mockRepresentativeRepository.findAll()).thenReturn(list);

        RepresentativeService representativeService = new RepresentativeService(null, mockRepresentativeRepository);

        //Action
        List<Representative> listRepresentative = representativeService.findAll();

        //Assertion
        assertEquals(list, listRepresentative);


    }

    /*** <b>US-0002</b>
     * Verifica Representative por Id
     * <i>Se cumprir:</i><br>
     * Permite continuar normalmente. <br>
     * <i>Se não cumprir:</i>
     * Retorna excessão ObjectNotFoundException
     */
    @Test
    public void verificaBuscaPorId() {

        //arrange
        Warehouse warehouse = new Warehouse(null, "warehouse1", "DF");

        Representative r1 = new Representative(1L, "felipe.13sd3", "Marcos Sá", "email1@gmail.com", "151515", RepresentativeJob.LIDER, warehouse);
        Representative r2 = new Representative(2L, "felipe.13dsd3", "Marcos Sá", "email2@gmail.com", "151515", RepresentativeJob.SUPERVISOR, warehouse);

        RepresentativeRepository mockRepresentativeRepository = Mockito.mock(RepresentativeRepository.class);
        Mockito.when(mockRepresentativeRepository.findById(2L)).thenReturn(java.util.Optional.of(new Representative(2L, "felipe.13dsd3", "Marcos Sá", "email2@gmail.com", "151515", RepresentativeJob.SUPERVISOR, null  )));

        RepresentativeService representativeService = new RepresentativeService(null, mockRepresentativeRepository);

        //Action
        Representative representative = representativeService.findById(2L);

        ObjectNotFoundException excesaoEsperada = assertThrows(ObjectNotFoundException.class,() -> representativeService.findById(1L));

        //Assertation
        assertEquals(r2, representative);
        assertTrue(excesaoEsperada.getMessage().equals("Representante não encontrado! Por favor verifique o id."));
    }

    /*** <b>US-0003</b>
     * Verifica a Insert de Representative
     * <i>Se cumprir:</i><br>
     * Permite continuar normalmente. <br>
     * <i>Se não cumprir:</i>
     * Retorna excessão ExistingUserException
     */
    @Test
    public void verificaInsertRepresentative() {
        //arrange
        Warehouse warehouse = new Warehouse(null, "warehouse1", "DF");

        Representative r1 = new Representative(1L, "felipe.13sd3", "Marcos Sá", "email1@gmail.com", "151515", RepresentativeJob.LIDER, warehouse );
        Representative r2 = new Representative(2L, "felipe.13dsd3", "Marcos Sá", "email2@gmail.com", "151515", RepresentativeJob.SUPERVISOR, warehouse );

        RepresentativeRepository mockRepresentativeRepository = Mockito.mock(RepresentativeRepository.class);
        Mockito.when(mockRepresentativeRepository.save(r2)).thenReturn(r2);
        Mockito.when(mockRepresentativeRepository.save(r1)).thenThrow(DataIntegrityViolationException.class);

        RepresentativeService representativeService = new RepresentativeService(new BCryptPasswordEncoder(), mockRepresentativeRepository);

        //Action
        Representative representative = representativeService.insert(r2);

        ExistingUserException expectedException = assertThrows(ExistingUserException.class, () -> representativeService.insert(r1));

        //Assertation
        assertEquals(r2, representative);
        assertTrue(expectedException.getMessage().equals("Username ou Email existente na base de dados, ou Armazém não existe"));
    }

    /*** <b>US-0004</b>
     * Verifica a Update de Representative
     * <i>Se cumprir:</i><br>
     * Permite continuar normalmente. <br>
     * <i>Se não cumprir:</i>
     * Retorna excessão ObjectNotFoundException
     */
    @Test
    public void verificaUpdateRepresentative() {

        //Arrange
        Warehouse warehouse = new Warehouse(null, "warehouse1", "DF");

        Representative r1 = new Representative(1L, "felipe.13sd3", "Marcos Sá", "email1@gmail.com", "151515", RepresentativeJob.LIDER, warehouse );
        Representative r2 = new Representative(2L, "felipe.13dsd3", "Marcos Sá", "email2@gmail.com", "151515", RepresentativeJob.SUPERVISOR, warehouse );

        Representative representativeUpdate = new Representative(r2.getId(), r2.getUsername(), "Marcos de Sá", r2.getEmail(), r2.getPassword(), r2.getJob(), r2.getWarehouse() );

        RepresentativeService mockRepresentativeService = Mockito.mock(RepresentativeService.class);
        Mockito.when(mockRepresentativeService.update(representativeUpdate)).thenReturn(representativeUpdate);

        RepresentativeRepository mockRepresentativeRepository = Mockito.mock(RepresentativeRepository.class);
        Mockito.when(mockRepresentativeRepository.save(representativeUpdate)).thenReturn(representativeUpdate);
        Mockito.when(mockRepresentativeRepository.findById(2L)).thenReturn(java.util.Optional.of(r2));

        RepresentativeService representativeService = new RepresentativeService(new BCryptPasswordEncoder(), mockRepresentativeRepository);

        //Action
        Representative representative = representativeService.update(representativeUpdate);
        ObjectNotFoundException excesaoEsperada = assertThrows(ObjectNotFoundException.class,() -> representativeService.findById(3L));

        //Action


        assertEquals(representativeUpdate, representative);
        assertTrue(excesaoEsperada.getMessage().equals("Representante não encontrado! Por favor verifique o id."));
    }

    /*** <b>US-0004</b>
     * Verifica a Delete de Representative
     * <i>Se cumprir:</i><br>
     * Permite continuar normalmente. <br>
     * <i>Se não cumprir:</i>
     * Retorna excessão ObjectNotFoundException
     */
    @Test
    public void verificaDeleteSeller() {
        //Arrange
        Warehouse warehouse = new Warehouse(null, "warehouse1", "DF");

        Representative r1 = new Representative(1L, "felipe.13sd3", "Marcos Sá", "email1@gmail.com", "151515", RepresentativeJob.LIDER, warehouse );

        RepresentativeRepository mockRepresentativeRepository = Mockito.mock(RepresentativeRepository.class);
        Mockito.when(mockRepresentativeRepository.findById(2L)).thenReturn(Optional.empty());
        Mockito.when(mockRepresentativeRepository.findById(1L)).thenReturn(Optional.of(r1));
        Mockito.doNothing().when(mockRepresentativeRepository).delete(r1);

        RepresentativeService representativeService = new RepresentativeService(new BCryptPasswordEncoder(), mockRepresentativeRepository);

        //Action
        representativeService.delete(1L);
        ObjectNotFoundException excesaoEsperada = assertThrows(ObjectNotFoundException.class,() -> representativeService.delete(2L));

        //Assertation
        Mockito.verify(mockRepresentativeRepository, Mockito.times(1)).delete(r1);
        assertTrue(excesaoEsperada.getMessage().contains("Representante não encontrado! Por favor verifique o id."));
    }
}
