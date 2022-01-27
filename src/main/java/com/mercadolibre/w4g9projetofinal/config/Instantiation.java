package com.mercadolibre.w4g9projetofinal.config;

import com.mercadolibre.w4g9projetofinal.entity.Representative;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.entity.enums.CargoRepresentante;
import com.mercadolibre.w4g9projetofinal.repository.RepresentativeRepository;
import com.mercadolibre.w4g9projetofinal.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private RepresentativeRepository representativeRepository;

    @Override
    public void run(String... args) throws Exception {

        Seller s1 = new Seller(null, "Marcos Sá", "vrr.marcos@hotmail.com");
        Seller s2 = new Seller(null, "Felipe Bontempo", "vrr.marcos@hotmail.com");
        Seller s3 = new Seller(null, "Fernando Netto", "vrr.marcos@hotmail.com");
        Seller s4 = new Seller(null, "Leonardo", "vrr.marcos@hotmail.com");
        Seller s5 = new Seller(null, "Rafael Menezes", "vrr.marcos@hotmail.com");
        sellerRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5));

        Representative r1 = new Representative(null, "Marcos Sá", CargoRepresentante.LIDER, "email@gmail.com");
        Representative r2 = new Representative(null, "Marcos Sá", CargoRepresentante.SUPERVISOR, "email@gmail.com");
        Representative r3 = new Representative(null, "Marcos Sá", CargoRepresentante.LIDER, "email@gmail.com");
        Representative r4 = new Representative(null, "Marcos Sá", CargoRepresentante.SUPERVISOR, "email@gmail.com");
        Representative r5 = new Representative(null, "Marcos Sá", CargoRepresentante.LIDER, "email@gmail.com");
        representativeRepository.saveAll(Arrays.asList(r1, r2, r3, r4, r5));




    }
}
