package com.mercadolibre.w4g9projetofinal.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ImobiliariaUnityTest {

    /*** <b>US-0001:<b><br>
     * Verifique se o total de metros quadrados calculados por propriedade está correto.<br>
     * Retorna o cálculo correto do total de metros quadrados de uma propriedade.
     *
    @Test
    public void verificaSeTotalM2EstaCerto() {
        //arrange
        String casaComodo = "Casa";
        String bairro = "bairro";
        Comodo comodo1x1 = new Comodo("1", 1.0, 1.0);
        Comodo comodo5x5 = new Comodo("2", 5.0, 5.0);
        Comodo comodo5x1 = new Comodo("3", 5.0, 1.0);
        Comodo comodo1x5 = new Comodo("4", 1.0, 5.0);
        List<Comodo> comodos = new ArrayList<>(Arrays.asList(comodo1x1, comodo5x1, comodo5x5, comodo1x5));
        Casa casa = new Casa(casaComodo, bairro, comodos);
        String areaComodos = "A área total da casa é de: " + (36.0) + " metros.";

        CasaRepository mockCasaRepository = Mockito.mock(CasaRepository.class);
        Mockito.when(mockCasaRepository.findByNome(casaComodo)).thenReturn(casa);
        CasaService casaService = new CasaService(null, mockCasaRepository);

        //act
        String result = casaService.getAreaCasa(casaComodo);

        //assertion
        assertEquals(result, areaComodos);
    }

    /*** <b>US-0002:<b><br>
     * Verifique se o bairro de entrada existe no repositório de bairros.<br>
     * <i>Se cumprir:</i><br>
     * Permite continuar normalmente.<br>
     * <i>Se não cumprir:</i><br>
     * Relate a incompatibilidade com uma exceção.
     *
    @Test
    public void verificaBairroExistente() {
        //arrange
        String nomeBairroExiste = "Este Bairro Existe";
        String nomeBairroNaoExiste = "Este Bairro não Existe";
        Bairro bairroExiste = new Bairro(nomeBairroExiste, new BigDecimal(5000));
        Bairro bairroNaoExiste = new Bairro(nomeBairroNaoExiste, new BigDecimal(5000));

        BairroRepository mockBairroRepository = Mockito.mock(BairroRepository.class);
        Mockito.doNothing().when(mockBairroRepository).salvar(bairroExiste);
        Mockito.when(mockBairroRepository.findByNome(isA(String.class))).thenReturn(null,bairroExiste,bairroExiste);

        BairroService bairroService = new BairroService(mockBairroRepository);

        //act
        bairroService.salvar(bairroExiste);
        RepositoryException excecaoEsperada = assertThrows(
                RepositoryException.class,
                () -> bairroService.salvar(bairroNaoExiste) //act
        );

        //assertion
        assertEquals(bairroExiste, bairroService.findByNome(bairroExiste.getNome()));
        assertTrue(excecaoEsperada.getMessage().contains("Ja existe bairro com esse nome."));
        assertNotEquals(bairroNaoExiste, bairroService.findByNome(bairroNaoExiste.getNome()));
    }

    /*** <b>US-0003:<b><br>
     * Verifique se o maior cômodo foi realmente devolvido.<br>
     * Retorna o cômodo com o maior tamanho
     *
    @Test
    public void verificaSeComodoMaiorEstaCorreto() {
        //arrange
        String casaComodo = "Casa";
        String bairro = "bairro";
        Comodo comodo1x1 = new Comodo("1", 1.0, 1.0);
        Comodo comodo5x5 = new Comodo("2", 5.0, 5.0);
        Comodo comodo5x1 = new Comodo("3", 5.0, 1.0);
        Comodo comodo1x5 = new Comodo("4", 1.0, 5.0);
        List<Comodo> comodos = new ArrayList<>(Arrays.asList(comodo1x1, comodo5x1, comodo5x5, comodo1x5));
        Casa casa = new Casa(casaComodo, bairro, comodos);
        String maiorComodo = "O maior comodo da casa "
                + casaComodo + ": 2 com área de " + (25.0) + " metros quadrados.";

        CasaRepository mockCasaRepository = Mockito.mock(CasaRepository.class);
        Mockito.when(mockCasaRepository.findByNome(casaComodo)).thenReturn(casa);
        CasaService casaService = new CasaService(null, mockCasaRepository);

        //act
        String result = casaService.getMaiorComodo(casaComodo);

        //assertion
        assertEquals(result, maiorComodo);
    }

    /*** <b>US-0004:<b><br>
     * Verifique se de fato o total de metros quadrados por cômodo está correto.<br>
     * Retorna o cálculo correto do total de metros quadrados de um cômodo.
     *
    @Test
    public void verificaTotalM2DoComodo() {
        //arrange
        String casaComodo = "Casa";
        String bairro = "bairro";
        Comodo comodo1x1 = new Comodo("1", 1.0, 1.0);
        Comodo comodo5x5 = new Comodo("2", 5.0, 5.0);
        Comodo comodo5x1 = new Comodo("3", 5.0, 1.0);
        Comodo comodo1x5 = new Comodo("4", 1.0, 5.0);
        List<Comodo> comodos = new ArrayList<>(Arrays.asList(comodo1x1, comodo5x1, comodo5x5, comodo1x5));
        Casa casa = new Casa(casaComodo, bairro, comodos);
        String areaComodos = "A area de cada comodo da casa " + casaComodo
                + " é: <br>Comodo: 1 com "
                + (1.0) + " metros quadrados<br>Comodo: 3 com " + (5.0)
                + " metros quadrados<br>Comodo: 2 com " + (25.0)
                + " metros quadrados<br>Comodo: 4 com " + (5.0)
                + " metros quadrados<br>";

        CasaRepository mockCasaRepository = Mockito.mock(CasaRepository.class);
        Mockito.when(mockCasaRepository.findByNome(casaComodo)).thenReturn(casa);
        CasaService casaService = new CasaService(null, mockCasaRepository);

        //act
        String result = casaService.getAreaComodos(casaComodo);

        //assertion
        assertEquals(result, areaComodos);
    }

    /*** <b>US-0005:<b><br>
     * Verifique se de fato todas casas cadastradas estão gravadas.<br>
     * Retorna lista com todas casas salvas no repositório.
     *
    @Test
    public void verificaListaDeCasasCadastradas() {
        //arrange
        Casa casa1 = new Casa("casa 1", "bairro", new ArrayList<>());
        Casa casa2 = new Casa("casa 2", "bairro", new ArrayList<>());
        Casa casa3 = new Casa("casa 1", "bairro", new ArrayList<>());
        Casa casa4 = new Casa("casa 2", "bairro", new ArrayList<>());
        List<Casa> casas = new ArrayList<>(Arrays.asList(casa1, casa2, casa3, casa4));

        BairroService mockBairroService = Mockito.mock(BairroService.class);
        Mockito.when(mockBairroService.findByNome("bairro")).thenReturn(new Bairro("bairro", new BigDecimal(1)));

        CasaRepository mockCasaRepository = Mockito.mock(CasaRepository.class);
        Mockito.doNothing().when(mockCasaRepository).salvar(isA(Casa.class));
        Mockito.when(mockCasaRepository.findAll()).thenReturn(casas);

        CasaService casaService = new CasaService(mockBairroService, mockCasaRepository);

        //act
        casaService.salvarCasa(casa1);
        casaService.salvarCasa(casa2);
        casaService.salvarCasa(casa3);
        casaService.salvarCasa(casa4);

        //assertion
        assertEquals(casas, casaService.findAll());
    }

    /*** <b>US-0006:<b><br>
     * Verifique se de fato todos os bairros cadastrados estão gravados.<br>
     * Retorna lista com todos os bairros salvos no repositório.
     *
    @Test
    public void verificaListaDeBairrosCadastrados() {
        //arrange
        Bairro b1 = new Bairro("b 1", new BigDecimal(55));
        Bairro b2 = new Bairro("b 2", new BigDecimal(6));
        Bairro b3 = new Bairro("b 3", new BigDecimal(557));
        Bairro b4 = new Bairro("b 4", new BigDecimal(88));
        List<Bairro> bairros = new ArrayList<>(Arrays.asList(b1, b2, b3, b4));

        BairroRepository mockBairroRepository = Mockito.mock(BairroRepository.class);
        Mockito.doNothing().when(mockBairroRepository).salvar(isA(Bairro.class));
        Mockito.when(mockBairroRepository.findAll()).thenReturn(bairros);

        BairroService bairroService = new BairroService(mockBairroRepository);

        //act
        bairroService.salvar(b1);
        bairroService.salvar(b2);
        bairroService.salvar(b3);
        bairroService.salvar(b4);

        //assertion
        assertEquals(bairros, bairroService.findAll());
    }

    /*** <b>US-0007:<b><br>
     * Verifique se o valor da casa está correto.<br>
     * Retorna o valor da casa, com base no metro quadrado do bairro.
     *
    @Test
    public void verificaSeValorDaCasaEstaCerto() {
        //arrange
        String casaComodo = "Casa";
        String nomeBairro = "bairro";
        Comodo comodo1x1 = new Comodo("1", 1.0, 1.0);
        Comodo comodo5x5 = new Comodo("2", 5.0, 5.0);
        Comodo comodo5x1 = new Comodo("3", 5.0, 1.0);
        Comodo comodo1x5 = new Comodo("4", 1.0, 5.0);
        List<Comodo> comodos = new ArrayList<>(Arrays.asList(comodo1x1, comodo5x1, comodo5x5, comodo1x5));
        Casa casa = new Casa(casaComodo, nomeBairro, comodos);
        BigDecimal valorMetroQuadrado = new BigDecimal(200);
        Bairro bairro = new Bairro(nomeBairro, valorMetroQuadrado);

        BairroService mockBairroService = Mockito.mock(BairroService.class);
        Mockito.when(mockBairroService.findByNome(nomeBairro)).thenReturn(bairro);

        BigDecimal valorCasa = new BigDecimal(36);
        valorCasa = valorCasa.multiply(valorMetroQuadrado);
        String valorDaCasa = "O valor da casa é: " + NumberFormat.getCurrencyInstance().format(valorCasa);

        CasaRepository mockCasaRepository = Mockito.mock(CasaRepository.class);
        Mockito.when(mockCasaRepository.findByNome(casaComodo)).thenReturn(casa);
        CasaService casaService = new CasaService(mockBairroService, mockCasaRepository);

        //act
        String result = casaService.getValorCasa(casaComodo);

        //assertion
        assertEquals(result, valorDaCasa);
    }

    /*** <b>US-0008:<b><br>
     * Confirmação dos validadores de campo da classe bairro
     *
    @Test
    public void validaCamposBairro() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        // <- if true apenas para "colapse" do código
        if (true) {
            // Valida nome do bairro = null
            BairroDto bairro = new BairroDto(null, new BigDecimal(1));
            Set<ConstraintViolation<BairroDto>> violations = validator.validate(bairro);
            StringBuilder g = new StringBuilder();
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida nome do bairro = "" (vazio)
            bairro = new BairroDto("", new BigDecimal(1));
            violations = validator.validate(bairro);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida nome do bairro = "Aa" (menos de 3 caracters)
            bairro = new BairroDto("Aa", new BigDecimal(1));
            violations = validator.validate(bairro);
            g.setLength(0);
            violations.forEach(p -> g .append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida nome do bairro (mais de 45 caracters)
            bairro = new BairroDto("AsdfghjklpAsdfghjklpAsdfghjklpAsdfghjklpAsdfghjklp", new BigDecimal(1));
            violations = validator.validate(bairro);
            g.setLength(0);
            violations.forEach(p -> g .append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida nome do bairro = "ahgfgghfgjjj" (nome não começa com letra maiúscula)
            bairro = new BairroDto("ahgfgghfgjjj", new BigDecimal(1));
            violations = validator.validate(bairro);
            g.setLength(0);
            violations.forEach(p -> g .append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida nome do bairro = "Aahgfgghfg 66" (contém letras nnumeros e espaço, e comeá com letra maiuscula)
            bairro = new BairroDto("Aahgfgghfg 66", new BigDecimal(1));
            violations = validator.validate(bairro);
            g.setLength(0);
            violations.forEach(p -> g .append(p.getMessage()));
            System.out.println(g);
            assertTrue(violations.isEmpty());
        } // validacoes nome bairro
        if (true) {
            // valida valor do metro quadrado = 0
            BairroDto bairro = new BairroDto("Aahgfgghfg 66", new BigDecimal(0));
            Set<ConstraintViolation<BairroDto>> violations = validator.validate(bairro);
            StringBuilder g = new StringBuilder();
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida valor do metro quadrado negativo
            bairro = new BairroDto("Aahgfgghfg 66", new BigDecimal(-1));
            violations = validator.validate(bairro);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida valor do metro quadrado nulo
            bairro = new BairroDto("Aahgfgghfg 66", null);
            violations = validator.validate(bairro);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida valor do metro quadrado com mais de 2 digitos decimais
            bairro = new BairroDto("Aahgfgghfg 66", new BigDecimal("10.999"));
            violations = validator.validate(bairro);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida valor do metro quadrado com mais de 11 digitos inteiros
            bairro = new BairroDto("Aahgfgghfg 66", new BigDecimal("12345678901234.9"));
            violations = validator.validate(bairro);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida valor do metro quadrado corretamente
            bairro = new BairroDto("Aahgfgghfg 66", new BigDecimal("199990.10"));
            violations = validator.validate(bairro);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertTrue(violations.isEmpty());
        } // validaçoes valor metro quadrado
    }

    /*** <b>US-0009:<b><br>
     * Confirmação dos validadores de campo da classe Casa e Comodo
     *
    @Test
    public void validaCamposCasa() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        ComodoDto comodoValido = new ComodoDto("Aghjytrdsfgh", 5.0, 5.0);
        List<ComodoDto> comodos = new ArrayList<>();
        comodos.add(comodoValido);
        // <- if true apenas para "colapse" do código
        if (true) {
            // Valida nome da casa = null
            CasaDto casa = new CasaDto(null, "Afghjkuytredd", comodos);
            Set<ConstraintViolation<CasaDto>> violations = validator.validate(casa);
            StringBuilder g = new StringBuilder();
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida nome da casa = "" (vazio)
            casa = new CasaDto("", "Afghjkuytredd", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida nome da casa = "Aa" (menos de 3 caracters)
            casa = new CasaDto("Aa", "Afghjkuytredd", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g .append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida nome da casa (mais de 30 caracters)
            casa = new CasaDto("AsdfghjklpAsdfghjklpAsdfghjklpAsdfghjklpAsdfghjklp", "Afghjkuytredd", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g .append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida nome da casa = "ahgfgghfgjjj" (nome não começa com letra maiúscula)
            casa = new CasaDto("ahgfgghfgjjj", "Afghjkuytredd", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g .append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida nome da casa = "Aahgfgghfg 66" (contém letras nnumeros e espaço, e começa com letra maiuscula)
            casa = new CasaDto("Aahgfgghfg 66", "Afghjkuytredd", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g .append(p.getMessage()));
            System.out.println(g);
            assertTrue(violations.isEmpty());
        } // validacoes nome casa
        if (true) {
            // Valida nome do bairro = null
            CasaDto casa = new CasaDto("Ahhhhhhhhh", null, comodos);
            Set<ConstraintViolation<CasaDto>> violations = validator.validate(casa);
            StringBuilder g = new StringBuilder();
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida nome do bairro = "" (vazio)
            casa = new CasaDto("Ahhhhhhhhh", "", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida nome do bairro = "Aa" (menos de 3 caracters)
            casa = new CasaDto("Ahhhhhhhhh", "Aa", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g .append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida nome do bairro (mais de 45 caracters)
            casa = new CasaDto("Ahhhhhhhhh","AsdfghjklpAsdfghjklpAsdfghjklpAsdfghjklpAsdfghjklp", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g .append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida nome do bairro = "ahgfgghfgjjj" (nome não começa com letra maiúscula)
            casa = new CasaDto("Ahhhhhhhhh", "ahgfgghfgjjj", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g .append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida nome do bairro = "Aahgfgghfg 66" (contém letras nnumeros e espaço, e começa com letra maiuscula)
            casa = new CasaDto("Ahhhhhhhhh","Aahgfgghfg 66", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g .append(p.getMessage()));
            System.out.println(g);
            assertTrue(violations.isEmpty());
        } // validacoes nome bairro
        if (true) {
            // Valida nome do comodo = null
            comodos = new ArrayList<>();
            comodoValido = new ComodoDto(null, 1.0, 1.0);
            comodos.add(comodoValido);
            CasaDto casa = new CasaDto("Ahhhhhhhhh", "Ahhhhhhhhh", comodos);
            Set<ConstraintViolation<CasaDto>> violations = validator.validate(casa);
            StringBuilder g = new StringBuilder();
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // Valida nome do comodo = ""
            comodos = new ArrayList<>();
            comodoValido = new ComodoDto("", 1.0, 1.0);
            comodos.add(comodoValido);
            casa = new CasaDto("Ahhhhhhhhh", "Ahhhhhhhhh", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // Valida nome do comodo com menos de 3 caracteres
            comodos = new ArrayList<>();
            comodoValido = new ComodoDto("Aa", 1.0, 1.0);
            comodos.add(comodoValido);
            casa = new CasaDto("Ahhhhhhhhh", "Ahhhhhhhhh", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // Valida nome do comodo com mais de 30 caracteres
            comodos = new ArrayList<>();
            comodoValido = new ComodoDto("Asdfghjkloiuytredfghjmnbvcxzasdewqasdfghjmn", 1.0, 1.0);
            comodos.add(comodoValido);
            casa = new CasaDto("Ahhhhhhhhh", "Ahhhhhhhhh", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // Valida nome do comodo deve começar com maiuscula
            comodos = new ArrayList<>();
            comodoValido = new ComodoDto("aaaaaaaaa", 1.0, 1.0);
            comodos.add(comodoValido);
            casa = new CasaDto("Ahhhhhhhhh", "Ahhhhhhhhh", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // Valida nome do comodo (contém letras nnumeros e espaço, e começa com letra maiuscula)
            comodos = new ArrayList<>();
            comodoValido = new ComodoDto("Ahgfgur 66 hgfg", 1.0, 1.0);
            comodos.add(comodoValido);
            casa = new CasaDto("Ahhhhhhhhh", "Ahhhhhhhhh", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertTrue(violations.isEmpty());
        } // valiadoes nome comodo
        if (true) {
            // Valida largura = 0
            comodos = new ArrayList<>();
            comodoValido = new ComodoDto("Agfdtrfvd", 0.0, 1.0);
            comodos.add(comodoValido);
            CasaDto casa = new CasaDto("Ahhhhhhhhh", "Ahhhhhhhhh", comodos);
            Set<ConstraintViolation<CasaDto>> violations = validator.validate(casa);
            StringBuilder g = new StringBuilder();
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida largura negativa
            comodos = new ArrayList<>();
            comodoValido = new ComodoDto("Agfdtrfvd", -1.9, 1.0);
            comodos.add(comodoValido);
            casa = new CasaDto("Ahhhhhhhhh", "Ahhhhhhhhh", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida largura nula
            comodos = new ArrayList<>();
            comodoValido = new ComodoDto("Agfdtrfvd", null, 1.0);
            comodos.add(comodoValido);
            casa = new CasaDto("Ahhhhhhhhh", "Ahhhhhhhhh", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida largura maior que 25m
            comodos = new ArrayList<>();
            comodoValido = new ComodoDto("Agfdtrfvd", 30.0, 1.0);
            comodos.add(comodoValido);
            casa = new CasaDto("Ahhhhhhhhh", "Ahhhhhhhhh", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida largura OK
            comodos = new ArrayList<>();
            comodoValido = new ComodoDto("Agfdtrfvd", 21.9, 1.0);
            comodos.add(comodoValido);
            casa = new CasaDto("Ahhhhhhhhh", "Ahhhhhhhhh", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertTrue(violations.isEmpty());
        } // validaçoes largura comodo
        if (true) {
            // Valida comprimento = 0
            comodos = new ArrayList<>();
            comodoValido = new ComodoDto("Agfdtrfvd", 1.0, 0.0);
            comodos.add(comodoValido);
            CasaDto casa = new CasaDto("Ahhhhhhhhh", "Ahhhhhhhhh", comodos);
            Set<ConstraintViolation<CasaDto>> violations = validator.validate(casa);
            StringBuilder g = new StringBuilder();
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida comprimento negativa
            comodos = new ArrayList<>();
            comodoValido = new ComodoDto("Agfdtrfvd", 1.9, -1.0);
            comodos.add(comodoValido);
            casa = new CasaDto("Ahhhhhhhhh", "Ahhhhhhhhh", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida comprimento nula
            comodos = new ArrayList<>();
            comodoValido = new ComodoDto("Agfdtrfvd", 1.0, null);
            comodos.add(comodoValido);
            casa = new CasaDto("Ahhhhhhhhh", "Ahhhhhhhhh", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida comprimento maior que 33m
            comodos = new ArrayList<>();
            comodoValido = new ComodoDto("Agfdtrfvd", 6.0, 51.0);
            comodos.add(comodoValido);
            casa = new CasaDto("Ahhhhhhhhh", "Ahhhhhhhhh", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertFalse(violations.isEmpty());
            // valida comprimento OK
            comodos = new ArrayList<>();
            comodoValido = new ComodoDto("Agfdtrfvd", 21.9, 1.0);
            comodos.add(comodoValido);
            casa = new CasaDto("Ahhhhhhhhh", "Ahhhhhhhhh", comodos);
            violations = validator.validate(casa);
            g.setLength(0);
            violations.forEach(p -> g.append(p.getMessage()));
            System.out.println(g);
            assertTrue(violations.isEmpty());
        } // validaçoes comprimento comodo
    }

    /*** <b>US-0010:<b><br>
     * Verifique se o bairro de entrada existe no repositório de bairros, co momento do cadastro de casa.<br>
     * <i>Se cumprir:</i><br>
     * Permite continuar normalmente.<br>
     * <i>Se não cumprir:</i><br>
     * Relate a incompatibilidade com uma exceção.
     *
    @Test
    public void verificaBairroExistenteNaInclusaoDeCasas() {
        //arrange
        String bairroExiste = "Este Bairro Existe";
        String bairroNaoExiste = "Este Bairro não Existe";
        Bairro bairro = new Bairro(bairroExiste, new BigDecimal(5000));
        Casa casaComBairroExistente = new Casa("casa 1", bairroExiste, new ArrayList<>());
        Casa casaComBairroInexistente = new Casa("casa 2", bairroNaoExiste, new ArrayList<>());

        BairroService mockBairroService = Mockito.mock(BairroService.class);
        Mockito.when(mockBairroService.findByNome(isA(String.class)))
                .thenReturn(bairro)
                .thenThrow( new RepositoryException("Bairro inexistente."));

        CasaRepository mockCasaRepository = Mockito.mock(CasaRepository.class);
        Mockito.doNothing().when(mockCasaRepository).salvar(casaComBairroExistente);
        Mockito.when(mockCasaRepository.findByNome(isA(String.class))).thenReturn(null,casaComBairroExistente,casaComBairroExistente);

        CasaService casaService = new CasaService(mockBairroService, mockCasaRepository);

        //act
        casaService.salvarCasa(casaComBairroExistente);
        RepositoryException excecaoEsperada = assertThrows(
                RepositoryException.class,
                () -> casaService.salvarCasa(casaComBairroInexistente) //act
        );

        //assertion
        assertEquals(casaComBairroExistente, casaService.findByNome(casaComBairroExistente.getNome()));
        assertTrue(excecaoEsperada.getMessage().contains("Bairro inexistente."));
        assertNotEquals(casaComBairroInexistente, casaService.findByNome(casaComBairroInexistente.getNome()));
    }

    /*** <b>US-0011:<b><br>
     * Verifique se a casa de entrada existe no repositório de casas.<br>
     * <i>Se cumprir:</i><br>
     * Permite continuar normalmente.<br>
     * <i>Se não cumprir:</i><br>
     * Relate a incompatibilidade com uma exceção.
     *
    @Test
    public void verificaCasaExistenteNaInclusaoDeCasas() {
        //arrange
        Bairro bairro = new Bairro("bairro", new BigDecimal("10"));
        Casa casaJaCadastrada = new Casa("casa existe", "bairro", new ArrayList<>());

        BairroService mockBairroService = Mockito.mock(BairroService.class);
        Mockito.when(mockBairroService.findByNome(isA(String.class)))
                .thenReturn(bairro)
                .thenThrow( new RepositoryException("Bairro inexistente."));

        CasaRepository mockCasaRepository = Mockito.mock(CasaRepository.class);
//        Mockito.doNothing().when(mockCasaRepository).salvar(casaComBairroExistente);
        Mockito.when(mockCasaRepository.findByNome(isA(String.class))).thenReturn(casaJaCadastrada);

        CasaService casaService = new CasaService(mockBairroService, mockCasaRepository);

        //act
        RepositoryException excecaoEsperada = assertThrows(
                RepositoryException.class,
                () -> casaService.salvarCasa(casaJaCadastrada) //act
        );

        //assertion
        assertTrue(excecaoEsperada.getMessage().contains("Já existe uma casa com esse nome"));
    }*/
}
