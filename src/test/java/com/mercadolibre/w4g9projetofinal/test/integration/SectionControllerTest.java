package com.mercadolibre.w4g9projetofinal.test.integration;

import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.repository.SectionRepository;
import com.mercadolibre.w4g9projetofinal.repository.WarehouseRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WithMockUser(username="teste001", roles={"ADMIN"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SectionControllerTest {

    private static MockHttpServletRequestBuilder request;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    SectionRepository repository;

    @Autowired
    WarehouseRepository repositoryWarehouse;

    @Test
    @Order(1)
    public void incluirDados(){
        Warehouse w = new Warehouse(1L, "SP", "Melicidade");
        repositoryWarehouse.save(w);
        Section s = new Section(1L, w, "Section-001", RefrigerationType.FRESH, 100, 70, 0.10f, 5.0f, null);
        repository.save(s);
    }

    @Test
    @Order(3)
    void deveRetornaTodasAsSectionsRegistradas() throws Exception {
        URI path = URI.create("/api/v1/fresh-products/section");
        request = MockMvcRequestBuilders.get(path);
        MvcResult result = mockMvc
            .perform(request)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    @Order(4)
    void deveRetornaUmaSectionPeloId() throws Exception {
        List<Section> sections = repository.findAll();
        Long sectionId = sections.get(0).getId();

        URI path = URI.create("/api/v1/fresh-products/section/" + sectionId);
        request = MockMvcRequestBuilders.get(path);
        mockMvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @Order(2)
    void deveRegistrarUmaNovaSection() throws Exception {
        String payload = "{\n" +
                "  \"name\": \"Sector 005\",\n" +
                "  \"type\": \"FROZEN\",\n" +
                "  \"id_warehouse\": 1,\n" +
                "  \"stock_limit\": 77,\n" +
                "  \"current_stock\": 10,\n" +
                "  \"min_teperature\": 0.3,\n" +
                "  \"max_teperature\": 0.2\n" +
                "}";
        URI path = URI.create("/api/v1/fresh-products/section");
        request = MockMvcRequestBuilders.post(path).content(payload).contentType(MediaType.APPLICATION_JSON);
        mockMvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    @Order(5)
    void deveAtualizarUmaSection() throws Exception {
        List<Section> sections = repository.findAll();
        System.out.println(sections);
        Long sectionId = sections.get(0).getId();

        String payload = "{\n" +
                "  \"name\": \"Sector 007\",\n" +
                "  \"type\": \"FROZEN\",\n" +
                "  \"id_warehouse\": 1,\n" +
                "  \"stock_limit\": 77,\n" +
                "  \"current_stock\": 10,\n" +
                "  \"min_teperature\": 0.3,\n" +
                "  \"max_teperature\": 0.2\n" +
                "}";


        URI path = URI.create("/api/v1/fresh-products/section/" + sectionId);
        request = MockMvcRequestBuilders.put(path).content(payload).contentType(MediaType.APPLICATION_JSON);
        mockMvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @Order(6)
    void deveDeletarUmaSection() throws Exception {
        List<Section> sections = repository.findAll();
        System.out.println(sections);
        Long sectionId = sections.get(0).getId();

        URI path = URI.create("/api/v1/fresh-products/section/" + sectionId);
        request = MockMvcRequestBuilders.delete(path);
        mockMvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}