package com.mercadolibre.fernando_netto_projeto_final.service;

import com.mercadolibre.fernando_netto_projeto_final.entity.Warehouse;
import com.mercadolibre.fernando_netto_projeto_final.repository.WarehouseRepository;
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
