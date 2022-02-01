package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.AdvertiseConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.AdvertiseRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.AdvertiseResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Advertise;
import com.mercadolibre.w4g9projetofinal.service.AdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
     * @pathVariable id do anuncio
     * @return retorna a lista de anuncio do id enviado
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Advertise> findById(@PathVariable Long id) {
        Advertise advertise = service.findById(id);
        return ResponseEntity.ok(advertise);
    }

    /*** Método para adicionar novo Anuncio<br>
     * POST - /api/v1/fresh-products/advertise
     * @return Retorna payload de AnuncioDto em um ResponseEntity com status <b>CREATED</b>
     */

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody AdvertiseRequestDTO advertise) {
        Advertise newAdvertise = AdvertiseConverter.convertDtoToEntity(advertise);
        newAdvertise = service.insert(newAdvertise);
        AdvertiseResponseDTO newAdvertisedto = AdvertiseConverter.convertEntityToDto(newAdvertise);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newAdvertisedto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /*** Método para Alterar Anuncio<br>
     * PUT - /api/v1/fresh-products/advertise
     * @return Retorna payload de AnuncioDto em um ResponseEntity com status <b>CREATED</b>
     */

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody AdvertiseRequestDTO advertiseDto, @PathVariable Long id) {
        Advertise advertise = AdvertiseConverter.convertDtoToEntity(advertiseDto);
        advertise.setId(id);
        service.update(advertise);
        return ResponseEntity.noContent().build();
    }

    /*** Método para deltear Anuncio<br>
     * DELETE - /api/v1/fresh-products/advertise
     * @return status ok.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
