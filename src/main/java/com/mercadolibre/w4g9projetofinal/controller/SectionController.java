package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.SectionConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.SectionRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.SectionResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Section;
import com.mercadolibre.w4g9projetofinal.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author fbontempo
 * @version 0.4
 *
 */
@RestController
@RequestMapping("/api/v1/fresh-products/section")
@PreAuthorize("hasRole('ADMIN') OR hasRole('REPRESENTATIVE')")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    /*** Retorna uma lista de Sections registradas
     * @return Retorna uma lista de SectionResponseDTO
     */
    @GetMapping
    public ResponseEntity<List<SectionResponseDTO>> findAll(){
        List<Section> sections = sectionService.findAll();
        return ResponseEntity.ok(SectionConverter.convertEntityListToDtoList(sections));
    }

    /** Retorna uma Section pesquisa pelo ID.
     * @param id id
     * @return O Section pesquisado caso OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SectionResponseDTO> findById(@PathVariable Long id){
        Section section = sectionService.findById(id);
        return ResponseEntity.ok(SectionConverter.convertEntityToDto(section));
    }

    /*** Cadastra uma Section
     * @param sectionRequestDTO section
     * @param uriBuilder uri
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

    /*** Atualiza uma Section com base no ID
     * @param id id
     * @param sectionDTO section
     * @return Retorna o payload de SectionResponseDTO em um ResponseEntity com o status 200
     */
    @PutMapping("/{id}")
    public ResponseEntity<SectionResponseDTO> update(@PathVariable Long id,
                                                     @RequestBody @Valid SectionRequestDTO sectionDTO){
        Section sectionRequest = SectionConverter.convertDtoToEntity(sectionDTO);
        sectionRequest.setId(id);
        Section section = sectionService.update(sectionRequest);
        if (section != null) {
            return ResponseEntity.ok(SectionConverter.convertEntityToDto(section));
        }
        return ResponseEntity.notFound().build();
    }

    /*** Deleta uma Section com base no ID
     * @param id id
     * @return Retorna o status 200
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        sectionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
