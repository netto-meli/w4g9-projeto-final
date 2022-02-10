package com.mercadolibre.w4g9projetofinal.test.integration;

import com.mercadolibre.w4g9projetofinal.entity.Representative;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;
import com.mercadolibre.w4g9projetofinal.entity.enums.Profile;
import com.mercadolibre.w4g9projetofinal.entity.enums.RepresentativeJob;
import com.mercadolibre.w4g9projetofinal.repository.RepresentativeRepository;
import com.mercadolibre.w4g9projetofinal.repository.WarehouseRepository;
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
public class RepresentativeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RepresentativeRepository representativeRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private BCryptPasswordEncoder pe;

    @Test
    @Order(1)
    public void populaDB() {
        Warehouse warehouse = new Warehouse(null, "warehouse1", "DF");
        warehouseRepository.save(warehouse);

        Representative representative1 = new Representative(null, "representative1", "representative 1", "representative1@gmail.com", pe.encode("It145785@"), RepresentativeJob.LIDER, warehouse);
        Representative representative2 = new Representative(null, "representative2", "representative 2", "representative2@gmail.com", pe.encode("It145785@"), RepresentativeJob.SUPERVISOR, warehouse);
        Representative representative3 = new Representative(null, "representative3", "representative 3", "representative3@gmail.com", pe.encode("It145785@"), RepresentativeJob.SUPERVISOR, warehouse);
        Representative representative4 = new Representative(null, "representative4", "representative 4", "representative4@gmail.com", pe.encode("It145785@"), RepresentativeJob.LIDER, warehouse);
        representative1.getProfile().add(Profile.ADMIN);
        representativeRepository.saveAll(Arrays.asList(representative1, representative2, representative3, representative4));
    }

    @Test
    @Order(2)
    public void deveBuscarListaRepresentative() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/fresh-products/representative"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @Order(3)
    @WithUserDetails("representative1")
    public void deveBuscarRepresentativePorId() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/fresh-products/representative/" + 2L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @Order(4)
    @WithUserDetails("representative1")
    public void deveInserirUmRepresentative() throws Exception {
        String payLoad = "{\n" +
                "\"username\": \"teste23232\", \n" +
                "\"name\": \"testesaded\", \n" +
                "\"email\": \"teste233@gmail.com\", \n" +
                "\"pass\": \"It4585521@\", \n" +
                "\"job\": \"SUPERVISOR\", \n" +
                "\"warehouse_id\": " + 1 +
                " \n }";

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/api/v1/fresh-products/representative/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
    }

    @Test
    @Order(5)
    @WithUserDetails("representative1")
    public void deveAlterarUmRepresentative() throws Exception {
        String payLoad = "{\n" +
                "\"username\": \"teste\", \n" +
                "\"name\": \"teste\", \n" +
                "\"email\": \"teste@gmail.com\", \n" +
                "\"pass\": \"It4585521@\", \n" +
                "\"job\": \"SUPERVISOR\", \n" +
                "\"warehouse_id\": " + 1 +
                " \n }";

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.put("/api/v1/fresh-products/representative/" + 3L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
    }

    @Test
    @Order(6)
    @WithUserDetails("representative1")
    public void deveDeletarUmRepresentative() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.delete("/api/v1/fresh-products/representative/" + 2L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}
