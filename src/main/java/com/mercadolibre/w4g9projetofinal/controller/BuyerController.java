package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.BuyerConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.BuyerRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.BuyerResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Buyer;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.controller.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/fresh-products/buyer")
public class BuyerController {

    @Autowired
    private BuyerService service;

    @GetMapping
    public ResponseEntity<List<BuyerResponseDTO>> findAll() {
        List<BuyerResponseDTO> list = BuyerConverter.convertEntityListToDtoList(service.findAll());
        if(list == null || list.isEmpty()){
            throw new ObjectNotFoundException("Ainda nao consta dados cadastrados");
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Buyer> findById(@PathVariable Long id) {
        Buyer buyer = service.findById(id);
        return ResponseEntity.ok(buyer);
    }

    @GetMapping(value = "/email")
    public ResponseEntity<Buyer> findByEmail(@RequestParam(value = "value") String email) {
         Buyer obj = service.findByEmail(email);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody BuyerRequestDTO buyer) {
        Buyer newBuyer = BuyerConverter.convertDtoToEntity(buyer);
        newBuyer = service.insert(newBuyer);
        BuyerResponseDTO buyerFinal = BuyerConverter.convertEntityToDto(newBuyer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(buyerFinal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody BuyerRequestDTO newBuyer, @PathVariable Long id) {
        Buyer buyer = BuyerConverter.convertDtoToEntity(newBuyer);
        buyer.setId(id);
        service.update(buyer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
