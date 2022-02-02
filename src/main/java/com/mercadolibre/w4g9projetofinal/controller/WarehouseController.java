package com.mercadolibre.w4g9projetofinal.controller;


import com.mercadolibre.w4g9projetofinal.dtos.converter.WarehouseConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.WarehouseRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.ProductByWarehouseResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.WarehouseResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;
import com.mercadolibre.w4g9projetofinal.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/fresh-products/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @GetMapping("byProduct/{id}")
    public ResponseEntity<ProductByWarehouseResponseDTO> findWarehousesByProductId(@PathVariable Long id) {
        Map<Long,Integer> warehouses = warehouseService.findWarehousesByProductId(id);
        ProductByWarehouseResponseDTO response = WarehouseConverter.convertEntityToDtoByProduct(id, warehouses);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<WarehouseResponseDTO>> findAll()
    {
        List<WarehouseResponseDTO> list = WarehouseConverter.convertEntityListToDtoList(warehouseService.findAll());
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Warehouse> findById(@PathVariable Long id)
    {
       Warehouse wh = warehouseService.findById(id);
        return ResponseEntity.ok(wh);
    }


    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid WarehouseRequestDTO wh)
    {
        Warehouse nWarehouse = WarehouseConverter.convertDtoToEntity(wh);
        nWarehouse = warehouseService.insert(nWarehouse);
        WarehouseResponseDTO nWarehouse1 = WarehouseConverter.convertEntityToDto(nWarehouse);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(nWarehouse1.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid WarehouseRequestDTO nWarehouse1)
    {
        Warehouse wh = WarehouseConverter.convertDtoToEntity(nWarehouse1);
        wh.setId(id);
        wh = warehouseService.update(wh);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        warehouseService.delete(id);
        return ResponseEntity.ok().build();
    }

    }

