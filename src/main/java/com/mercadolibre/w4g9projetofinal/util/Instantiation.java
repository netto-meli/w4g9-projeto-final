package com.mercadolibre.w4g9projetofinal.util;

import com.mercadolibre.w4g9projetofinal.entity.*;
import com.mercadolibre.w4g9projetofinal.entity.enums.AdvertiseStatus;
import com.mercadolibre.w4g9projetofinal.entity.enums.Profile;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.entity.enums.RepresentativeJob;
import com.mercadolibre.w4g9projetofinal.exceptions.ExistingUserException;
import com.mercadolibre.w4g9projetofinal.repository.*;
import com.mercadolibre.w4g9projetofinal.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Configuration
public class Instantiation implements CommandLineRunner {

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

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Buyer adm = new Buyer(null, "admin", "Administrator", "adm@adm.com", "123", "Endereço");
        adm.getProfile().add(Profile.ADMIN);
        BuyerService bs = new BuyerService(new BCryptPasswordEncoder(), buyerRepository);
        try {
            bs.insert(adm);
        } catch (ExistingUserException ignored) {}
/*
        Warehouse warehouse = new Warehouse(null, "k", "l" );
        warehouse = warehouseRepository.save(warehouse);

        Buyer buyer = new Buyer(null, "1jhjggkg23",
                "Comprador nome", "email1@hotkkmail.com", pe.encode("123"), "Endereco");
        buyer = buyerRepository.save(buyer);

        Seller seller = new Seller(null, "userSeller",
                "vendedor nome", "email1@hotmail.com", pe.encode("123"), null);
        seller = sellerRepository.save(seller);

        Representative representative = new Representative(null, "123",
                "Representante nome", "em1@gmail.com", pe.encode("123"),
                RepresentativeJob.LIDER, warehouse);
        representative.getProfile().add(Profile.ADMIN);
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
        Batch t1 = new Batch(1L, 10, 10, 10F, 10F,
                lc.plusDays(10), lc, lt, a1, null);
        Batch t2 = new Batch(2L, 10, 10, 10F, 10F,
                lc.plusDays(10), lc, lt, a2, null);
        l1.add(t1);
        l1.add(t2);
        InboundOrder i1 = new InboundOrder(1L, lc, seller, representative, l1, section);
        InboundOrder i2 = new InboundOrder(2L, lc, seller, representative, l2, section);
        i1.setInboundOrderToBatchList();
        i2.setInboundOrderToBatchList();
        inboundOrderRepository.saveAll(Arrays.asList(i1, i2));*/
    }
}
