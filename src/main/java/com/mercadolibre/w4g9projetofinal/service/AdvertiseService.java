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

    public Seller findSellerByAdvertiseId(Long id) {
        return advertiseRepository.findSellerByAdvertise_Id(id);
    }
}
