package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.WarehouseConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.WarehouseRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.WarehouseResponseDTO;
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
    public WarehouseResponseDTO api() {
        WarehouseRequestDTO request = new WarehouseRequestDTO();
        Warehouse wh = WarehouseConverter.convertDtoToEntity(request);
        wh = warehouseService.save(wh);
        WarehouseResponseDTO response = WarehouseConverter.convertEntityToDto(wh);
        return response;
    }
}
