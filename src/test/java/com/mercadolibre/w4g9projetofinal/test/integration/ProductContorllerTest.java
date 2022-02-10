package com.mercadolibre.w4g9projetofinal.test.integration;

import com.mercadolibre.w4g9projetofinal.dtos.request.ProductRequestDTO;
import com.mercadolibre.w4g9projetofinal.entity.Advertise;
import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.Product;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.entity.enums.AdvertiseStatus;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.repository.AdvertiseRepository;
import com.mercadolibre.w4g9projetofinal.repository.BatchRepository;
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
import java.time.LocalDate;
import java.time.LocalDateTime;

/***
 * @author Leonardo
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

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private AdvertiseRepository advertiseRepository;

    @Autowired
    BatchRepository batchRepository;

    @Test
    @Order(1)
    public void incluiDados() {
        Product product1 = new Product(null, "produto1", "desc produto 1",
                10F, 20F, RefrigerationType.FRESH);
        productRepository.save(product1);

        Product product2 = new Product(null, "produto2", "desc produto 4",
                10F, 20F, RefrigerationType.COLD);
        productRepository.save(product2);

        Product product3 = new Product(null, "produto3", "desc produto 4",
                10F, 20F, RefrigerationType.COLD);
        productRepository.save(product3);

        Product product4 = new Product(null, "produto3", "desc produto 4",
                10F, 20F, RefrigerationType.FROZEN);
        productRepository.save(product4);

        Seller seller = new Seller(null, "userSeller",
                "vendedor nome", "email1@hotmail.com", crypt.encode("123456"), null);
        seller = sellerRepository.save(seller);

        Advertise a1 = new Advertise(null, "Anuncio 1", product1, seller, BigDecimal.TEN, AdvertiseStatus.ATIVO,false );
        Advertise a3 = new Advertise(null, "Anuncio 3", product1, seller, BigDecimal.TEN, AdvertiseStatus.ATIVO,false );
        Advertise a2 = new Advertise(null, "Anuncio 2", product2, seller, BigDecimal.TEN, AdvertiseStatus.ATIVO,true );
        a1 = advertiseRepository.save(a1);
        a2 = advertiseRepository.save(a2);
        a3 = advertiseRepository.save(a3);

        LocalDate lc = LocalDate.now();
        LocalDateTime lt = LocalDateTime.now();
        Batch t1 = new Batch(1L, 50, 100, 10F, 10F,
                lc.plusDays(10), lc, lt, a1, null);

        Batch t2 = new Batch(2L, 50, 100, 10F, 10F,
                lc.plusDays(10), lc, lt, a2, null);

        Batch t3 = new Batch(2L, 50, 100, 10F, 10F,
                lc.plusDays(10), lc, lt, a3, null);

        batchRepository.save(t2);
        batchRepository.save(t3);
        batchRepository.save(t1);

    }

    @Test
    @Order(2)
    public void insertProduct() throws Exception {

        ProductRequestDTO prod = new ProductRequestDTO( "produto", "desc produto",
                20, 40, RefrigerationType.COLD);

        JSONObject item = new JSONObject();
        item.put("name",prod.getName());
        item.put("description",prod.getDescription());
        item.put("minTemperature",prod.getMinTemperature());
        item.put("maxTemperature",prod.getMaxTemperature());
        item.put("category_refrigeration",prod.getCategoryRefrigeration());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/fresh-products/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(item)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
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
    }

    @Test
    @Order(5)
    public void putIdUpdate() throws Exception {

        ProductRequestDTO prod = new ProductRequestDTO( "produto11", "desc produto 123",
                20F, 40F, RefrigerationType.FROZEN);

        JSONObject item = new JSONObject();
        item.put("name",prod.getName());
        item.put("description",prod.getDescription());
        item.put("minTemperature",prod.getMinTemperature());
        item.put("maxTemperature",prod.getMaxTemperature());
        item.put("category_refrigeration",prod.getCategoryRefrigeration());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .put("/api/v1/fresh-products/product/" + 3L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(item)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nDado alterado:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    @Test
    @Order(9)
    public void delete() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/fresh-products/product/" + 4L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nDado excluido:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    @Test
    @Order(6)
    public void orderByCategory() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/fresh-products/product/listCategory/" +"?categoryProd=FROZEN")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nFiltro por tipo Congelado:\n" + result.getResponse().getContentAsString() + "\n\n");
    }


    @Test
    @Order(7)
    public void orderByBatch() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/fresh-products/product/listBatch/" +"?id=" + 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nLista dos lotes onde o produto esta cadastrado: \n" + result.getResponse().getContentAsString() + "\n\n");
    }

    @Test
    @Order(8)
    public void orderByBatchForProduct() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/fresh-products/product/listBatchByProduct/" +"?id=" + 1L + "&order=C")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nLista dos lotes ondernados por qtd atual: \n" + result.getResponse().getContentAsString() + "\n\n");
    }

}
