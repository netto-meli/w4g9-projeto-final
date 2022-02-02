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
    public List<SectionResponseDTO> findAll(){
        List<Section> sections = sectionService.findAll();
        return SectionConverter.convertEntityListToDtoList(sections);
    }

    /*** Cadastra uma Section
     * @param sectionRequestDTO
     * @param uriBuilder
     * @return Retorna o payload de SectionResponseDTO em um ResponseEntity com o status 201
     */
    @PostMapping
    public ResponseEntity<SectionResponseDTO> insert(@RequestBody @Valid SectionRequestDTO sectionRequestDTO,
                                                        UriComponentsBuilder uriBuilder){
        Section sectionRequest = SectionConverter.convertDtoToEntity(sectionRequestDTO);
        Section section = sectionService.save(sectionRequest);
        URI uri = uriBuilder.path("/section/{id}").buildAndExpand(section.getId()).toUri();
        return ResponseEntity.created(uri).body(SectionConverter.convertEntityToDto(section));
    }

    /** Retorna uma Section pesquisa pelo ID.
     * @param id
     * @return O Section pesquisado caso OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SectionResponseDTO> findById(@PathVariable Long id){
        Section section = sectionService.findById(id);
        return ResponseEntity.ok(SectionConverter.convertEntityToDto(section));
    }

    /*** Atualiza uma Section com base no ID
     * @param id
     * @param sectionDTO
     * @return Retorna o payload de SectionResponseDTO em um ResponseEntity com o status 200
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<SectionResponseDTO> update(@PathVariable Long id,
                                                        @RequestBody @Valid SectionRequestDTO sectionDTO){
        Section sectionRequest = SectionConverter.convertDtoToEntity(sectionDTO);
        Section section = sectionService.update(id, sectionRequest);
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
    public ResponseEntity<SectionResponseDTO> delete(@PathVariable Long id){
        Section section = sectionService.delete(id);
        if (section != null) {
            return ResponseEntity.ok(SectionConverter.convertEntityToDto(section));
        }
        return ResponseEntity.notFound().build();
    }
}
