package com.mercadolibre.w4g9projetofinal.test.unit;

import com.mercadolibre.w4g9projetofinal.entity.Advertise;
import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.exceptions.ExistingUserException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.SellerRepository;
import com.mercadolibre.w4g9projetofinal.service.AdvertiseService;
import com.mercadolibre.w4g9projetofinal.service.SellerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class SellerServiceTest {

    /*** <b>US-0001</b>
     * Verifica a lista de Sellers
     */
    @Test
    public void verificaListaDeSellersCadastrados() {

        //arrrange
        Seller s1 = new Seller(1l, "vrs_marcos", "Marcos Sá", "email1@hotmail.com","123456", null);
        Seller s2 = new Seller(2L, "felipe.133", "Felipe Bontempo", "email2@hotmail.com", "123456", null);
        Seller s3 = new Seller(3L, "felipe.13iu34", "Fernando Netto", "email3@hotmail.com", "123456", null);
        Seller s4 = new Seller(5L, "felipe.1338", "Leonardo", "email4@hotmail.com", "123456", null);
        Seller s5 = new Seller(6L, "felipe.13iuii34", "Rafael Menezes", "email5@hotmail.com", "123456", null);
        List<Seller> list = new ArrayList<>(Arrays.asList(s1, s2, s3, s4, s5));

        SellerRepository mockSellerRepository = Mockito.mock(SellerRepository.class);
        Mockito.when(mockSellerRepository.findAll()).thenReturn(list);

        SellerService sellerService = new SellerService(null, mockSellerRepository, null);

        //Action
        List<Seller> listSeller = sellerService.findAll();

        //Assertion
        assertEquals(list, listSeller);
    }

    /*** <b>US-0002</b>
     * Verifica a Seller por Id
     * <i>Se cumprir:</i><br>
     * Permite continuar normalmente. <br>
     * <i>Se não cumprir:</i>
     * Retorna excessão ObjectNotFoundException
     */
    @Test
    public void verificaBuscaPorId() {

        //arrange
        Seller s1 = new Seller(3L, "felipe3", "Fe Bontempo", "email2@hotmail.com", "123456", null);
        Seller s2 = new Seller(2L, "felipe.133", "Felipe Bontempo", "email2@hotmail.com", "123456", null);

        SellerRepository mockSellerRepository = Mockito.mock(SellerRepository.class);
        Mockito.when(mockSellerRepository.findById(2L)).thenReturn(java.util.Optional.of(s2));

        SellerService sellerService = new SellerService(null, mockSellerRepository, null);

        //Action
        Seller seller = sellerService.findById(2L);

        ObjectNotFoundException excesaoEsperada = assertThrows(ObjectNotFoundException.class,() -> sellerService.findById(1L));

        //Assertation
        assertEquals(s2, seller);
        assertTrue(excesaoEsperada.getMessage().contains("Vendedor não encontrado! Por favor verifique o id."));
    }

    /*** <b>US-0003</b>
     * Verifica a Insert de Seller
     * <i>Se cumprir:</i><br>
     * Permite continuar normalmente. <br>
     * <i>Se não cumprir:</i>
     * Retorna excessão ExistingUserException
     */
    @Test
    public void verificaInsertSeller() {
        //arrange
        Seller s1 = new Seller(3L, "felipe3", "Fe Bontempo", "email2@hotmail.com", "123456", null);
        Seller s2 = new Seller(2L, "felipe.133", "Felipe Bontempo", "email2@hotmail.com", "123456", null);

        SellerRepository mockSellerRepository = Mockito.mock(SellerRepository.class);
        Mockito.when(mockSellerRepository.save(s2)).thenReturn(s2);
        Mockito.when(mockSellerRepository.save(s1)).thenThrow(DataIntegrityViolationException.class);

        SellerService sellerService = new SellerService(new BCryptPasswordEncoder(), mockSellerRepository, null);

        //Action
        Seller seller = sellerService.insert(s2);

        ExistingUserException expectedException = assertThrows(ExistingUserException.class, () -> sellerService.insert(s1));

        //Assertation
        assertEquals(s2, seller);
        assertTrue(expectedException.getMessage().contains("Username ou Email existente na base de dados"));
    }

    /*** <b>US-0004</b>
     * Verifica a Update de Seller
     * <i>Se cumprir:</i><br>
     * Permite continuar normalmente. <br>
     * <i>Se não cumprir:</i>
     * Retorna excessão ObjectNotFoundException
     */
    @Test
    public void verificaUpdateSeller() {

        //Arrange
        Seller s1 = new Seller(3L, "felipe3", "Fe Bontempo", "email2@hotmail.com", "123456", null);
        Seller s2 = new Seller(2L, "felipe.133", "Felipe Bontempo", "email2@hotmail.com", "123456", null);

        Seller sellerUpdate = new Seller(s1.getId(), s1.getUsername(), "Marcos de Sá", s1.getEmail(), s1.getPassword(), null);

        SellerService mockSellerService = Mockito.mock(SellerService.class);
        Mockito.when(mockSellerService.update(sellerUpdate)).thenReturn(sellerUpdate);

        SellerRepository mockSellerRepository = Mockito.mock(SellerRepository.class);
        Mockito.when(mockSellerRepository.save(sellerUpdate)).thenReturn(sellerUpdate);
        Mockito.when(mockSellerRepository.findById(3L)).thenReturn(java.util.Optional.of(s1));

        SellerService sellerService = new SellerService(new BCryptPasswordEncoder(), mockSellerRepository, null);

        //Action
        Seller seller = sellerService.update(sellerUpdate);
        ObjectNotFoundException excesaoEsperada = assertThrows(ObjectNotFoundException.class,() -> sellerService.findById(1L));

        //Action
        assertEquals(sellerUpdate, seller);
        assertTrue(excesaoEsperada.getMessage().contains("Vendedor não encontrado! Por favor verifique o id."));
    }

    /*** <b>US-0004</b>
     * Verifica a Delete de Seller
     * <i>Se cumprir:</i><br>
     * Permite continuar normalmente. <br>
     * <i>Se não cumprir:</i>
     * Retorna excessão ObjectNotFoundException
     */
    @Test
    public void verificaDeleteSeller() {
        //Arrange
        Seller s1 = new Seller(3L, "felipe3", "Fe Bontempo", "email2@hotmail.com", "123456", null);

        SellerRepository mockSellerRepository = Mockito.mock(SellerRepository.class);
        Mockito.when(mockSellerRepository.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(mockSellerRepository.findById(3L)).thenReturn(Optional.of(s1));
        Mockito.doNothing().when(mockSellerRepository).delete(s1);

        SellerService sellerService = new SellerService(new BCryptPasswordEncoder(), mockSellerRepository, null);

        //Action
        sellerService.delete(3L);
        ObjectNotFoundException excesaoEsperada = assertThrows(ObjectNotFoundException.class,() -> sellerService.delete(1L));

        //Assertation
        Mockito.verify(mockSellerRepository, Mockito.times(1)).delete(s1);
        assertTrue(excesaoEsperada.getMessage().contains("Vendedor não encontrado! Por favor verifique o id."));
    }

    /*** <b>US-0005</b>
     * Verifica se todos os lotes tem o mesmo vendedor
     * <i>Se cumprir:</i><br>
     * Permite continuar normalmente. <br>
     * <i>Se não cumprir:</i>
     * Retorna excessão ObjectNotFoundException
     */
    @Test
    public void verificaLoteMesmoVendedor() {
        //Arrange
        Seller s1 = new Seller(1L, "felipe1", "Fe Bontempo", "email2@hotmail.com", "123456", null);
        Seller s2 = new Seller(2L, "felipe2", "Fe Bontempo", "email2@hotmail.com", "123456", null);
        Seller s3 = new Seller(15L, "felipe3", "Fe Bontempo", "email2@hotmail.com", "123456", null);

        Advertise a1 = new Advertise(1L, null, null, s1, null, null, true);
        Advertise a2 = new Advertise(1L, null, null, s3, null, null, true);
        Advertise a3 = new Advertise(5L, null, null, null, null, null, true);

        Batch b1 = new Batch(1L, 0, 15, 0, 0, null, null, null, a1, null);
        Batch b2 = new Batch(2L, 0, 15, 0, 0, null, null, null, a3, null);
        Batch b3 = new Batch(3L, 0, 15, 0, 0, null, null, null, a2, null);
        List<Batch> list = new ArrayList<>(Arrays.asList(b1));
        List<Batch> list2 = new ArrayList<>(Arrays.asList(b2));

        SellerRepository mockSellerRepository = Mockito.mock(SellerRepository.class);
        Mockito.when(mockSellerRepository.findSellerByAdvertiseId(1L)).thenReturn(Optional.of(a1.getId()));
        Mockito.when(mockSellerRepository.findSellerByAdvertiseId(null)).thenThrow(ObjectNotFoundException.class);

        AdvertiseService mockAdvertiseService = Mockito.mock(AdvertiseService.class);
        Mockito.when(mockAdvertiseService.findById(b1.getAdvertise().getId())).thenReturn(a1);

        SellerService sellerService = new SellerService(new BCryptPasswordEncoder(), mockSellerRepository, mockAdvertiseService);

        //Act
        Seller seller = sellerService.verifySellerInInboundOrder(list);
        ObjectNotFoundException excesaoEsperada = assertThrows(ObjectNotFoundException.class,() -> sellerService.verifySellerInInboundOrder(list2));

        //Assertation
        assertEquals(s1, seller);
        assertTrue(excesaoEsperada.getMessage().contains("Seller not found"));


    }


}
