package com.mercadolibre.w4g9projetofinal.test.integration;

import com.mercadolibre.w4g9projetofinal.dtos.request.BuyerRequestDTO;
import com.mercadolibre.w4g9projetofinal.entity.Buyer;
import com.mercadolibre.w4g9projetofinal.repository.BuyerRepository;
import com.mercadolibre.w4g9projetofinal.service.UserService;
import org.json.JSONObject;
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

/***
 * @autor Leonardo
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WithMockUser(username = "admin", roles = {"ADMIN"})
public class BuyerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BCryptPasswordEncoder crypt;

    @Autowired
    private BuyerRepository buyerRepository;

    @Test
    @Order(1)
    public void incluiDados() {
        Buyer buyer = new Buyer(2L, "userComprador",
                "Comprador nome", "email1@hotkkmail.com", crypt.encode("123776456"), "Endereco");
        buyer = buyerRepository.save(buyer);
    }

    @Test
    @Order(2)
    public void insertBuyer() throws Exception {

        BuyerRequestDTO buyer = new BuyerRequestDTO("user", "Comprador", "email12@hotkkmail.com", crypt.encode("1237764526"), "Endereco2");

        JSONObject item = new JSONObject();
        item.put("username", buyer.getUsername());
        item.put("name", buyer.getName());
        item.put("email", buyer.getEmail());
        item.put("pass", buyer.getPass());
        item.put("address", buyer.getAddress());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/fresh-products/buyer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(item)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/freshproducts/buyer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(item)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Order(3)
    public void getAll() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/fresh-products/buyer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nDados comsultados:\n" + result.getResponse().getContentAsString() + "\n\n");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/freshproducts/buyer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Order(4)
    @WithUserDetails("userComprador")
    public void getId() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/fresh-products/buyer/" + 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nDado consultados:\n" + result.getResponse().getContentAsString() + "\n\n");

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/fresh-products/buyer/" + 4L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Order(5)
    @WithUserDetails("userComprador")
    public void putIdUpedate() throws Exception {

        BuyerRequestDTO buyerUpdate = new BuyerRequestDTO("userAlterado", "CompradorT", "email1@hotkkmail.com", crypt.encode("123776456"), "Endereco");

        JSONObject item = new JSONObject();
        item.put("username", buyerUpdate.getUsername());
        item.put("name", buyerUpdate.getName());
        item.put("email", buyerUpdate.getEmail());
        item.put("pass", buyerUpdate.getPass());
        item.put("address", buyerUpdate.getAddress());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .put("/api/v1/fresh-products/buyer/" + 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(item)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nDado alterado:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    @Test
    @Order(6)
    @WithUserDetails("userComprador")
    public void delete() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/fresh-products/buyer/" + 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nDado excluido:\n" + result.getResponse().getContentAsString() + "\n\n");
    }
}
