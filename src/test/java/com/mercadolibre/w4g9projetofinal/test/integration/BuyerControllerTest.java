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
public class BuyerControllerTest extends ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void insertBuyer() throws Exception{
        String payLoad = "{\n"
                + "	\"address\":\"Endereco qualquer\",\n"
                + "    \"name\": \"Luis\",\n"
                + "    \"email\" : \"email@email\"\n"
                + "}";
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/fresh-products/buyer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

}
