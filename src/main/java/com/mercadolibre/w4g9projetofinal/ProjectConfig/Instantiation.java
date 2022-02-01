package com.mercadolibre.w4g9projetofinal.ProjectConfig;

import com.mercadolibre.w4g9projetofinal.entity.Buyer;
import com.mercadolibre.w4g9projetofinal.entity.Representative;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.entity.enums.Profile;
import com.mercadolibre.w4g9projetofinal.entity.enums.RepresentativeJob;
import com.mercadolibre.w4g9projetofinal.repository.BuyerRepository;
import com.mercadolibre.w4g9projetofinal.repository.RepresentativeRepository;
import com.mercadolibre.w4g9projetofinal.repository.SellerRepository;
import com.mercadolibre.w4g9projetofinal.repository.UserRepository;
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

    @Autowired
    private BuyerRepository buyerRepository;



    @Override
    public void run(String... args) throws Exception {

        Seller s1 = new Seller(null, "Marcos Sá", "email1@hotmail.com", "vrs_marcos", pe.encode("123456"));
        Seller s2 = new Seller(null, "Felipe Bontempo", "email2@hotmail.com", "felipe.133", pe.encode("123456"));
        Seller s3 = new Seller(null, "Fernando Netto", "email3@hotmail.com", "felipe.13iu34", pe.encode("123456"));
        Seller s4 = new Seller(null, "Leonardo", "email4@hotmail.com", "felipe.1338", pe.encode("123456"));
        Seller s5 = new Seller(null, "Rafael Menezes", "email5@hotmail.com", "felipe.13iuii34", pe.encode("123456"));
        sellerRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5));

        Representative r1 = new Representative(null, "Marcos Sá", "email1@gmail.com", RepresentativeJob.LIDER, "felipe.13sd3", pe.encode("151515"));
        Representative r2 = new Representative(null, "Marcos Sá", "email2@gmail.com", RepresentativeJob.SUPERVISOR, "felipe.13dsd3", pe.encode("151515"));
        Representative r3 = new Representative(null, "Marcos Sá", "email3@gmail.com", RepresentativeJob.LIDER, "felipe.1efe33", pe.encode("151515"));
        Representative r4 = new Representative(null, "Marcos Sá", "email4@gmail.com", RepresentativeJob.SUPERVISOR, "felipe.1ww33", pe.encode("151515"));
        Representative r5 = new Representative(null, "Marcos Sá", "email5@gmail.com", RepresentativeJob.LIDER, "felipe.13fdf3", pe.encode("151515"));
        r1.addProfile(Profile.ADMIN);
        representativeRepository.saveAll(Arrays.asList(r1, r2, r3, r4, r5));

        Buyer b1 = new Buyer(null, "Ricardo oo", "email1@yahoo.com", "felipe.13wwf3", pe.encode("123"), "qnp28conjuntoU");
        Buyer b2 = new Buyer(null, "Ricardo oo", "email2@yahoo.com", "felipe.1wef33", pe.encode("123"), "qnp28conjuntoU");
        Buyer b3 = new Buyer(null, "Ricardo oo", "email3@yahoo.com", "felipe.13ssdf3", pe.encode("123"), "qnp28conjuntoU");
        Buyer b4 = new Buyer(null, "Ricardo oo", "email4@yahoo.com", "felipe.1sfs33", pe.encode("123"), "qnp28conjuntoU");
        Buyer b5 = new Buyer(null, "Ricardo oo", "email5@yahoo.com", "felipe.1wrgr33", pe.encode("123"), "qnp28conjuntoU");
        buyerRepository.saveAll(Arrays.asList(b1, b2, b3, b4, b5));

    }
}
