package com.mercadolibre.w4g9projetofinal.test.integration;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WithMockUser(username="admin",roles={"ADMIN"})
public class UserContorllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void deveRetornarListaDeUsuarios() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/fresh-products/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nLista de Usuários cadastrados:\n" + result.getResponse().getContentAsString() + "\n\n");
    }
}
