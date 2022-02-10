package com.mercadolibre.w4g9projetofinal.test.unit;

import com.mercadolibre.w4g9projetofinal.entity.Buyer;
import com.mercadolibre.w4g9projetofinal.exceptions.ExistingUserException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.repository.BuyerRepository;
import com.mercadolibre.w4g9projetofinal.service.BuyerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/***
 * @author Leonardo
 */
public class BuyerServiceTest {

    @Test
    public void verifyAllBuyer() {

        Buyer buyer = new Buyer(2L, "Test", "Buyer", "leota@gmail.com", "end","rua");
        Buyer buyer2 = new Buyer(4L, "Test2", "Buyer2", "leota@gmail.com", "end2","ruo");
        Buyer buyer3 = new Buyer(5L, "Test2", "Buyer2", "leota@gmail.com", "end2","ruo");
        Buyer buyer4 = new Buyer(6L, "Test2", "Buyer2", "leota@gmail.com", "end2","ruo");
        List<Buyer> listBuyer = new ArrayList<>(Arrays.asList(buyer, buyer2, buyer3, buyer4));

        BuyerRepository mockBuyerRepository = Mockito.mock(BuyerRepository.class);
        Mockito.when(mockBuyerRepository.findAll()).thenReturn(listBuyer);

        BuyerService buyerService = new BuyerService(null, mockBuyerRepository);
        List<Buyer> buyerFind = buyerService.findAll();

        assertEquals(listBuyer, buyerFind);
        assertNotNull(buyerService);
    }

   @Test
    public void verifyBuyerById() {
       Buyer buyer = new Buyer(2L, "Test", "Buyer", "leota@gmail.com", "end","rua");

        BuyerRepository mockBuyerRepository = Mockito.mock(BuyerRepository.class);
        Mockito.when(mockBuyerRepository.findById(2L)).thenReturn(java.util.Optional.of(buyer));

        BuyerService buyerService = new BuyerService(null, mockBuyerRepository);

        Buyer buyerFind = buyerService.findById(2L);
        ObjectNotFoundException ex = assertThrows(ObjectNotFoundException.class,() -> buyerService.findById(1L));

        assertEquals(buyerFind, buyer);
        assertTrue(ex.getMessage().contains("Comprador não encontrado! Por favor verifique dados informados."));
    }

    @Test
    public void insertBuyer(){

        Buyer buyer = new Buyer(2L, "Test", "Buyer", "leota@gmail.com", "end","rua");
        Buyer buyer2 = new Buyer(4L, "Test2", "Buyer2", "leota@gmail.com", "end2","ruo");

        BuyerRepository mockBuyerRepository = Mockito.mock(BuyerRepository.class);
        Mockito.when(mockBuyerRepository.save(buyer)).thenReturn(buyer);
        Mockito.when(mockBuyerRepository.save(buyer2)).thenThrow(DataIntegrityViolationException.class);

        BuyerService buyerService = new BuyerService(new BCryptPasswordEncoder(), mockBuyerRepository);
        Buyer buyerFind = buyerService.insert(buyer);

        ExistingUserException expectedException = assertThrows(ExistingUserException.class, () -> buyerService.insert(buyer2));

        assertEquals(buyerFind,buyer);
        assertTrue(expectedException.getMessage().contains("Username ou Email existente na base de dados"));

    }

    @Test
    public void buyerUpdate(){
        Buyer buyer1 = new Buyer(2L, "Test", "Buyer", "leota@gmail.com", "end","rua");

        BuyerRepository mockBuyerRepository = Mockito.mock(BuyerRepository.class);
        Mockito.when(mockBuyerRepository.save(buyer1)).thenReturn(buyer1);

        Mockito.when(mockBuyerRepository.findById(2L)).thenReturn(java.util.Optional.of(buyer1));
        BuyerService buyerService = new BuyerService(new BCryptPasswordEncoder(), mockBuyerRepository);

        Buyer buyer = buyerService.update(buyer1);
        ObjectNotFoundException ex = assertThrows(ObjectNotFoundException.class,() -> buyerService.findById(1L));

        assertEquals(buyer1, buyer);
        assertNotNull(buyer);
        assertTrue(ex.getMessage().contains("Comprador não encontrado! Por favor verifique dados informados."));
    }

    @Test
    public void DeleteBuyer() {
        Buyer buyer = new Buyer(2L, "Test", "Buyer", "leota@gmail.com", "end","rua");

        BuyerRepository mockBuyerRepository = Mockito.mock(BuyerRepository.class);
        Mockito.when(mockBuyerRepository.save(buyer)).thenReturn(buyer);
        Mockito.doNothing().when(mockBuyerRepository).delete(buyer);
        Mockito.when(mockBuyerRepository.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(mockBuyerRepository.findById(2L)).thenReturn(Optional.of(buyer));

        BuyerService buyerService = new BuyerService(new BCryptPasswordEncoder(), mockBuyerRepository);

        buyerService.delete(2L);
        ObjectNotFoundException ex = assertThrows(ObjectNotFoundException.class,() -> buyerService.findById(1L));

        Mockito.verify(mockBuyerRepository, Mockito.times(1)).delete(buyer);
        assertTrue(ex.getMessage().contains("Comprador não encontrado! Por favor verifique dados informados."));
    }

}
