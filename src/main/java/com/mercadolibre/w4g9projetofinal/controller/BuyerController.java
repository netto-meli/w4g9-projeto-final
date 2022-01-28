package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.BuyerConverter;
import com.mercadolibre.w4g9projetofinal.dtos.converter.RepresentativeConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.BuyerRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.request.RepresentativeRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.BuyerResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Buyer;
import com.mercadolibre.w4g9projetofinal.entity.Representative;
import com.mercadolibre.w4g9projetofinal.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/buyer")
public class BuyerController {

    @Autowired
    private BuyerService service;

    @GetMapping
    public ResponseEntity<List<BuyerResponseDTO>> findAll() {
        List<BuyerResponseDTO> list = BuyerConverter.fromDTOBuyer(service.findAll());
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Buyer> findById(@PathVariable Long id) {
        Buyer buyer = service.findById(id);
        return ResponseEntity.ok(buyer);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody BuyerRequestDTO buyer) {
        Buyer newBuyer = BuyerConverter.convertDtoToEntityBuyer(buyer);
        newBuyer = service.insert(newBuyer);
        BuyerResponseDTO buyerFinal = BuyerConverter.convertEntityToDtoBuyer(newBuyer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(buyerFinal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody BuyerRequestDTO newbuyer, @PathVariable Long id) {
        Buyer buyer = BuyerConverter.convertDtoToEntityBuyer(newbuyer);
        buyer.setId(id);
        buyer = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
