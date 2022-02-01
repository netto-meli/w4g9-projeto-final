package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.RepresentativeConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.RepresentativeRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.RepresentativeResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Representative;
import com.mercadolibre.w4g9projetofinal.service.RepresentativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/***
 * Classe de controle para Representative
 *
 * @author Marcos Sá
 */

@RestController
@RequestMapping(value = "/api/v1/fresh-products/representative")
public class RepresentativeController {

    /*** Instancia de serviço: <b>RepresentativeService</b> com notação <i>{@literal @}Autowired</i> do lombok */
    @Autowired
    private RepresentativeService service;

    /*** Método para buscar todos os Representatives do banco de dados<br>
     * GET - /representatives
     * @return Payload com Lista de Representatives e ResponseEntity com status <b>OK</b>
     */
    @GetMapping
    public ResponseEntity<List<RepresentativeResponseDTO>> findAll() {
        List<RepresentativeResponseDTO> list = RepresentativeConverter.convertEntityListToDtoList(service.findAll());
        return ResponseEntity.ok(list);
    }

    /*** Método para buscar Representatives por Id<br>
     * GET - /representatives/{id}
     * @param id id do Representative a ser encontrado
     * @return PayLoad com Representative encontrado e ResponseEntity com status <b>OK</b>
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Representative> findById(@PathVariable Long id) {
        Representative obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    /*** Método para buscar Representative por email<br>
     * GET - /representative/email
     * @param email email do Representative a ser encontrado
     * @return PayLoad com Representative encontrado e ResponseEntity com status <b>OK</b>
     */
    @GetMapping(value = "/email")
    public ResponseEntity<Representative> findByEmail(@RequestParam(value = "value") String email) {
        Representative obj = service.findByEmail(email);
        return ResponseEntity.ok(obj);
    }

    /*** Método para inserção de Representative <br>
     * POST - /representatives
     * @param obj Objeto Representative a ser inserido
     * @return ResponseEntity com status <b>CREATED</b>
     */
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody RepresentativeRequestDTO obj) {
       Representative newObj = RepresentativeConverter.convertDtoToEntity(obj);
       newObj = service.insert(newObj);
       RepresentativeResponseDTO newObj2 = RepresentativeConverter.convertEntityToDto(newObj);
       URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj2.getId()).toUri();
       return ResponseEntity.created(uri).build();
    }

    /*** Método para atualização de Seller existente<br>
     * PUT - /representatives/{id}
     * @param newObj Objeto Representative com informações para atualização
     * @param id id do Representative a ser atualizado
     * @return ResponseEntity com status <b>NO CONTENT</b>
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody RepresentativeRequestDTO newObj, @PathVariable Long id) {
        Representative obj = RepresentativeConverter.convertDtoToEntity(newObj);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    /*** Método para atualização de Representative existente<br>
     * DELETE - /representative/{id}
     * @param id Id do Representative a ser deletado
     * @return ResponseEntity com status <b>OK</b>
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
