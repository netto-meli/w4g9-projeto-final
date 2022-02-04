package com.mercadolibre.w4g9projetofinal.util;

import com.mercadolibre.w4g9projetofinal.entity.*;
import com.mercadolibre.w4g9projetofinal.entity.enums.AdvertiseStatus;
import com.mercadolibre.w4g9projetofinal.entity.enums.Profile;
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
import java.util.List;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private RepresentativeRepository representativeRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private ProductRepository productRepository;

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
    private BatchRepository batchRepository;



    @Override
    public void run(String... args) throws Exception {

        Seller s1 = new Seller(null, "vrs_marcos", "Marcos Sá", "email1@hotmail.com", pe.encode("123456"), null);
        Seller s2 = new Seller(null, "felipe.133", "Felipe Bontempo", "email2@hotmail.com", pe.encode("123456"), null);
        Seller s3 = new Seller(null, "felipe.13iu34", "Fernando Netto", "email3@hotmail.com", pe.encode("123456"), null);
        Seller s4 = new Seller(null, "felipe.1338", "Leonardo", "email4@hotmail.com", pe.encode("123456"), null);
        Seller s5 = new Seller(null, "felipe.13iuii34", "Rafael Menezes", "email5@hotmail.com", pe.encode("123456"), null);
        sellerRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5));

        Representative r1 = new Representative(null, "felipe.13sd3", "Marcos Sá", "email1@gmail.com", pe.encode("151515"), RepresentativeJob.LIDER);
        Representative r2 = new Representative(null, "felipe.13dsd3", "Marcos Sá", "email2@gmail.com", pe.encode("151515"), RepresentativeJob.SUPERVISOR);
        Representative r3 = new Representative(null, "felipe.1efe33", "Marcos Sá", "email3@gmail.com", pe.encode("151515"), RepresentativeJob.LIDER);
        Representative r4 = new Representative(null, "felipe.1ww33", "Marcos Sá", "email4@gmail.com", pe.encode("151515"), RepresentativeJob.SUPERVISOR);
        Representative r5 = new Representative(null, "felipe.13fdf3", "Marcos Sá", "email5@gmail.com", pe.encode("151515"), RepresentativeJob.LIDER);
        r1.addProfile(Profile.ADMIN);
        representativeRepository.saveAll(Arrays.asList(r1, r2, r3, r4, r5));

        Buyer b1 = new Buyer(null, "felipe.13wwf3", "Ricardo oo", "email1@yahoo.com", pe.encode("123"), "qnp28conjuntoU");
        Buyer b2 = new Buyer(null, "felipe.1wef33", "Ricardo oo", "email2@yahoo.com", pe.encode("123"), "qnp28conjuntoU");
        Buyer b3 = new Buyer(null, "felipe.13ssdf3", "Ricardo oo", "email3@yahoo.com", pe.encode("123"), "qnp28conjuntoU");
        Buyer b4 = new Buyer(null, "felipe.1sfs33", "Ricardo oo", "email4@yahoo.com", pe.encode("123"), "qnp28conjuntoU");
        Buyer b5 = new Buyer(null, "felipe.1wrgr33", "Ricardo oo", "email5@yahoo.com", pe.encode("123"), "qnp28conjuntoU");
        buyerRepository.saveAll(Arrays.asList(b1, b2, b3, b4, b5));

        Product p1 = new Product(null, "kk", "kk", 0F, 8F, RefrigerationType.COLD);
        Product p2 = new Product(null, "kk", "kk", 0F, 8F, RefrigerationType.COLD);
        Product p3 = new Product(null, "kk", "kk", 0F, 8F, RefrigerationType.COLD);
        productRepository.saveAll(Arrays.asList(p1, p2, p3));

        Advertise a1 = new Advertise(null, "k", p1, s1, BigDecimal.TEN, AdvertiseStatus.ATIVO,false );
        Advertise a2 = new Advertise(null, "k", p2, s1, BigDecimal.TEN, AdvertiseStatus.ATIVO,false );
        Advertise a3 = new Advertise(null, "k", p1, s1, BigDecimal.TEN, AdvertiseStatus.ATIVO,false );
        Advertise a4 = new Advertise(null, "k", p3, s1, BigDecimal.TEN, AdvertiseStatus.ATIVO,false );
        Advertise a5 = new Advertise(null, "k", p1, s1, BigDecimal.TEN, AdvertiseStatus.ATIVO,false );
        advertiseRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5));

        Warehouse w1 = new Warehouse(null, "k", "l" );
        Warehouse w2 = new Warehouse(null, "k", "l" );
        Warehouse w3 = new Warehouse(null, "k", "l" );
        warehouseRepository.saveAll(Arrays.asList(w1, w2, w3));

        Section c1 = new Section(null, w1, "l", RefrigerationType.COLD, 4, 1, 1F, 1F, null );
        Section c2 = new Section(null, w2, "l", RefrigerationType.COLD, 4, 1, 1F, 1F, null );
        Section c3 = new Section(null, w1, "l", RefrigerationType.COLD, 4, 1, 1F, 1F, null );
        sectionRepository.saveAll(Arrays.asList(c1, c2, c3));

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
        l1.addAll(Arrays.asList(t1, t2));
        l2.addAll(Arrays.asList(t3));
        l3.addAll(Arrays.asList(t4, t5));
        batchRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5));

        InboundOrder i1 = new InboundOrder(1L, lc, s1, r1, l1, c1);
        InboundOrder i2 = new InboundOrder(2L, lc, s1, r1, l2, c2);
        InboundOrder i3 = new InboundOrder(3L, lc, s1, r1, l3, c3);
        i1.setInboundOrderToBatchList();
        i2.setInboundOrderToBatchList();
        i3.setInboundOrderToBatchList();
        inboundOrderRepository.saveAll(Arrays.asList(i1, i2, i3));

    }
}
