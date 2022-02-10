package com.mercadolibre.w4g9projetofinal.controller;


import com.mercadolibre.w4g9projetofinal.dtos.converter.WarehouseConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.WarehouseRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.ProductByWarehouseResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.WarehouseResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Warehouse;
import com.mercadolibre.w4g9projetofinal.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

/***Classe para Controle dos métodos do Armazém
 * @author Rafael Menezes
 * @author Fernando
 * @version 1.0
 * @since Release 01 da aplicação
 */
@RestController
@RequestMapping(value = "/api/v1/fresh-products/warehouse")
@PreAuthorize("hasRole('ADMIN') OR hasRole('REPRESENTATIVE')")
public class WarehouseController {

    /** Intancia de serviço: WarehouseService com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    WarehouseService warehouseService;

    /**
     * Busca armazem por id de produto
     * GET - /byProduct/{id}
     * @param id id do Product a ser encontrado
     * @return Pooduct encontrado e ResponseEntity com status <b>OK</b>
     */
    @GetMapping("byProduct/{id}")
    public ResponseEntity<ProductByWarehouseResponseDTO> findWarehousesByProductId(@PathVariable Long id) {
        Map<Long, Integer> warehouses = warehouseService.findWarehousesByProductId(id);
        ProductByWarehouseResponseDTO response = WarehouseConverter.convertEntityToDtoByProduct(id, warehouses);
        return ResponseEntity.ok().body(response);
    }

    /*** Método para buscar todos os Warehouse do banco de dados
     * GET - /warehouse
     * @return Lista de Warehouse e ResponseEntity com status <b>OK</b>
     */
    @GetMapping
    public ResponseEntity<List<WarehouseResponseDTO>> findAll() {
        List<WarehouseResponseDTO> list = WarehouseConverter.convertEntityListToDtoList(warehouseService.findAll());
        return ResponseEntity.ok(list);
    }

    /*** Método para buscar Warehouse por Id<br>
     * GET - /warehouse/{id}
     * @param id id do Warehouse a ser encontrado
     * @return Warehouse encontrado e ResponseEntity com status <b>OK</b>
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<WarehouseResponseDTO> findById(@PathVariable Long id) {
        Warehouse wh = warehouseService.findById(id);
        return ResponseEntity.ok(WarehouseConverter.convertEntityToDto(wh));
    }

    /*** Método para inserção do Warehouse
     * POST - /warehouse
     * @param wh Objeto seller a ser inserido
     * @return ResponseEntity com status <b>CREATED</b>
     */
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid WarehouseRequestDTO wh) {
        Warehouse nWarehouse = WarehouseConverter.convertDtoToEntity(wh);
        nWarehouse = warehouseService.insert(nWarehouse);
        WarehouseResponseDTO nWarehouse1 = WarehouseConverter.convertEntityToDto(nWarehouse);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(nWarehouse1.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /*** Método para atualização do Armazém existente
     * PUT - /warehouse/{id}
     * @param nWarehouse1 Objeto warehouse com informações para atualização
     * @param id id do Warehouse a ser atualizado
     * @return ResponseEntity com status <b>NO CONTENT</b>
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid WarehouseRequestDTO nWarehouse1) {
        Warehouse wh = WarehouseConverter.convertDtoToEntity(nWarehouse1);
        wh.setId(id);
        wh = warehouseService.update(wh);
        return ResponseEntity.noContent().build();
    }

    /*** Método para atualização do Armazém existente
     * DELETE - /warehouse/{id}
     * @param id Id do Warehouse  a ser deletado
     * @return ResponseEntity com status <b>OK</b>
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        warehouseService.delete(id);
        return ResponseEntity.ok().build();
    }
}

