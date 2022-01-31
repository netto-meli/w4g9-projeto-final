package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.SectionConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.SectionRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.SectionResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.repository.SectionRepository;
import com.mercadolibre.w4g9projetofinal.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/section")
public class SectionController {

    @Autowired
    private SectionRepository sectionRepository;

    @GetMapping
    @ResponseBody
    public List<SectionResponseDTO> lista(){
        List<Section> sections = sectionRepository.findAll();
        return SectionConverter.convertEntityListToDtoList(sections);
    }

    @PostMapping
    public ResponseEntity<SectionResponseDTO> cadastrar(@RequestBody SectionRequestDTO sectionRequestDTO,
                                                        UriComponentsBuilder uriBuilder){
        Section section = SectionConverter.convertDtoToEntity(sectionRequestDTO);
        //todo service
        sectionRepository.save(section);
        URI uri = uriBuilder.path("/section/{id}").buildAndExpand(section.getId()).toUri();
        return ResponseEntity.created(uri).body(SectionConverter.convertEntityToDto(section));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionResponseDTO> detalhar(@PathVariable Long id){
        //todo service
        Optional<Section> section = sectionRepository.findById(id);
        if (section.isPresent()) {
            return ResponseEntity.ok(SectionConverter.convertEntityToDto(section.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<SectionResponseDTO> atualizar(@PathVariable Long id, @RequestBody SectionRequestDTO sectionDTO){
        // todo service
        Optional<Section> optional = sectionRepository.findById(id);
        if (optional.isPresent()) {
            Section section = sectionDTO.atualizar(id, sectionRepository);
            return ResponseEntity.ok(SectionConverter.convertEntityToDto(section));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        // todo service
        Optional<Section> optional = sectionRepository.findById(id);
        if (optional.isPresent()) {
            sectionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
