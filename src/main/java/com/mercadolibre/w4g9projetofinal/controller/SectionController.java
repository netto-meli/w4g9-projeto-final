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
import java.util.Optional;

/**
 * @author fbontempo
 * @version 0.2
 *
 */
@RestController
@RequestMapping("/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    /**
     * Retorna uma lista de sections registradas
     *
     * @return Retorna uma lista de SectionResponseDTO
     */
    @GetMapping
    @ResponseBody
    public List<SectionResponseDTO> lista(){
        return sectionService.sectionListAvailable();
    }

    /**
     *
     * @param sectionRequestDTO
     * @param uriBuilder
     * @return Retorna o payload de SectionResponseDTO em um ResponseEntity com o status 201
     */
    @PostMapping
    public ResponseEntity<SectionResponseDTO> cadastrar(@RequestBody SectionRequestDTO sectionRequestDTO,
                                                        UriComponentsBuilder uriBuilder){
        Section section = sectionService.registerSectionDtoRequest(sectionRequestDTO);
        URI uri = uriBuilder.path("/section/{id}").buildAndExpand(section.getId()).toUri();
        return ResponseEntity.created(uri).body(SectionConverter.convertEntityToDto(section));
    }

    /**
     *
     * @param id
     * @return O Section pesquisado caso OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SectionResponseDTO> detalhar(@PathVariable Long id){
        Optional<Section> section = sectionService.searchDetailSection(id);
        if (section.isPresent()) {
            return ResponseEntity.ok(SectionConverter.convertEntityToDto(section.get()));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     *
     * @param id
     * @param sectionDTO
     * @return Retorna o payload de SectionResponseDTO em um ResponseEntity com o status 200
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<SectionResponseDTO> atualizar(@PathVariable Long id, @RequestBody SectionRequestDTO sectionDTO){
        Section section = sectionService.updateSection(id, sectionDTO);
        if (section != null) {
            return ResponseEntity.ok(SectionConverter.convertEntityToDto(section));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     *
     * @param id
     * @return Retorna o payload de SectionResponseDTO em um ResponseEntity com o status 200
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<SectionResponseDTO> remover(@PathVariable Long id){
        Section section = sectionService.deleteSectionById(id);
        if (section != null) {
            return ResponseEntity.ok(SectionConverter.convertEntityToDto(section));
        }
        return ResponseEntity.notFound().build();
    }
}
