package com.mercadolibre.w4g9projetofinal.test.integration;

import com.mercadolibre.w4g9projetofinal.dtos.request.ProductRequestDTO;
import com.mercadolibre.w4g9projetofinal.entity.Product;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.repository.ProductRepository;
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
public class ProductContorllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BCryptPasswordEncoder crypt;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Order(1)
    public void incluiDados() {
        Product product1 = new Product(null, "produto1", "desc produto 1",
                10F, 20F, RefrigerationType.FRESH);
        product1 = productRepository.save(product1);

        Product product2 = new Product(null, "produto2", "desc produto 4",
                10F, 20F, RefrigerationType.COLD);
        product2 = productRepository.save(product1);
    }

    @Test
    @Order(2)
    public void insertBuyer() throws Exception {

        ProductRequestDTO prod = new ProductRequestDTO( "produto100", "desc produto 1",
                20F, 40F, RefrigerationType.FROZEN);

        JSONObject item = new JSONObject();
        item.put("name",prod.getName());
        item.put("description",prod.getDescription());
        item.put("minTemperature",prod.getMinTemperature());
        item.put("maxTemperature",prod.getMaxTemperature());
        item.put("categoryRefrigeration",prod.getCategoryRefrigeration());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/fresh-products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(item)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/freshproducts/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(item)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Order(3)
    public void getAll() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/fresh-products/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nDados consultados:\n" + result.getResponse().getContentAsString() + "\n\n");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/freshproducts/product/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Order(4)
    public void getId() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/fresh-products/product/" + 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nDado consultados:\n" + result.getResponse().getContentAsString() + "\n\n");

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/fresh-products/product/" + 4L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Order(5)
    public void putIdUpedate() throws Exception {

        ProductRequestDTO prod = new ProductRequestDTO( "produto11", "desc produto 123",
                20F, 40F, RefrigerationType.FROZEN);

        JSONObject item = new JSONObject();
        item.put("name",prod.getName());
        item.put("description",prod.getDescription());
        item.put("minTemperature",prod.getMinTemperature());
        item.put("maxTemperature",prod.getMaxTemperature());
        item.put("categoryRefrigeration",prod.getCategoryRefrigeration());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .put("/api/v1/fresh-products/product/" + 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(item)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nDado alterado:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    @Test
    @Order(6)
    public void delete() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/fresh-products/product/" + 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nDado excluido:\n" + result.getResponse().getContentAsString() + "\n\n");
    }






}
