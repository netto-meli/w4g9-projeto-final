package com.mercadolibre.w4g9projetofinal.test.integration;

import com.mercadolibre.w4g9projetofinal.dtos.request.WarehouseRequestDTO;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;
import com.mercadolibre.w4g9projetofinal.repository.WarehouseRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
public class WarehouseContorllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Test
    @Order(1)
    public void alimentaBanco() {

        Warehouse wh = new Warehouse(2L, "Testando", "São Paulo");
        Warehouse wh1 = new Warehouse(3L, "Testando1", "Rio de Janeiro");
        Warehouse wh2 = new Warehouse(4L, "Testando2", "Belo Horizonte");
        Warehouse wh3 = new Warehouse(5L, "Testando3", "Curitiba");
        List<Warehouse> list = new ArrayList<>(Arrays.asList(wh, wh1, wh2, wh3));

    }


    @Test
    @Order(2)
    public void incluisaoDados() {
        Warehouse warehouse = new Warehouse(2L, "Melicidade", "São Paulo");
        warehouse = warehouseRepository.save(warehouse);
    }

    @Test
    @Order(3)
    public void warhouseInsercao() throws Exception {

        WarehouseRequestDTO warehouse = new WarehouseRequestDTO("Meli", "São Paulo");

        JSONObject produto = new JSONObject();
        produto.put("name", warehouse.getName());
        produto.put("location", warehouse.getLocation());


        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/fresh-products/warehouse").contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(produto))).andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/freshproducts/warehouse").contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(produto))).andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @Order(4)
    public void todosOsGets() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/fresh-products/warehouse")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        System.out.print("\n\nConsultas:\n" + result.getResponse().getContentAsString() + "\n\n");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/freshproducts/warehouse").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }




    }



