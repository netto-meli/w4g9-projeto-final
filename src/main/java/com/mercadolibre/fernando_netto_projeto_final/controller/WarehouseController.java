package com.mercadolibre.fernando_netto_projeto_final.controller;

import com.mercadolibre.fernando_netto_projeto_final.entity.Warehouse;
import com.mercadolibre.fernando_netto_projeto_final.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @GetMapping("/api")
    public Warehouse api() {
        Warehouse wh = new Warehouse();
        return warehouseService.save(wh);
    }
}
