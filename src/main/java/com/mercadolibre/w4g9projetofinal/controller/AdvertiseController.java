package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.AdvertiseConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.AdvertiseRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.AdvertiseResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Advertise;
import com.mercadolibre.w4g9projetofinal.service.AdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/fresh-products/advertise")
public class AdvertiseController {

    @Autowired
    private AdvertiseService service;

    @GetMapping
    public ResponseEntity<List<AdvertiseResponseDTO>> findAll() {
        List<AdvertiseResponseDTO> list = AdvertiseConverter.fromDTOAdvertise(service.findAll());
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Advertise> findById(@PathVariable Long id) {
        Advertise advertise = service.findById(id);
        return ResponseEntity.ok(advertise);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody AdvertiseRequestDTO advertise) {
        Advertise newAdvertise = AdvertiseConverter.convertDtoToEntityAdvertise(advertise);
        newAdvertise = service.insert(newAdvertise);
        AdvertiseResponseDTO newAdvertisedto = AdvertiseConverter.convertEntityToDtoAdvertise(newAdvertise);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newAdvertisedto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody AdvertiseRequestDTO advertiseDto, @PathVariable Long id) {
        Advertise advertise = AdvertiseConverter.convertDtoToEntityAdvertise(advertiseDto);
        advertise.setId(id);
        service.update(advertise);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
