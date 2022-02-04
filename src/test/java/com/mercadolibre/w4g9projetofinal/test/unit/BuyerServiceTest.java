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
 * @autor Leonardo
 */
public class BuyerServiceTest {

    @Test
    public void verifyAllBuyer() {
        BuyerRepository mockBuyerRepository = Mockito.mock(BuyerRepository.class);
        Mockito.when(mockBuyerRepository.findAll()).thenReturn(mockBuyer());

        BuyerService buyerService = new BuyerService(null, mockBuyerRepository);
        List<Buyer> buyerFind = buyerService.findAll();

        assertEquals(mockBuyer(), buyerFind);
        assertNotNull(buyerService);
    }

    @Test
    public void verifyBuyerById() {
        BuyerRepository mockBuyerRepository = Mockito.mock(BuyerRepository.class);
        Mockito.when(mockBuyerRepository.findById(2L)).thenReturn(java.util.Optional.of(mockBuyer().get(0)));

        BuyerService buyerService = new BuyerService(null, mockBuyerRepository);

        Buyer buyerFind = buyerService.findById(2L);
        ObjectNotFoundException ex = assertThrows(ObjectNotFoundException.class,() -> buyerService.findById(1L));

        assertEquals(buyerFind, mockBuyer().get(0));
        assertTrue(ex.getMessage().contains("Comprador não encontrado! Por favor verifique dados informados."));
    }

    @Test
    public void insertBuyer(){
        BuyerRepository mockBuyerRepository = Mockito.mock(BuyerRepository.class);
        Mockito.when(mockBuyerRepository.save(mockBuyer().get(0))).thenReturn(mockBuyer().get(0));
        Mockito.when(mockBuyerRepository.save(mockBuyer().get(1))).thenThrow(DataIntegrityViolationException.class);

        BuyerService buyerService = new BuyerService(new BCryptPasswordEncoder(), mockBuyerRepository);
        Buyer buyerFind = buyerService.insert(mockBuyer().get(0));

        ExistingUserException expectedException = assertThrows(ExistingUserException.class, () -> buyerService.insert(mockBuyer().get(1)));

        assertEquals(buyerFind, mockBuyer().get(0));
        assertTrue(expectedException.getMessage().contains("Username ou Email existente na base de dados"));

    }

    @Test
    public void buyerUpdate(){
        Buyer buyerUpdate = mockBuyer().get(0);

        BuyerRepository mockBuyerRepository = Mockito.mock(BuyerRepository.class);
        Mockito.when(mockBuyerRepository.save(buyerUpdate)).thenReturn(buyerUpdate);

        Mockito.when(mockBuyerRepository.findById(2L)).thenReturn(java.util.Optional.of(mockBuyer().get(0)));
        BuyerService buyerService = new BuyerService(new BCryptPasswordEncoder(), mockBuyerRepository);

        Buyer buyer = buyerService.update(buyerUpdate);
        ObjectNotFoundException ex = assertThrows(ObjectNotFoundException.class,() -> buyerService.findById(1L));

        assertEquals(buyerUpdate, buyer);
        assertNotNull(buyer);
        assertTrue(ex.getMessage().contains("Comprador não encontrado! Por favor verifique dados informados."));
    }

    @Test
    public void DeleteBuyer() {
        BuyerRepository mockBuyerRepository = Mockito.mock(BuyerRepository.class);
        Mockito.when(mockBuyerRepository.save(mockBuyer().get(0))).thenReturn(mockBuyer().get(0));
        Mockito.doNothing().when(mockBuyerRepository).delete(mockBuyer().get(0));
        Mockito.when(mockBuyerRepository.findById(1L)).thenReturn(Optional.empty());
        Mockito.when(mockBuyerRepository.findById(2L)).thenReturn(Optional.ofNullable(mockBuyer().get(0)));

        BuyerService buyerService = new BuyerService(new BCryptPasswordEncoder(), mockBuyerRepository);

        buyerService.delete(2L);
        ObjectNotFoundException ex = assertThrows(ObjectNotFoundException.class,() -> buyerService.findById(1L));

        Mockito.verify(mockBuyerRepository, Mockito.times(1)).delete(mockBuyer().get(0));
        assertTrue(ex.getMessage().contains("Comprador não encontrado! Por favor verifique dados informados."));
    }


    public List<Buyer> mockBuyer(){
        Buyer buyer = new Buyer(2L, "Test", "Buyer", "leota@gmail.com", "end","rua");
        Buyer buyer2 = new Buyer(4L, "Test2", "Buyer2", "leota@gmail.com", "end2","ruo");
        Buyer buyer3 = new Buyer(5L, "Test2", "Buyer2", "leota@gmail.com", "end2","ruo");
        Buyer buyer4 = new Buyer(6L, "Test2", "Buyer2", "leota@gmail.com", "end2","ruo");
        List<Buyer> list = new ArrayList<>(Arrays.asList(buyer, buyer2, buyer3, buyer4));
        return list;
    }

}
