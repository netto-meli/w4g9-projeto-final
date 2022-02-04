package com.mercadolibre.w4g9projetofinal.test.unit;

import com.mercadolibre.w4g9projetofinal.entity.Buyer;
import com.mercadolibre.w4g9projetofinal.repository.BuyerRepository;
import com.mercadolibre.w4g9projetofinal.service.BuyerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyerServiceTest {

    @Test
    public void verifyBuyerList() {

        Buyer buyer = new Buyer(2L, "Test", "Buyer", "leota@gmail.com", "end","rua");
        Buyer buyer2 = new Buyer(4L, "Test2", "Buyer2", "leota@gmail.com", "end2","ruo");

        BuyerRepository mockBuyerRepository = Mockito.mock(BuyerRepository.class);
        Mockito.when(mockBuyerRepository.findById(4L)).thenReturn(Optional.of(buyer2));

        BuyerService buyerService = new BuyerService(null, mockBuyerRepository);

        Buyer buyerFind = (Buyer) buyerService.findAll();

        assertEquals(buyer, buyerFind);
    }




}
