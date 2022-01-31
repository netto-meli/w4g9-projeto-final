package com.mercadolibre.w4g9projetofinal.service;

import com.mercadolibre.w4g9projetofinal.entity.Warehouse;
import com.mercadolibre.w4g9projetofinal.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {

    WarehouseRepository warehouseRepository;

    public WarehouseService( WarehouseRepository warehouseRepository ) {
        this.warehouseRepository = warehouseRepository;
    }

    public Warehouse save(Warehouse wh) {
        return warehouseRepository.save(wh);
    }




}
