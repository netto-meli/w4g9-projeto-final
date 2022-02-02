package com.mercadolibre.w4g9projetofinal.test;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DesafioSpringIntegratedTest {/*
    private static String bairroCadastrado;
    private static String casaCadastrada;
    @Autowired
    private MockMvc mockMvc;

    /*** <b>TeteIntegrado-0001:<b><br>
     * Cadastro de um bairro:<br>
     * Se bairro não existe, o cadastro é realizado<br>
     * Se bairro existe, retorna uma exceção
     *
    @Test
    @Order(1)
    public void deveCadastrarUmBairro() throws Exception{
        String alfabeto = "qwertyuioplkjhgfdsazxc vbnm";
        StringBuilder generatedString = new StringBuilder("Z");
        Random r = new Random();
        for (int i = 0; i < 15; i++) generatedString.append(alfabeto.charAt(r.nextInt(27)));
        String payLoad = "{\n" +
                "  \"nome\": \"" + generatedString + "\",\n" +
                "  \"valorMetroQuadrado\": 10\n" +
                "}";
        bairroCadastrado = String.valueOf(generatedString);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/bairros/cadastrarBairro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/bairros/cadastrarBairro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    /*** <b>TeteIntegrado-0002:<b><br>
     * Lista de Bairros cadastrados*
     /*
    @Test
    @Order(2)
    public void deveRetornarListaBairros() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/bairros"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nLista de bairros cadastrados:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    /*** <b>TeteIntegrado-0003:<b><br>
     * Busca de um bairro:<br>
     * Se bairro não existe, retorna uma exceção<br>
     * Se bairro existe, ele é retornado.
     *
    @Test
    @Order(3)
    public void deveRetornarBuscaBairroCadastrado() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/bairros/"+bairroCadastrado))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nBusca do bairro cadastrado:\n" + result.getResponse().getContentAsString() + "\n\n");

        result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/bairros/"+bairroCadastrado+ "123"))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andReturn();


        System.out.println("\n\nBusca de um bairro não cadastrado:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    /*** <b>TeteIntegrado-0004:<b><br>
     * Cadastro de uma casa:<br>
     * Se casa não existe, o cadastro é realizado<br>
     * Se casa existe, retorna uma exceção
     *
    /*
    @Test
    @Order(4)
    public void deveCadastrarUmaCasa() throws Exception{
        String alfabeto = "qwertyuioplkjhgfdsazxc vbnm";
        Random r = new Random();
        StringBuilder generatedString = new StringBuilder("Z");
        for (int i = 0; i < 15; i++) generatedString.append(alfabeto.charAt(r.nextInt(27)));
        String payLoad = "{\n" +
                "  \"nome\": \"" + generatedString + "\",\n" +
                "  \"bairro\": \"" + bairroCadastrado + "\",\n" +
                "  \"comodos\": [\n" +
                "    {\n" +
                "      \"nome\": \"Prtuytuytuyt Ahhhhhh\",\n" +
                "      \"largura\": 1,\n" +
                "      \"comprimento\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"nome\": \"Prtuytuhhhh\",\n" +
                "      \"largura\": 1,\n" +
                "      \"comprimento\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"nome\": \"Prtuytuyth\",\n" +
                "      \"largura\": 1,\n" +
                "      \"comprimento\": 1\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        casaCadastrada = String.valueOf(generatedString);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/casas/cadastrarCasa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/casas/cadastrarCasa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payLoad))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    /*** <b>TeteIntegrado-0005:<b><br>
     * Lista de casas
     *
    /*
    @Test
    @Order(5)
    public void deveRetornarListaCasas() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/casas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nLista de casas cadastradas\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    /*** <b>TeteIntegrado-0006:<b><br>
     * Busca de uma casa:<br>
     * Se casa não existe, retorna uma exceção<br>
     * Se casa existe, ele é retornado.
     *
    @Test
    @Order(6)
    public void deveRetornarBuscaCasaCadastrada() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/casas/"+casaCadastrada))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nCasa cadastrada:\n" + result.getResponse().getContentAsString() + "\n\n");

        result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/casas/"+casaCadastrada + "123"))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andReturn();

        System.out.println("\n\nCasa cadastrada:\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    /*** <b>TeteIntegrado-0007:<b><br>
     * Busca da área total de uma casa
     *
    @Test
    @Order(7)
    public void deveRetornarAreaTotalDaCasaCadastrada() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/casas/area/"+casaCadastrada))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nArea total da casa cadastrada\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    /*** <b>TeteIntegrado-0008:<b><br>
     * Busca do valor de uma casa
     *
    @Test
    @Order(8)
    public void deveRetornarValorDaCasaCadastrada() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/casas/valorCasa/"+casaCadastrada))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nValor da casa cadastrada\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    /*** <b>TeteIntegrado-0009:<b><br>
     * Busca do maior cômodo de uma casa
     *
    @Test
    @Order(9)
    public void deveRetornarMaiorComodoDaCasaCadastrada() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/casas/maiorComodo/"+casaCadastrada))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nMaior Comodo cadastrado\n" + result.getResponse().getContentAsString() + "\n\n");
    }

    /*** <b>TeteIntegrado-0010:<b><br>
     * Busca da área de cada cômodo de uma casa
     *
    @Test
    @Order(10)
    public void deveRetornarAreaDosComodosDaCasaCadastrada() throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/casas/areaComodos/"+casaCadastrada))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("\n\nArea dos comodos cadastrados\n" + result.getResponse().getContentAsString() + "\n\n");
    }*/
}