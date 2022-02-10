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

/*** Controller dos métodos do Setor
 * <b>Lista Setores registrados</b>
 * <b>Localiza Setor pelo código ID</b>
 * <b>Registra novo Setor</b>
 * <b>Atualiza os dados de um Setor, localize pelo ID</b>
 * <b>Deleta um Setor selecionado pelo ID</b>
 * @author Felipe Bontempo
 * @version 0.4
 *
 */
@RestController
@RequestMapping("/api/v1/fresh-products/section")
@PreAuthorize("hasRole('ADMIN') OR hasRole('REPRESENTATIVE')")
public class SectionController {
    /***
     * Instancia de serviço <b>SectionService</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private SectionService sectionService;

    /*** Método retorna uma lista de Sections registradas
     * GET - /api/v1/fresh-products/section
     * @return Retorna uma lista de SectionResponseDTO
     */
    @GetMapping
    public ResponseEntity<List<SectionResponseDTO>> findAll(){
        List<Section> sections = sectionService.findAll();
        return ResponseEntity.ok(SectionConverter.convertEntityListToDtoList(sections));
    }

    /** Método que retorna uma Section pelo ID.
     * GET - /api/v1/fresh-products/section/{id}
     * @param id ID da Section que o representante deseja selecionar
     * @return O Section pesquisado caso OK.
     * @return Retorna payload de SectionResponseDTO em um ResponseEntity com status <b>OK</b> e
     */
    @GetMapping("/{id}")
    public ResponseEntity<SectionResponseDTO> findById(@PathVariable Long id){
        Section section = sectionService.findById(id);
        return ResponseEntity.ok(SectionConverter.convertEntityToDto(section));
    }

    /*** Método que cadastra uma Section
     * POST - /api/v1/fresh-products/section
     * @param sectionRequestDTO
     * @param uriBuilder
     * @return Retorna o payload de SectionResponseDTO em um ResponseEntity com o status <b>CREATED</b>
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
     * PUT - /api/v1/fresh-products/section/{id}
     * @param id
     * @param sectionDTO
     * @return Retorna o payload de SectionResponseDTO em um ResponseEntity com o status <b>OK</b>
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
     * DELETE - /api/v1/fresh-products/section/{id}
     * @param id
     * @return Retorna o status 200
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        sectionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
