package com.mercadolibre.w4g9projetofinal.controller;


import com.mercadolibre.w4g9projetofinal.dtos.converter.WarehouseConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.WarehouseRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.WarehouseResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;
import com.mercadolibre.w4g9projetofinal.entity.dtos.converter.WarehouseConverter;
import com.mercadolibre.w4g9projetofinal.entity.dtos.request.WarehouseRequestDTO;
import com.mercadolibre.w4g9projetofinal.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/fresh-products/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    public ResponseEntity<List<WarehouseResponseDTO>>findAll()
    {
        List<WarehouseResponseDTO> lista = WarehouseConverter.fromDTO(warehouseService.findAll());
        return ResponseEntity.ok(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Warehouse> findById(@PathVariable Long id)
    {
       Warehouse wh = warehouseService.findById(id);
        return ResponseEntity.ok(wh);
    }


    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody WarehouseRequestDTO wh)
    {
        Warehouse nWarehouse = WarehouseConverter.convertDtoToEntity(wh);
        nWarehouse = warehouseService.insert(nWarehouse);
        WarehouseResponseDTO nWarehouse1 = WarehouseConverter.convertEntityToDto(nWarehouse);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(nWarehouse1.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody WarehouseRequestDTO nWarehouse1, @PathVariable Long id)
    {
        Warehouse wh = WarehouseConverter.convertDtoToEntity(nWarehouse1);
        wh.setId(id);
        wh = service.update(wh);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        warehouseService.delete(id);
        return ResponseEntity.ok().build();
    }

    }

