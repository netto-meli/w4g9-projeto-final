package com.mercadolibre.w4g9projetofinal.test.unit;

import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.service.SellerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SellerServiceTest {

    @Test
    public void verificaListaDeSellersCadastrados() {

        //arrrange
        Seller s1 = new Seller(1l, "vrs_marcos", "Marcos Sá", "email1@hotmail.com","123456", null);
        Seller s2 = new Seller(2L, "felipe.133", "Felipe Bontempo", "email2@hotmail.com", "123456", null);
        Seller s3 = new Seller(3L, "felipe.13iu34", "Fernando Netto", "email3@hotmail.com", "123456", null);
        Seller s4 = new Seller(5L, "felipe.1338", "Leonardo", "email4@hotmail.com", "123456", null);
        Seller s5 = new Seller(6L, "felipe.13iuii34", "Rafael Menezes", "email5@hotmail.com", "123456", null);


    }
}
