package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.BuyerConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.BuyerRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.BuyerResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Buyer;
import com.mercadolibre.w4g9projetofinal.entity.enums.Profile;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.security.entity.UserSS;
import com.mercadolibre.w4g9projetofinal.service.BuyerService;
import com.mercadolibre.w4g9projetofinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/*** Controller dos métodos do Comprador:<br>
 * <b>Adiciona Comprador</b><br>
 * <b>Retira Comprador</b><br>
 * <b>exclui Comprador</b><br>
 * <b>Exibir Comprador</b><br>
 * <b>Altera Comprador</b><br>
 *
 * @author Leonardo
 */

@RestController
@RequestMapping(value = "/api/v1/fresh-products/buyer")
public class BuyerController {

    /*** Instancia de serviço: <b>CompradorService</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private BuyerService service;

    /***
     * Motodo GET para listar todos os compradores existentes.
     * @return retorna a lista e status 200
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BuyerResponseDTO>> findAll() {
        List<BuyerResponseDTO> list = BuyerConverter.convertEntityListToDtoList(service.findAll());
        if(list == null || list.isEmpty()){
            throw new ObjectNotFoundException("Ainda nao consta dados cadastrados");
        }
        return ResponseEntity.ok(list);
    }

    /***
     * Motodo GET para listar comprador por id.
     * @param id do comprador
     * @return retorna a lista de comprador do id enviado
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<BuyerResponseDTO> findById(@PathVariable Long id) {
        UserService.adminOrSameUser(id);
        Buyer buyer = service.findById(id);
        return ResponseEntity.ok(BuyerConverter.convertEntityToDto(buyer));
    }

    /*** Método para adicionar novo Comprador<br>
     * POST - /api/v1/fresh-products/buyer
     * @return Retorna payload de Comprador em um ResponseEntity com status <b>CREATED</b>
     */
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid BuyerRequestDTO buyer) {
        Buyer newBuyer = BuyerConverter.convertDtoToEntity(buyer);
        newBuyer = service.insert(newBuyer);
        BuyerResponseDTO buyerFinal = BuyerConverter.convertEntityToDto(newBuyer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(buyerFinal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /*** Método para Alterar Comprador<br>
     * PUT - /api/v1/fresh-products/buyer
     * @return Retorna payload de CompradorDto em um ResponseEntity com status <b>CREATED</b>
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid BuyerRequestDTO newBuyer,
                                       @PathVariable Long id) {
        UserService.adminOrSameUser(id);
        Buyer buyer = BuyerConverter.convertDtoToEntity(newBuyer);
        buyer.setId(id);
        service.update(buyer);
        return ResponseEntity.noContent().build();
    }

    /*** Método para deltear Anuncio<br>
     * DELETE - /api/v1/fresh-products/buyer
     * @return status ok.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        UserService.adminOrSameUser(id);
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
