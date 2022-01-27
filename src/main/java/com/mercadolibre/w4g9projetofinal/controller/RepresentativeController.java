package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.RepresentativeDTO;
import com.mercadolibre.w4g9projetofinal.entity.Representative;
import com.mercadolibre.w4g9projetofinal.service.RepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/representatives")
public class RepresentativeController {

    @Autowired
    private RepresentativeService service;

    @GetMapping
    public ResponseEntity<List<RepresentativeDTO>> findAll() {
        List<RepresentativeDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Representative> findById(@PathVariable Long id) {
        Representative obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody RepresentativeDTO obj) {
        service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
