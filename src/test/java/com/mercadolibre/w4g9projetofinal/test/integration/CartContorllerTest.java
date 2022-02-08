package com.mercadolibre.w4g9projetofinal.test.integration;

import com.mercadolibre.w4g9projetofinal.entity.*;
import com.mercadolibre.w4g9projetofinal.entity.enums.AdvertiseStatus;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.entity.enums.RepresentativeJob;
import com.mercadolibre.w4g9projetofinal.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
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
public class CartContorllerTest {

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
    private SectionRepository sectionRepository;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private InboundOrderRepository inboundOrderRepository;

    @Test
    @Order(1)
    public void populateDB(){

        Warehouse warehouse = new Warehouse(null, "k", "l" );
        warehouse = warehouseRepository.save(warehouse);

        Buyer buyer = new Buyer(null, "userComprador",
                "Comprador nome", "email1@hotkkmail.com", pe.encode("123776456"), "Endereco");
        buyer = buyerRepository.save(buyer);

        Seller seller = new Seller(null, "userSeller",
                "vendedor nome", "email1@hotmail.com", pe.encode("123456"), null);
        seller = sellerRepository.save(seller);

        Representative representative = new Representative(null, "userRepresentative",
                "Representante nome", "em1@gmail.com", pe.encode("151515"),
                RepresentativeJob.LIDER, warehouse);
        representative = representativeRepository.save(representative);

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

        Section section = new Section(null, warehouse, "Setor", RefrigerationType.FRESH, 10, 10, 10F, 20F, null );
        section = sectionRepository.save(section);

        LocalDate lc = LocalDate.now();
        LocalDateTime lt = LocalDateTime.now();
        List<Batch> l1 = new ArrayList<>();
        List<Batch> l2 = new ArrayList<>();
        Batch t1 = new Batch(1L, 50, 100, 10F, 10F,
                lc.plusDays(10), lc, lt, a1, null);
        Batch t2 = new Batch(2L, 50, 100, 10F, 10F,
                lc.plusDays(10), lc, lt, a2, null);
        l1.add(t1);
        l1.add(t2);
        InboundOrder i1 = new InboundOrder(1L, lc, seller, representative, l1, section);
        InboundOrder i2 = new InboundOrder(2L, lc, seller, representative, l2, section);
        for (Batch b: i1.getBatchList())
            b.setInboundOrder(i1);
        for (Batch b: i2.getBatchList())
            b.setInboundOrder(i2);
        inboundOrderRepository.saveAll(Arrays.asList(i1, i2));
    }

    @Test
    @Order(2)
    public void deveIncluirUmItemDeUmAnuncioNoCarrinhoDoComprador() throws Exception {

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/fresh-products/cart/addProduct/"
                                + 1L
                                + "?idAdvertise="
                                + 1L
                                + "&qtdProduct="
                                + 1))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        System.out.println("\n\nCarrinho de compras criado:\n" + result.getResponse().getContentAsString() + "\n\n");

        result = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/fresh-products/cart/addProduct/"
                                + 1L
                                + "?idAdvertise="
                                + 2L
                                + "&qtdProduct="
                                + 2))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        System.out.println("\n\nMais produtos adicionados no carrinho de compra:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    @Test
    @Order(3)
    public void deveRemoverUmItemDeUmAnuncioNoCarrinhoDoComprador() throws Exception {

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/v1/fresh-products/cart/removeProduct/"
                                + 1L
                                + "?idAdvertise="
                                + 1L
                                + "&qtdRemove="
                                + 1))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        System.out.println("\n\nItem removido do carrinho:\n" + result.getResponse().getContentAsString() + "\n\n");

        result = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/v1/fresh-products/cart/removeProduct/"
                                + 1L
                                + "?idAdvertise="
                                + 2L
                                + "&qtdRemove="
                                + 1))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        System.out.println("\n\nMais um item removido do carrinho:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    @Test
    @Order(4)
    public void deveMostrarCarrinhoDoComprador() throws Exception {

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/fresh-products/cart/" + 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nCarrinho atual do comprador:\n" + result.getResponse().getContentAsString() + "\n\n");

    }

    @Test
    @Order(5)
    public void deveEsvaziarCarrinhoDoComprador() throws Exception {

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/v1/fresh-products/cart/emptyCart/" + 1L))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        System.out.println("\n\nCarrinho vazio:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    @Test
    @Order(6)
    public void deveCriarUmPedidoProComprador() throws Exception {

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/fresh-products/cart/addProduct/"
                                + 1L
                                + "?idAdvertise="
                                + 1L
                                + "&qtdProduct="
                                + 1))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        System.out.println("\n\nCarrinho de compras criado:\n" + result.getResponse().getContentAsString() + "\n\n");


        result = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/v1/fresh-products/cart/createSellOrder/" + 1L))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        System.out.println("\n\nCarrinho de compras criado:\n" + result.getResponse().getContentAsString() + "\n\n");


        result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/fresh-products/cart/" + 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nPedido de compra do comprador:\n" + result.getResponse().getContentAsString() + "\n\n");

    }
}
