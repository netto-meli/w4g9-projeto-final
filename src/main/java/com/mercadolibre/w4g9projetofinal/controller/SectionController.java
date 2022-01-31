package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.SectionConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.SectionRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.SectionResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @GetMapping
    @ResponseBody
    public List<SectionResponseDTO> lista(){
        return sectionService.sectionListAvailable();
    }

    @PostMapping
    public ResponseEntity<SectionResponseDTO> cadastrar(@RequestBody SectionRequestDTO sectionRequestDTO,
                                                        UriComponentsBuilder uriBuilder){
        Section section = sectionService.registerSectionDtoRequest(sectionRequestDTO);
        URI uri = uriBuilder.path("/section/{id}").buildAndExpand(section.getId()).toUri();
        return ResponseEntity.created(uri).body(SectionConverter.convertEntityToDto(section));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectionResponseDTO> detalhar(@PathVariable Long id){
        return sectionService.searchDetailSection(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<SectionResponseDTO> atualizar(@PathVariable Long id, @RequestBody SectionRequestDTO sectionDTO){
        return sectionService.updateSection(id, sectionDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id){
        return sectionService.deleteSectionById(id);
    }
}
