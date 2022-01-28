package com.mercadolibre.w4g9projetofinal.controller;

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
    @Autowired
    private WarehouseRepository warehouseRepository;

    @GetMapping
    @ResponseBody
    public List<SectionRequestDTO> lista(){
        List sections = sectionRepository.findAll();
        return SectionRequestDTO.convert(sections);
    }

    @PostMapping
    public ResponseEntity<SectionResponseDTO> cadastrar(@RequestBody SectionRequestDTO sectionRequestDTO,
                                                        UriComponentsBuilder uriBuilder){
        Section section = sectionRequestDTO.convertToSection(warehouseRepository);
        sectionRepository.save(section);
        URI uri = uriBuilder.path("/section/{id}").buildAndExpand(section.getId()).toUri();
        return ResponseEntity.created(uri).body(new SectionResponseDTO(section));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionRequestDTO> detalhar(@PathVariable Long id){
        Optional<Section> section = sectionRepository.findById(id);
        if (section.isPresent()) {
            return ResponseEntity.ok(new SectionRequestDTO(section.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<SectionResponseDTO> atualizar(@PathVariable Long id, @RequestBody SectionRequestDTO sectionDTO){
        Optional<Section> optional = sectionRepository.findById(id);
        if (optional.isPresent()) {
            Section section = sectionDTO.atualizar(id, sectionRepository);
            return ResponseEntity.ok(new SectionResponseDTO(section));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Section> optional = sectionRepository.findById(id);
        if (optional.isPresent()) {
            sectionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
