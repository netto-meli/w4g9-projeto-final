package com.mercadolibre.w4g9projetofinal.test.integration;

import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.repository.SellerRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthResourceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SellerRepository sellerRepository;

    @Test
    @Order(1)
    public void populaDB() {
        Seller seller1 = new Seller(null, "UserSeller1", "Seller1", "seller1@gmail.com", "Ma174578@", null);
        Seller seller2 = new Seller(null, "UserSeller2", "Seller2", "seller2@gmail.com", "Ma174578@", null);
        Seller seller3 = new Seller(null, "UserSeller3", "Seller3", "seller3@gmail.com", "Ma174578@", null);
        Seller seller4 = new Seller(null, "UserSeller4", "Seller4", "seller4@gmail.com", "Ma174578@", null);
        sellerRepository.saveAll(Arrays.asList( seller1, seller2, seller3, seller4));
    }

    @Test
    @Order(2)
    public void deveAlterarSenha() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/auth/forgot/").param("email", "seller1@gmail.com"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
    }
}
