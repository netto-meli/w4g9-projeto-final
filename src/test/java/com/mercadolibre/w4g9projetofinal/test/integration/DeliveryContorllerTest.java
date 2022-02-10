package com.mercadolibre.w4g9projetofinal.test.integration;

import com.mercadolibre.w4g9projetofinal.entity.*;
import com.mercadolibre.w4g9projetofinal.entity.enums.AdvertiseStatus;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.entity.enums.RepresentativeJob;
import com.mercadolibre.w4g9projetofinal.entity.enums.SellOrderStatus;
import com.mercadolibre.w4g9projetofinal.repository.*;
import com.newrelic.api.agent.NewRelic;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WithMockUser(username = "admin", roles = {"ADMIN"})
public class DeliveryContorllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BCryptPasswordEncoder pe;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RepresentativeRepository representativeRepository;
    @Autowired
    private AdvertiseRepository advertiseRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private SellOrderRepository sellOrderRepository;
    @Autowired
    private BuyerRepository buyerRepository;

    @Test
    @Order(1)
    public void populateDB(){

        Warehouse warehouse = new Warehouse(null, "k", "l" );
        warehouse = warehouseRepository.save(warehouse);
        Representative representative = new Representative(null, "userRepresentative",
                "Representante nome", "em1@gmail.com", pe.encode("151515"),
                RepresentativeJob.LIDER, warehouse);
        representative = representativeRepository.save(representative);
        Seller seller = new Seller(null, "userSeller",
                "vendedor nome", "email1@hotmail.com", pe.encode("123456"), null);
        seller = sellerRepository.save(seller);
        Product product1 = new Product(null, "produto1", "desc produto 1",
                10F, 20F, RefrigerationType.FRESH);
        Product product2 = new Product(null, "produto2", "desc produto 2",
                10F, 20F, RefrigerationType.FRESH);
        product1 = productRepository.save(product1);
        product2 = productRepository.save(product2);
        product1 = productRepository.findById(1L).orElse(null);
        product2 = productRepository.findById(2L).orElse(null);
        Advertise a1 = new Advertise(null, "Anuncio 1", product1, seller, BigDecimal.TEN, AdvertiseStatus.ATIVO,false );
        Advertise a2 = new Advertise(null, "Anuncio 2", product2, seller, BigDecimal.TEN, AdvertiseStatus.ATIVO,true );
        a1 = advertiseRepository.save(a1);
        a2 = advertiseRepository.save(a2);
        Buyer buyer = new Buyer(null, "userComprador",
                "Comprador nome", "email1@hotkkmail.com", pe.encode("123776456"), "Endereco");
        buyer = buyerRepository.save(buyer);
        OrderItem oi1 = new OrderItem(null, 5, a1, null);
        OrderItem oi2 = new OrderItem(null, 5, a1, null);
        OrderItem oi3 = new OrderItem(null, 5, a1, null);
        SellOrder s01 = new SellOrder(null, buyer, SellOrderStatus.PAID, null, BigDecimal.TEN, BigDecimal.TEN);
        SellOrder s02 = new SellOrder(null, buyer, SellOrderStatus.PAID, null, BigDecimal.TEN, BigDecimal.TEN);
        oi1.setSellOrder(s01);
        oi2.setSellOrder(s01);
        oi3.setSellOrder(s02);
        List<OrderItem> loi1 = new ArrayList<>();
        List<OrderItem> loi2 = new ArrayList<>();
        loi1.add(oi1);
        loi1.add(oi2);
        loi2.add(oi3);
        s01.setOrderItemList(loi1);
        s02.setOrderItemList(loi2);
        sellOrderRepository.save(s01);
        sellOrderRepository.save(s02);
    }

    @Test
    @Order(2)
    @WithUserDetails("userRepresentative")
    public void deveIncluirUmEntregador() throws Exception {

        String payLoad = "{\n" +
                "  \"name\": \"hh\",\n" +
                "  \"car_model\": \"h8\",\n" +
                "  \"car_plate\": \"AAA0000\",\n" +
                "  \"fresh_max_quantity\": 20,\n" +
                "  \"frozen_max_quantity\": 10,\n" +
                "  \"cold_max_quantity\": 30\n" +
                "}";

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/fresh-products/delivery/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        System.out.println("\n\nEntregador incluido:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    @Test
    @Order(3)
    public void deveConsultarTodosEntregadores() throws Exception {

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/fresh-products/delivery/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nLista de Entregadores consultados:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    @Test
    @Order(4)
    public void deveConsultarUmEntregador() throws Exception {

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/fresh-products/delivery/" + 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nEntregador consultado:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    @Test
    @Order(5)
    @WithUserDetails("userRepresentative")
    public void deveAlterarUmEntregador() throws Exception {

        String payLoad = "{\n" +
                "  \"name\": \"hh\",\n" +
                "  \"car_model\": \"h8\",\n" +
                "  \"car_plate\": \"AAA0000\",\n" +
                "  \"fresh_max_quantity\": 20,\n" +
                "  \"frozen_max_quantity\": 10,\n" +
                "  \"cold_max_quantity\": 30\n" +
                "}";

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/v1/fresh-products/delivery/" + 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nEntregador alterado:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    @Test
    @Order(6)
    public void deveBuscarEntregadoresPelaDisponibilidade() throws Exception {

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/fresh-products/delivery/byStatus/?isInRoute=" + true)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nEntregadores disponiveis:\n" + result.getResponse().getContentAsString() + "\n\n");

        result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/fresh-products/delivery/byStatus/?isInRoute=" + false)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nEntregadores indisponiveis:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    @Test
    @Order(7)
    public void deveSolicitarEntregadorParaListaDePedidosParaSeremEntregues() throws Exception {

        String payLoad = "[\n" +
                "   {\n" +
                "      \"id\": 1\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\": 2\n" +
                "   }\n" +
                "]";

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/fresh-products/delivery/delivery/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        System.out.println("\n\nEntregador em rota com as entregas:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    @Test
    @Order(8)
    public void deveVerificarRetornoDasEntregasDoEntregador() throws Exception {

        String payLoad = "[\n" +
                "   {\n" +
                "      \"id\": 2\n" +
                "   }\n" +
                "]";

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/v1/fresh-products/delivery/delivery/" + 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nEntregador retornou e foi pago:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    @Test
    @Order(9)
    public void deveExcluirUmEntregador() throws Exception {

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/v1/fresh-products/delivery/" + 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nEntregador excluido:\n" + result.getResponse().getContentAsString() + "\n\n");
    }
}
