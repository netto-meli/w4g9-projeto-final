package com.mercadolibre.w4g9projetofinal.test.integration;

import com.mercadolibre.w4g9projetofinal.dtos.request.WarehouseRequestDTO;
import com.mercadolibre.w4g9projetofinal.entity.*;
import com.mercadolibre.w4g9projetofinal.entity.enums.AdvertiseStatus;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.entity.enums.RepresentativeJob;
import com.mercadolibre.w4g9projetofinal.repository.*;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * @author Rafael Menezes
 */
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"ADMIN"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WarehouseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private AdvertiseRepository advertiseRepository;

    @Autowired
    private InboundOrderRepository inboundOrderRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private RepresentativeRepository representativeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Test
    @Order(1)
    public void populaDB() {

        Warehouse wh = new Warehouse(null, "Testando", "São Paulo");
        Warehouse wh1 = new Warehouse(null, "Testando1", "Rio de Janeiro");
        Warehouse wh2 = new Warehouse(null, "Testando2", "Belo Horizonte");
        Warehouse wh3 = new Warehouse(null, "Testando3", "Curitiba");
        warehouseRepository.saveAll(Arrays.asList(wh, wh1, wh2, wh3));

        Product product = new Product(null, "temp", "klkjlhkjl", 0, 0, RefrigerationType.FRESH);
        productRepository.save(product);

        Seller seller1 = new Seller(null, "tetsgs", "klhjklsaj", "teste@gmail.com", pe.encode("khkjhkjhk232@"), null);
        sellerRepository.save(seller1);

        Warehouse warehouse = new Warehouse(null, "teste", "df");
        warehouseRepository.save(warehouse);

        Representative representative1 = new Representative(null, "jkhjkhajksd", "dfsfds", "testeeee@gmail.com", pe.encode("jghj22@@45"), RepresentativeJob.LIDER, warehouse);
        representativeRepository.save(representative1);

        Advertise advertise1 = new Advertise(null, "description", product, seller1, new BigDecimal(40.00), AdvertiseStatus.ATIVO, true);
        advertiseRepository.save(advertise1);

        Section section = new Section(null, warehouse, "teste", RefrigerationType.FRESH, 10, 15, 0, 0, null);
        sectionRepository.save(section);

        InboundOrder inboundOrder1 = new InboundOrder(1L, LocalDate.now(), seller1, representative1, null, section);
        inboundOrderRepository.save(inboundOrder1);

        Batch batch1 = new Batch(1L, 10, 15, 0, 0, LocalDate.now(), LocalDate.now(), LocalDateTime.now(), advertise1, inboundOrder1);
        batchRepository.save(batch1);
    }

    @Test
    @Order(2)
    public void deveBuscarListadeWarehouse() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/fresh-products/warehouse"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @Order(3)
    public void DeveBuscarWarehousePorId() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/fresh-products/warehouse/" +2L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @Order(4)
    public void DeveInserirWarehouse() throws Exception {
        String payLoad = "{\n" +
                "\"name\": \"teste23232\", \n" +
                "\"location\": \"DF\"" +
                " \n }";

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/fresh-products/warehouse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    @Order(5)
    public void DeveAlterarWarehouse() throws Exception {
        String payLoad = "{\n" +
                "\"name\": \"teste23232\", \n" +
                "\"location\": \"DF\"" +
                " \n }";
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.put("/api/v1/fresh-products/warehouse/" + 2L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
    }

    @Test
    @Order(6)
    public void deveDeletarUmWarehouse() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.delete("/api/v1/fresh-products/warehouse/" + 3L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @Order(7)
    public void deveProcurarWarehousePorProdutoId() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/fresh-products/warehouse/byProduct/" + 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }




}



