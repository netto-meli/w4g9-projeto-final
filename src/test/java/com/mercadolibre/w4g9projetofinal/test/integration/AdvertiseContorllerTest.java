package com.mercadolibre.w4g9projetofinal.test.integration;

import com.mercadolibre.w4g9projetofinal.dtos.request.AdvertiseRequestDTO;
import com.mercadolibre.w4g9projetofinal.entity.Advertise;
import com.mercadolibre.w4g9projetofinal.entity.Product;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.entity.enums.AdvertiseStatus;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.repository.AdvertiseRepository;
import com.mercadolibre.w4g9projetofinal.repository.ProductRepository;
import com.mercadolibre.w4g9projetofinal.repository.SellerRepository;
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

import java.math.BigDecimal;

/***
 * @author Leonardo
 */

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WithMockUser(username = "admin", roles = {"ADMIN"})
public class AdvertiseContorllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BCryptPasswordEncoder crypt;

    @Autowired
    private AdvertiseRepository advertiseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Test
    @Order(1)
    public void incluiDados() {
        Product product1 = new Product(null, "produto1", "desc produto 1",
                10F, 20F, RefrigerationType.FRESH);
        productRepository.save(product1);

        Seller seller = new Seller(null, "userSeller",
                "vendedor nome", "email@hotmail.com", crypt.encode("123456"), null);
        sellerRepository.save(seller);

        Advertise advertise = new Advertise(null, "description", product1, seller, BigDecimal.valueOf(46), AdvertiseStatus.ATIVO, true);
        advertiseRepository.save(advertise);
    }

    @Order(2)
    @Test
    public void insertAdvertise() throws Exception {

        Product product1 = new Product(null, "produto2", "desc produto 3",
                10F, 20F, RefrigerationType.FRESH);
        product1 = productRepository.save(product1);

        Seller seller = new Seller(null, "userSellerIdinclu",
                "vendedor nome incluso", "email1@hotmail.com", crypt.encode("123456"), null);
        seller = sellerRepository.save(seller);

        AdvertiseRequestDTO advertiseRequestDTO = new AdvertiseRequestDTO( "description", product1.getId(), seller.getId(), BigDecimal.valueOf(489), AdvertiseStatus.INATIVO, true);

        JSONObject item = new JSONObject();
        item.put("description",advertiseRequestDTO.getDescription());
        item.put("product_id",advertiseRequestDTO.getProductId());
        item.put("seller_id",advertiseRequestDTO.getSellerId());
        item.put("price",advertiseRequestDTO.getPrice());
        item.put("status",advertiseRequestDTO.getStatus());
        item.put("free_shipping",advertiseRequestDTO.getFreeShipping());

        MvcResult result =  mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/fresh-products/advertise")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(item)))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

        System.out.println("\n\nDados incluidos:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    @Test
    @Order(3)
    public void getAll() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/fresh-products/advertise")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nDados consultados:\n" + result.getResponse().getContentAsString() + "\n\n");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/freshproducts/advertise")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Order(4)
    public void getId() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/fresh-products/advertise/" + 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nDado consultados:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    @Test
    @Order(5)
    public void putIdUpedate() throws Exception {

        Product product1 = new Product(2L, "produto2", "desc produto 3",
                10F, 20F, RefrigerationType.FRESH);
        product1 = productRepository.save(product1);

        Seller seller = new Seller(3L, "userSellerIdinclu",
                "vendedor nome incluso", "emailtu@hotmail.com", crypt.encode("123456"), null);
        seller = sellerRepository.save(seller);

        AdvertiseRequestDTO advertiseRequestDTO = new AdvertiseRequestDTO( "description", product1.getId(), seller.getId(), BigDecimal.valueOf(461), AdvertiseStatus.INATIVO, true);

        JSONObject item = new JSONObject();
        item.put("description",advertiseRequestDTO.getDescription());
        item.put("product_id",advertiseRequestDTO.getProductId());
        item.put("seller_id",advertiseRequestDTO.getSellerId());
        item.put("price",advertiseRequestDTO.getPrice());
        item.put("status",advertiseRequestDTO.getStatus());
        item.put("free_shipping",advertiseRequestDTO.getFreeShipping());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .put("/api/v1/fresh-products/advertise/" + 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(item)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andReturn();

        System.out.println("\n\nDado alterado:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    @Test
    @Order(6)
    public void delete() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/fresh-products/advertise/" + 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nDado excluido:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

}
