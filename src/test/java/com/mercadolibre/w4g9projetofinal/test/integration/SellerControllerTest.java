package com.mercadolibre.w4g9projetofinal.test.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"ADMIN"})
public class SellerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void insertSeller() throws Exception{
        String payLoad = "{\n" +
                "  \"username\": \"a8767876\",\n" +
                "  \"name\": \"hfgfhgg\",\n" +
                "  \"email\": \"hjjg@ggfhj.gh\",\n" +
                "  \"pass\": \"jhgh7#Jjghhj\"\n" +
                "}";
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/fresh-products/seller")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

}
