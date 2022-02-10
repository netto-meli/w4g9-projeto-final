package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.AdvertiseConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.AdvertiseRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.AdvertiseResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Advertise;
import com.mercadolibre.w4g9projetofinal.service.AdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/*** Controller dos métodos do Anuncia:<br>
 * <b>Adiciona Anuncio</b><br>
 * <b>Retira Anuncio</b><br>
 * <b>exclui Anuncio</b><br>
 * <b>Exibir Anuncio</b><br>
 * <b>Altera Anuncio</b><br>
 *
 * @author Leonardo
 */
@RestController
@RequestMapping(value = "/api/v1/fresh-products/advertise")
@PreAuthorize("hasRole('ADMIN') OR hasRole('SELLER')")
public class AdvertiseController {

    /*** Instancia de serviço: <b>AnuncioService</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private AdvertiseService service;

    /***
     * Motodo GET para listar todos os anuncios existentes.
     * @return retorna a lista e status 200
     */
    @GetMapping
    public ResponseEntity<List<AdvertiseResponseDTO>> findAll() {
        List<AdvertiseResponseDTO> list = AdvertiseConverter.convertEntityListToDtoList(service.findAll());
        return ResponseEntity.ok(list);
    }

    /***
     * Motodo GET para listar anuncios por id.
     * @param id do anuncio
     * @return retorna a lista de anuncio do id enviado
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<AdvertiseResponseDTO> findById(@PathVariable Long id) {
        Advertise advertise = service.findById(id);
        return ResponseEntity.ok(AdvertiseConverter.convertEntityToDto(advertise));
    }

    /*** Método para adicionar novo Anuncio<br>
     * POST - /api/v1/fresh-products/advertise
     * @param advertise anuncio
     * @return Retorna payload de AnuncioDto em um ResponseEntity com status <b>CREATED</b>
     */
    @PostMapping
    public ResponseEntity<AdvertiseResponseDTO> insert(@RequestBody @Valid AdvertiseRequestDTO advertise) {
        Advertise newAdvertise = AdvertiseConverter.convertDtoToEntity(advertise);
        newAdvertise = service.insert(newAdvertise);
        AdvertiseResponseDTO newAdvertisedto = AdvertiseConverter.convertEntityToDto(newAdvertise);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newAdvertisedto.getId()).toUri();
        return ResponseEntity.created(uri).body(newAdvertisedto);
    }

    /*** Método para Alterar Anuncio<br>
     * PUT - /api/v1/fresh-products/advertise
     * @param advertiseDto anuncio
     * @param id id
     * @return Retorna payload de AnuncioDto em um ResponseEntity com status <b>CREATED</b>
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<AdvertiseResponseDTO> update(@RequestBody @Valid AdvertiseRequestDTO advertiseDto,
                                                      @PathVariable Long id) {
        Advertise advertise = AdvertiseConverter.convertDtoToEntity(advertiseDto);
        advertise.setId(id);
        advertise = service.update(advertise);
        return ResponseEntity.accepted().body(AdvertiseConverter.convertEntityToDto(advertise));
    }

    /*** Método para deltear Anuncio<br>
     * DELETE - /api/v1/fresh-products/advertise
     * @param id id
     * @return status ok.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().body("Anuncio excluido id: " + id);
    }
}