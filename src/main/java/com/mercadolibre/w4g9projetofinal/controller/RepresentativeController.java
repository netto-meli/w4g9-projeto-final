package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.RepresentativeConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.RepresentativeRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.RepresentativeResponseDTO;
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
    public ResponseEntity<List<RepresentativeResponseDTO>> findAll() {
        List<RepresentativeResponseDTO> list = RepresentativeConverter.fromDTO(service.findAll());
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Representative> findById(@PathVariable Long id) {
        Representative obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody RepresentativeRequestDTO obj) {
       Representative newObj = RepresentativeConverter.convertDtoToEntity(obj);
       newObj = service.insert(newObj);
       RepresentativeResponseDTO newObj2 = RepresentativeConverter.convertEntityToDto(newObj);
       URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj2.getId()).toUri();
       return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody RepresentativeRequestDTO newObj, @PathVariable Long id) {
        Representative obj = RepresentativeConverter.convertDtoToEntity(newObj);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
