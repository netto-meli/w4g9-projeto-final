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
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.mercadolibre.w4g9projetofinal.dtos.converter.SectionConverter.convertDtoRequestForEntity;

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

    /*** Retorna uma lista de Sections registradas
     * @return Retorna uma lista de SectionResponseDTO
     */
    @GetMapping
    @ResponseBody
    public List<SectionResponseDTO> lista(){
        List<Section> sections = sectionService.sectionListAvailable();
        return SectionConverter.convertEntityListToDtoList(sections);
    }

    /*** Cadastra uma Section
     * @param sectionRequestDTO
     * @param uriBuilder
     * @return Retorna o payload de SectionResponseDTO em um ResponseEntity com o status 201
     */
    @PostMapping
    public ResponseEntity<SectionResponseDTO> cadastrar(@RequestBody @Valid SectionRequestDTO sectionRequestDTO,
                                                        UriComponentsBuilder uriBuilder){
        Section sectionRequest = convertDtoRequestForEntity(sectionRequestDTO);
        Section section = sectionService.registerSectionDtoRequest(sectionRequest);
        URI uri = uriBuilder.path("/section/{id}").buildAndExpand(section.getId()).toUri();
        return ResponseEntity.created(uri).body(SectionConverter.convertEntityToDto(section));
    }

    /** Retorna uma Section pesquisa pelo ID.
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

    /*** Atualiza uma Section com base no ID
     * @param id
     * @param sectionDTO
     * @return Retorna o payload de SectionResponseDTO em um ResponseEntity com o status 200
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<SectionResponseDTO> atualizar(@PathVariable Long id,
                                                        @RequestBody @Valid SectionRequestDTO sectionDTO){
        Section sectionRequest = convertDtoRequestForEntity(sectionDTO);
        Section section = sectionService.updateSection(id, sectionRequest);
        if (section != null) {
            return ResponseEntity.ok(SectionConverter.convertEntityToDto(section));
        }
        return ResponseEntity.notFound().build();
    }

    /*** Deleta uma Section com base no ID
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
