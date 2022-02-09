package com.mercadolibre.w4g9projetofinal.test.unit;

import com.mercadolibre.w4g9projetofinal.entity.*;
import com.mercadolibre.w4g9projetofinal.exceptions.BusinessException;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.exceptions.SectionManagementException;
import com.mercadolibre.w4g9projetofinal.repository.InboundOrderRepository;
import com.mercadolibre.w4g9projetofinal.security.entity.UserSS;
import com.mercadolibre.w4g9projetofinal.service.InboundOrderService;
import com.mercadolibre.w4g9projetofinal.service.RepresentativeService;
import com.mercadolibre.w4g9projetofinal.service.SectionService;
import com.mercadolibre.w4g9projetofinal.service.SellerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class InboundOrderServiceTest {

    @Test
    public void verificaBuscaTodasOrdensDeEntrada(){
        // <-- ARRANGE -->
        List<InboundOrder> ordensDeEntrada = new ArrayList<>();
        // Mocks - Class
        InboundOrderRepository inboundOrderRepository = Mockito.mock(InboundOrderRepository.class);
        SellerService sellerService = Mockito.mock(SellerService.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        // Mock - Actions
        Mockito.when(inboundOrderRepository.findAll()).thenReturn(ordensDeEntrada);
        // Service
        InboundOrderService io = new InboundOrderService(inboundOrderRepository,sellerService,
                sectionService,representativeService);

        // <-- ACT -->
        List<InboundOrder> ordensDeEntradaRetornadas = io.findAll();

        // <-- ASSERTION -->
        Assertions.assertEquals(ordensDeEntrada,ordensDeEntradaRetornadas);
    }

    @Test
    public void verificaBuscaDeUmaOrdemDeEntradaQueNaoExiste(){
        // <-- ARRANGE -->
        Long idOrdemEntrada = 1L;
        // Mocks - Class
        InboundOrderRepository inboundOrderRepository = Mockito.mock(InboundOrderRepository.class);
        SellerService sellerService = Mockito.mock(SellerService.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        // Mock - Actions
        Mockito.when(inboundOrderRepository.findById(idOrdemEntrada)).thenReturn(Optional.empty());
        // Service
        InboundOrderService io = new InboundOrderService(inboundOrderRepository,sellerService,
                sectionService,representativeService);

        // <-- ACT -->
        ObjectNotFoundException excecaoEsperada = Assertions.assertThrows(
                ObjectNotFoundException.class,
                () -> io.findById(idOrdemEntrada) //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage().contains("Inbound Order not found! Please check the id."));
    }

    @Test
    public void verificaBuscaDeUmaOrdemDeEntrada(){
        // <-- ARRANGE -->
        Long idOrdemEntrada = 1L;
        InboundOrder ordemDeEntrada = new InboundOrder();
        // Mocks - Class
        InboundOrderRepository inboundOrderRepository = Mockito.mock(InboundOrderRepository.class);
        SellerService sellerService = Mockito.mock(SellerService.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        // Mock - Actions
        Mockito.when(inboundOrderRepository.findById(idOrdemEntrada)).thenReturn(Optional.of(ordemDeEntrada));
        // Service
        InboundOrderService io = new InboundOrderService(inboundOrderRepository,sellerService,
                sectionService,representativeService);

        // <-- ACT -->
        InboundOrder ordemDeEntradaRetornada = io.findById(idOrdemEntrada);

        // <-- ASSERTION -->
        Assertions.assertEquals(ordemDeEntrada,ordemDeEntradaRetornada);
    }

    @Test
    public void verificaInclusaoDeOrdemDeEntrada_QuandoJaExisteOrdemNoRepositorio(){
        // <-- ARRANGE -->
        Long idOrdemEntrada = 1L;
        InboundOrder ordemDeEntrada = new InboundOrder();
        Section setor = new Section();
        ordemDeEntrada.setId(idOrdemEntrada);
        ordemDeEntrada.setSection(setor);
        UserSS usuario = new UserSS(1L, null, null, new HashSet<>());
        // Mocks - Class
        InboundOrderRepository inboundOrderRepository = Mockito.mock(InboundOrderRepository.class);
        SellerService sellerService = Mockito.mock(SellerService.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        // Mock - Actions
        Mockito.when(inboundOrderRepository.findById(idOrdemEntrada)).thenReturn(Optional.of(ordemDeEntrada));
        Mockito.when(sectionService.findById(Mockito.anyLong())).thenReturn(setor);
        // Service
        InboundOrderService io = new InboundOrderService(inboundOrderRepository,sellerService,
                sectionService,representativeService);

        // <-- ACT -->
        BusinessException excecaoEsperada = Assertions.assertThrows(
                BusinessException.class,
                () -> io.inboundOrderManager(usuario, ordemDeEntrada,false) //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage().contains("InboundOrder already exists, please update via PUT"));
    }

    @Test
    public void verificaInclusaoDeOrdemDeEntrada_QuandoSetorInformadoNaoFazParteDoArmazemInformado(){
        // <-- ARRANGE -->
        UserSS usuario = new UserSS(1L, null, null, new HashSet<>());
        Long idOrdemEntrada = 1L;
        Long idSetor = 1L;
        InboundOrder ordemDeEntrada = new InboundOrder();
        Section setor = new Section();
        Warehouse armazem = new Warehouse();
        armazem.setId(1L);
        setor.setId(idSetor);
        setor.setWarehouse(armazem);
        ordemDeEntrada.setId(idOrdemEntrada);
        ordemDeEntrada.setSection(setor);
        Section setorRepo = new Section();
        Warehouse armazemRepo = new Warehouse();
        armazemRepo.setId(2L);
        setorRepo.setId(idSetor);
        setorRepo.setWarehouse(armazemRepo);
        // Mocks - Class
        InboundOrderRepository inboundOrderRepository = Mockito.mock(InboundOrderRepository.class);
        SellerService sellerService = Mockito.mock(SellerService.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        // Mock - Actions
        Mockito.when(inboundOrderRepository.findById(idOrdemEntrada)).thenReturn(Optional.empty());
        Mockito.when(sectionService.findById(Mockito.anyLong())).thenReturn(setorRepo);
        // Service
        InboundOrderService io = new InboundOrderService(inboundOrderRepository,sellerService,
                sectionService,representativeService);

        // <-- ACT -->
        SectionManagementException excecaoEsperada = Assertions.assertThrows(
                SectionManagementException.class,
                () -> io.inboundOrderManager(usuario, ordemDeEntrada,false) //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage().contains("Section: 1 does not belong to the Warehouse: 1"));
    }

    @Test
    public void verificaInclusaoDeOrdemDeEntrada_QuandoRepresentanteInformadoNaoFazParteDoArmazemInformado(){
        // <-- ARRANGE -->
        Long idUsuario = 1L;
        UserSS usuario = new UserSS(idUsuario, null, null, new HashSet<>());
        Representative representante = new Representative();
        representante.setId(idUsuario);
        representante.setName("Representante");
        Warehouse armazemRepresentante = new Warehouse();
        armazemRepresentante.setId(2L);
        representante.setWarehouse(armazemRepresentante);
        Long idOrdemEntrada = 1L;
        Long idSetor = 1L;
        Long idArmazem = 1L;
        InboundOrder ordemDeEntrada = new InboundOrder();
        Section setor = new Section();
        Warehouse armazem = new Warehouse();
        armazem.setId(idArmazem);
        armazem.setName("Armazem");
        setor.setId(idSetor);
        setor.setWarehouse(armazem);
        ordemDeEntrada.setId(idOrdemEntrada);
        ordemDeEntrada.setSection(setor);
        // Mocks - Class
        InboundOrderRepository inboundOrderRepository = Mockito.mock(InboundOrderRepository.class);
        SellerService sellerService = Mockito.mock(SellerService.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        // Mock - Actions
        Mockito.when(inboundOrderRepository.findById(idOrdemEntrada)).thenReturn(Optional.empty());
        Mockito.when(sectionService.findById(Mockito.anyLong())).thenReturn(setor);
        Mockito.when(representativeService.findById(Mockito.anyLong())).thenReturn(representante);
        // Service
        InboundOrderService io = new InboundOrderService(inboundOrderRepository,sellerService,
                sectionService,representativeService);

        // <-- ACT -->
        BusinessException excecaoEsperada = Assertions.assertThrows(
                BusinessException.class,
                () -> io.inboundOrderManager(usuario, ordemDeEntrada,false) //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage()
                .contains("Representative: Representante not part of the Warehouse: Armazem"));
    }

    @Test
    public void verificaInclusaoDeOrdemDeEntrada(){
        // <-- ARRANGE -->
        Long idUsuario = 1L;
        UserSS usuario = new UserSS(idUsuario, null, null, new HashSet<>());
        Representative representante = new Representative();
        representante.setId(idUsuario);
        Long idOrdemEntrada = 1L;
        Long idSetor = 1L;
        Long idArmazem = 1L;
        InboundOrder ordemDeEntrada = new InboundOrder();
        Section setor = new Section();
        Warehouse armazem = new Warehouse();
        armazem.setId(idArmazem);
        setor.setId(idSetor);
        setor.setWarehouse(armazem);
        ordemDeEntrada.setId(idOrdemEntrada);
        ordemDeEntrada.setSection(setor);
        representante.setWarehouse(armazem);
        // Mocks - Class
        InboundOrderRepository inboundOrderRepository = Mockito.mock(InboundOrderRepository.class);
        SellerService sellerService = Mockito.mock(SellerService.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        // Mock - Actions
        Mockito.when(inboundOrderRepository.findById(idOrdemEntrada)).thenReturn(Optional.empty());
        Mockito.when(sectionService.findById(Mockito.anyLong())).thenReturn(setor);
        Mockito.when(representativeService.findById(Mockito.anyLong())).thenReturn(representante);
        Mockito.when(sellerService.verifySellerInInboundOrder(Mockito.any())).thenReturn(new Seller());
        Mockito.when(inboundOrderRepository.save(ordemDeEntrada)).thenReturn(ordemDeEntrada);
        Mockito.when(sectionService.validateBatchSection(Mockito.any(), Mockito.any(), Mockito.anyBoolean()))
                .thenReturn(setor);
        // Service
        InboundOrderService io = new InboundOrderService(inboundOrderRepository,sellerService,
                sectionService,representativeService);

        // <-- ACT -->
        InboundOrder ordemDeEntradaRetornada = io.inboundOrderManager(usuario, ordemDeEntrada,false);

        // <-- ASSERTION -->
        Assertions.assertEquals(ordemDeEntrada,ordemDeEntradaRetornada);
    }

    @Test
    public void verificaAlteracaoDeOrdemDeEntrada_QuandoSetorInformadoEstaDiferenteDoJaCadastrado(){
        // <-- ARRANGE -->
        Long idOrdemEntrada = 1L;
        InboundOrder ordemDeEntrada = new InboundOrder();
        Section setor = new Section();
        setor.setId(1L);
        ordemDeEntrada.setId(idOrdemEntrada);
        ordemDeEntrada.setSection(setor);
        InboundOrder ordemDeEntradaRepo = new InboundOrder();
        Section setorRepo = new Section();
        setorRepo.setId(2L);
        ordemDeEntradaRepo.setId(idOrdemEntrada);
        ordemDeEntradaRepo.setSection(setorRepo);
        UserSS usuario = new UserSS(1L, null, null, new HashSet<>());
        // Mocks - Class
        InboundOrderRepository inboundOrderRepository = Mockito.mock(InboundOrderRepository.class);
        SellerService sellerService = Mockito.mock(SellerService.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        // Mock - Actions
        Mockito.when(sectionService.findById(Mockito.anyLong())).thenReturn(setor);
        Mockito.when(inboundOrderRepository.findById(idOrdemEntrada)).thenReturn(Optional.of(ordemDeEntradaRepo));
        // Service
        InboundOrderService io = new InboundOrderService(inboundOrderRepository,sellerService,
                sectionService,representativeService);

        // <-- ACT -->
        SectionManagementException excecaoEsperada = Assertions.assertThrows(
                SectionManagementException.class,
                () -> io.inboundOrderManager(usuario, ordemDeEntrada,true) //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage().contains("Cannot change Section from an Inbound Order"));
    }

    @Test
    public void verificaAlteracaoDeOrdemDeEntrada_QuandoNaoExisteOrdemNoRepositorio(){
        // <-- ARRANGE -->
        Long idOrdemEntrada = 1L;
        InboundOrder ordemDeEntrada = new InboundOrder();
        Section setor = new Section();
        ordemDeEntrada.setId(idOrdemEntrada);
        ordemDeEntrada.setSection(setor);
        UserSS usuario = new UserSS(1L, null, null, new HashSet<>());
        // Mocks - Class
        InboundOrderRepository inboundOrderRepository = Mockito.mock(InboundOrderRepository.class);
        SellerService sellerService = Mockito.mock(SellerService.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        // Mock - Actions
        Mockito.when(inboundOrderRepository.findById(idOrdemEntrada)).thenReturn(Optional.empty());
        Mockito.when(sectionService.findById(Mockito.anyLong())).thenReturn(setor);
        // Service
        InboundOrderService io = new InboundOrderService(inboundOrderRepository,sellerService,
                sectionService,representativeService);

        // <-- ACT -->
        ObjectNotFoundException excecaoEsperada = Assertions.assertThrows(
                ObjectNotFoundException.class,
                () -> io.inboundOrderManager(usuario, ordemDeEntrada,true) //act
        );

        // <-- ASSERTION -->
        Assertions.assertTrue(excecaoEsperada.getMessage().contains("Inbound Order not found! Please check the id."));
    }

    @Test
    public void verificaAlteracaoDeOrdemDeEntrada(){
        // <-- ARRANGE -->
        Long idUsuario = 1L;
        UserSS usuario = new UserSS(idUsuario, null, null, new HashSet<>());
        Representative representante = new Representative();
        representante.setId(idUsuario);
        Long idOrdemEntrada = 1L;
        Long idSetor = 1L;
        Long idArmazem = 1L;
        InboundOrder ordemDeEntrada = new InboundOrder();
        Batch b = new Batch();
        List<Batch> lb = new ArrayList<>();
        lb.add(b);
        ordemDeEntrada.setBatchList(lb);
        Section setor = new Section();
        Warehouse armazem = new Warehouse();
        armazem.setId(idArmazem);
        setor.setId(idSetor);
        setor.setWarehouse(armazem);
        ordemDeEntrada.setId(idOrdemEntrada);
        ordemDeEntrada.setSection(setor);
        representante.setWarehouse(armazem);
        // Mocks - Class
        InboundOrderRepository inboundOrderRepository = Mockito.mock(InboundOrderRepository.class);
        SellerService sellerService = Mockito.mock(SellerService.class);
        SectionService sectionService = Mockito.mock(SectionService.class);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        // Mock - Actions
        Mockito.when(sectionService.findById(Mockito.anyLong())).thenReturn(setor);
        Mockito.when(inboundOrderRepository.findById(idOrdemEntrada)).thenReturn(Optional.of(ordemDeEntrada));
        Mockito.when(representativeService.findById(Mockito.anyLong())).thenReturn(representante);
        Mockito.when(sellerService.verifySellerInInboundOrder(Mockito.any())).thenReturn(new Seller());
        Mockito.when(sectionService.updateOldSectionStock(Mockito.any(), Mockito.any())).thenReturn(lb);
        Mockito.when(inboundOrderRepository.save(Mockito.any())).thenReturn(ordemDeEntrada);
        Mockito.when(sectionService.validateBatchSection(Mockito.any(), Mockito.any(), Mockito.anyBoolean()))
                .thenReturn(setor);
        // Service
        InboundOrderService io = new InboundOrderService(inboundOrderRepository,sellerService,
                sectionService,representativeService);

        // <-- ACT -->
        InboundOrder ordemDeEntradaRetornada = io.inboundOrderManager(usuario, ordemDeEntrada,true);

        // <-- ASSERTION -->
        Assertions.assertEquals(ordemDeEntrada,ordemDeEntradaRetornada);
    }
}
