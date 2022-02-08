package com.mercadolibre.w4g9projetofinal.test.integration;

import com.mercadolibre.w4g9projetofinal.integration.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class BuyerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void insertBuyer() throws Exception{
        String payLoad = "{\n" +
                "  \"username\": \"gf6jhfuyu6f6jhf\",\n" +
                "  \"name\": \"jhgh hg o\",\n" +
                "  \"email\": \"h@h.co\",\n" +
                "  \"pass\": \"fg#Dh9tredg\",\n" +
                "  \"address\": \"hjghhgjhghjhghjhghhhj\"\n" +
                "}";
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/fresh-products/buyer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

}
