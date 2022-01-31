package com.mercadolibre.w4g9projetofinal.ProjectConfig;

import com.mercadolibre.w4g9projetofinal.entity.Representative;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.entity.enums.RepresentativeJob;
import com.mercadolibre.w4g9projetofinal.repository.RepresentativeRepository;
import com.mercadolibre.w4g9projetofinal.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private BCryptPasswordEncoder pe;


    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private RepresentativeRepository representativeRepository;

    @Override
    public void run(String... args) throws Exception {

        Seller s1 = new Seller(null, "Marcos Sá", "vr.marcos@hotmail.com", pe.encode("123456"));
        Seller s2 = new Seller(null, "Felipe Bontempo", "vrr.marc@hotmail.com", pe.encode("123456"));
        Seller s3 = new Seller(null, "Fernando Netto", "vrr.mars@hotmail.com", pe.encode("123456"));
        Seller s4 = new Seller(null, "Leonardo", "vrr.mars@hotmail.com", pe.encode("123456"));
        Seller s5 = new Seller(null, "Rafael Menezes", "vrr.marcos@hotmail.com", pe.encode("123456"));
        sellerRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5));

        Representative r1 = new Representative(null, "Marcos Sá", "email@gmail.com", RepresentativeJob.LIDER, "151515");
        Representative r2 = new Representative(null, "Marcos Sá", "email@gmail.com", RepresentativeJob.SUPERVISOR, "151515");
        Representative r3 = new Representative(null, "Marcos Sá", "email@gmail.com", RepresentativeJob.LIDER, "151515");
        Representative r4 = new Representative(null, "Marcos Sá", "email@gmail.com", RepresentativeJob.SUPERVISOR, "151515");
        Representative r5 = new Representative(null, "Marcos Sá", "email@gmail.com", RepresentativeJob.LIDER, "151515");
        representativeRepository.saveAll(Arrays.asList(r1, r2, r3, r4, r5));




    }
}
