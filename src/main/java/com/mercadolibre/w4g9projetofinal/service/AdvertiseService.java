package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Advertise;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;
import com.mercadolibre.w4g9projetofinal.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

@Service
public class AdvertiseService {

    public AdvertiseService( ) {
    }

    public Seller findSellerByadvertiseId(Long id) {
        return new Seller();
    }




}
