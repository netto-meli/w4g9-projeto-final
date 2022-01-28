package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.entity.Warehouse;
import com.mercadolibre.w4g9projetofinal.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @GetMapping("/api")
    public Warehouse api() {
        Warehouse wh = new Warehouse(1L, "ziks", "Cuiabá");
        return warehouseService.save(wh);
    }
}
