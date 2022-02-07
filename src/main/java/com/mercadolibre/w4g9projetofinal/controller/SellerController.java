package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.SellerConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.SellerRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.SellerResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Seller;
import com.mercadolibre.w4g9projetofinal.entity.enums.Profile;
import com.mercadolibre.w4g9projetofinal.security.entity.UserSS;
import com.mercadolibre.w4g9projetofinal.service.SellerService;
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

/***
 * Classe de controle para Seller
 *
 * @author Marcos Sá
 */

@RestController
@RequestMapping(value = "/api/v1/fresh-products/seller")
public class SellerController {

    /*** Instancia de serviço: <b>RepresentativeService</b> com notação <i>{@literal @}Autowired</i> do lombok */
    @Autowired
    private SellerService service;

    /*** Método para buscar todos os Sellers do banco de dados<br>
     * GET - /sellers
     * @return Payload com Lista de Sellers e ResponseEntity com status <b>OK</b>
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SellerResponseDTO>> findAll() {
        List<SellerResponseDTO> list = SellerConverter.convertEntityListToDtoList(service.findAll());
        return ResponseEntity.ok(list);
    }

    /*** Método para buscar Sellers por Id<br>
     * GET - /sellers/{id}
     * @param id id do Seller a ser encontrado
     * @return PayLoad com Seller encontrado e ResponseEntity com status <b>OK</b>
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<SellerResponseDTO> findById(@PathVariable Long id) {
        UserService.adminOrSameUser(id);
        Seller obj = service.findById(id);
        return ResponseEntity.ok(SellerConverter.convertEntityToDto(obj));
    }

    /*** Método para inserção de Seller <br>
     * POST - /sellers
     * @param obj Objeto seller a ser inserido
     * @return ResponseEntity com status <b>CREATED</b>
     */
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid SellerRequestDTO obj) {
        Seller newObj = SellerConverter.convertDtoToEntity(obj);
        newObj = service.insert(newObj);
        SellerResponseDTO newObj2 = SellerConverter.convertEntityToDto(newObj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj2.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /*** Método para atualização de Seller existente<br>
     * PUT - /sellers/{id}
     * @param newObj Objeto seller com informações para atualização
     * @param id id do Seller a ser atualizado
     * @return ResponseEntity com status <b>NO CONTENT</b>
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody @Valid SellerRequestDTO newObj) {
        UserService.adminOrSameUser(id);
        Seller obj = SellerConverter.convertDtoToEntity(newObj);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    /*** Método para atualização de Seller existente<br>
     * DELETE - /sellers/{id}
     * @param id Id do Seller a ser deletado
     * @return ResponseEntity com status <b>OK</b>
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        UserService.adminOrSameUser(id);
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
