package com.mercadolibre.w4g9projetofinal.test.unit;

import com.mercadolibre.w4g9projetofinal.entity.Buyer;
import com.mercadolibre.w4g9projetofinal.repository.BuyerRepository;
import com.mercadolibre.w4g9projetofinal.service.BuyerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BuyerServiceTest {

    @Test
    public void verifyBuyerList() {
        List<Buyer> b = new ArrayList<>();

        Buyer buyer = new Buyer(2L, "Test", "Buyer", "leota@gmail.com", "end","rua");
        Buyer buyer2 = new Buyer(4L, "Test2", "Buyer2", "leota@gmail.com", "end2","ruo");

        b.add(buyer);
        b.add(buyer2);

        BuyerRepository mockBuyerRepository = Mockito.mock(BuyerRepository.class);
        Mockito.when(mockBuyerRepository.findAll()).thenReturn(b);

        BuyerService buyerService = new BuyerService(null, mockBuyerRepository);

        List<Buyer> buyerFind = buyerService.findAll();

        assertEquals(b, buyerFind);
        assertNotNull(buyerService);
    }




}
