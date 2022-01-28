package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.*;
import com.mercadolibre.w4g9projetofinal.repository.AdvertiseRepository;
import com.mercadolibre.w4g9projetofinal.repository.WarehouseRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AdvertiseService {

    AdvertiseRepository advertiseRepository;

    public AdvertiseService( AdvertiseRepository advertiseRepository ) {
        this.advertiseRepository = advertiseRepository;
    }

    public Advertise save(){
        Advertise advertise = new Advertise(1L, "gffj", null, new Seller(1L, "l", "l", null, null ), BigDecimal.ONE, "l");
        Frozen fro = new Frozen(1L, "l", "l", 0.1f, 0.4f);
        advertise.setProduct(fro);
        return advertiseRepository.save(advertise);
    }

    public Seller findSellerByadvertiseId(Long id) {
        return new Seller();
    }




}
