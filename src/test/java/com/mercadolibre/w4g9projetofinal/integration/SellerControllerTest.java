package com.mercadolibre.w4g9projetofinal.integration;

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
public class SellerControllerTest extends ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void insertSeller() throws Exception{
        String payLoad = "{\n"
                + "	\"name\":\"Nome pessoal\",\n"
                + "    \"email\": \"email@gmail.com\",\n"
                + "    \"password\" : \"123456\"\n"
                + "}";
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/fresh-products/sellers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

}
