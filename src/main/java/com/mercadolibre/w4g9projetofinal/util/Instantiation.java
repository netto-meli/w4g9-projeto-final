package com.mercadolibre.w4g9projetofinal.util;

import com.mercadolibre.w4g9projetofinal.entity.*;
import com.mercadolibre.w4g9projetofinal.entity.enums.AdvertiseStatus;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.entity.enums.RepresentativeJob;
import com.mercadolibre.w4g9projetofinal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
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
    private InboundOrderRepository inboundOrderRepository;



    @Override
    public void run(String... args) throws Exception {
/*
        Product p1 = new Product(null, "kk", "kk", 0F, 8F, RefrigerationType.COLD);
        Product p2 = new Product(null, "kk", "kk", 0F, 8F, RefrigerationType.COLD);
        Product p3 = new Product(null, "kk", "kk", 0F, 8F, RefrigerationType.COLD);
        p1 = productRepository.save(p1);
        p2 = productRepository.save(p2);
        p3 = productRepository.save(p3);


        Seller s1 = new Seller(null, "Marcos Sá", "email1@hotmail.com", pe.encode("123456"), null);
        s1 = sellerRepository.save(s1);


        Representative r1 = new Representative(null, "Marcos Sá", "email1@gmail.com", pe.encode("151515"), RepresentativeJob.LIDER);
        r1 = representativeRepository.save(r1);

        p1 = productRepository.findById(1L).orElse(null);
        p2 = productRepository.findById(2L).orElse(null);
        p3 = productRepository.findById(3L).orElse(null);
        p2 = new Product(2L, "kk", "kk", 0F, 8F, RefrigerationType.COLD);
        p3 = new Product(3L, "kk", "kk", 0F, 8F, RefrigerationType.COLD);
        Advertise a1 = new Advertise(null, "k", p1, s1, BigDecimal.TEN, AdvertiseStatus.ATIVO,false );
        Advertise a2 = new Advertise(null, "k", p2, s1, BigDecimal.TEN, AdvertiseStatus.ATIVO,false );
        Advertise a3 = new Advertise(null, "k", p1, s1, BigDecimal.TEN, AdvertiseStatus.ATIVO,false );
        Advertise a4 = new Advertise(null, "k", p3, s1, BigDecimal.TEN, AdvertiseStatus.ATIVO,false );
        Advertise a5 = new Advertise(null, "k", p1, s1, BigDecimal.TEN, AdvertiseStatus.ATIVO,false );
        a1 = advertiseRepository.save(a1);
        a2 = advertiseRepository.save(a2);
        a3 = advertiseRepository.save(a3);
        a4 = advertiseRepository.save(a4);
        a5 = advertiseRepository.save(a5);

        Warehouse w1 = new Warehouse(null, "k", "l" );
        Warehouse w2 = new Warehouse(null, "k", "l" );
        Warehouse w3 = new Warehouse(null, "k", "l" );
        w1 = warehouseRepository.save(w1);
        w2 = warehouseRepository.save(w2);
        w3 = warehouseRepository.save(w3);

        Section c1 = new Section(null, w1, "l", RefrigerationType.COLD, 4, 1, 1F, 1F, null );
        Section c2 = new Section(null, w2, "l", RefrigerationType.COLD, 4, 1, 1F, 1F, null );
        Section c3 = new Section(null, w1, "l", RefrigerationType.COLD, 4, 1, 1F, 1F, null );
        c1 = sectionRepository.save(c1);
        c2 = sectionRepository.save(c2);
        c3 = sectionRepository.save(c3);

        LocalDate lc = LocalDate.now();
        LocalDateTime lt = LocalDateTime.now();
        List<Batch> l1 = new ArrayList<>();
        List<Batch> l2 = new ArrayList<>();
        List<Batch> l3 = new ArrayList<>();
        Batch t1 = new Batch(1L, 1, 1, 1F, 1F, lc, lc, lt, a1, null);
        Batch t2 = new Batch(2L, 1, 10, 1F, 1F, lc, lc, lt, a2, null);
        Batch t3 = new Batch(3L, 1, 100, 1F, 1F, lc, lc, lt, a3, null);
        Batch t4 = new Batch(4L, 1, 2, 1F, 1F, lc, lc, lt, a4, null);
        Batch t5 = new Batch(5L, 1, 20, 1F, 1F, lc, lc, lt, a5, null);
        l1.add(t1);
        l1.add(t2);
        l2.add(t3);
        l3.add(t4);
        l3.add(t5);
        InboundOrder i1 = new InboundOrder(1L, lc, s1, r1, l1, c1);
        InboundOrder i2 = new InboundOrder(2L, lc, s1, r1, l2, c2);
        InboundOrder i3 = new InboundOrder(3L, lc, s1, r1, l3, c3);
        i1.setInboundOrderToBatchList();
        i2.setInboundOrderToBatchList();
        i3.setInboundOrderToBatchList();
        inboundOrderRepository.saveAll(Arrays.asList(i1, i2, i3));*/
    }
}
