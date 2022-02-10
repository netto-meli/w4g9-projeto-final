package com.mercadolibre.w4g9projetofinal.test.integration;

import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.entity.enums.Profile;
import com.mercadolibre.w4g9projetofinal.repository.SellerRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WithMockUser(username = "admin", roles = {"ADMIN"})
public class SellerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private BCryptPasswordEncoder pe;

    @Test
    @Order(1)
    public void populaDB() {
        Seller seller1 = new Seller(null, "UserSeller1", "Seller1", "seller1@gmail.com", pe.encode("Ma174578@"), null);
        Seller seller2 = new Seller(null, "UserSeller2", "Seller2", "seller2@gmail.com", pe.encode("Ma174578@"), null);
        Seller seller3 = new Seller(null, "UserSeller3", "Seller3", "seller3@gmail.com", pe.encode("Ma174578@"), null);
        Seller seller4 = new Seller(null, "UserSeller4", "Seller4", "seller4@gmail.com", pe.encode("Ma174578@"), null);
        seller4.getProfile().add(Profile.ADMIN);
        sellerRepository.saveAll(Arrays.asList( seller1, seller2, seller3, seller4));
    }

    @Test
    @Order(2)
    public void deveBuscarListSeller() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/fresh-products/seller"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @Order(3)
    @WithUserDetails("UserSeller4")
    public void deveBuscarUmSellerPorId() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/fresh-products/seller/" + 2L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @Order(4)
    public void deveInserirUmSeller() throws Exception {
        String payLoad = "{\n" +
                "\"username\": \"vrs_marcos\", \n" +
                "\"name\": \"Marcos Vinicius\", \n" +
                "\"email\": \"teste@gmail.com\", \n" +
                "\"pass\": \"It745856@\" \n" +
                "}";

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/fresh-products/seller")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    @Order(5)
    @WithUserDetails("UserSeller4")
    public void deveAlterarUmSeller() throws Exception {
        String payLoad = "{\n" +
                "\"username\": \"vrs_marcosss\", \n" +
                "\"name\": \"Marcos Vinicius Sá\", \n" +
                "\"email\": \"teste2@gmail.com\", \n" +
                "\"pass\": \"It74587@\" \n" +
                "}";

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.put("/api/v1/fresh-products/seller/" + 2L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
    }

    @Test
    @Order(6)
    @WithUserDetails("UserSeller4")
    public void deveDeletarUser() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.delete("/api/v1/fresh-products/seller/" + 2L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}
