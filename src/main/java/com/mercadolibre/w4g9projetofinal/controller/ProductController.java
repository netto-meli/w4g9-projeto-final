package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.BatchConverter;
import com.mercadolibre.w4g9projetofinal.dtos.converter.ProductConverter;
import com.mercadolibre.w4g9projetofinal.dtos.request.ProductRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.BatchResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.ProductResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Product;
import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/*** Controller dos métodos do Produt:<br>
 * <b>Lista todos produtos</b><br>
 * <b>lista produto por categoria</b><br>
 * <b>listar produtos por lote</b>
 * <b>listar produtos por Ordenacao.</b>
 * @author Leonardo
 */
@RestController
@RequestMapping(value = "/api/v1/fresh-products/product")
@PreAuthorize("hasRole('ADMIN') OR hasRole('REPRESENTATIVE')")
public class ProductController {

    /*** Instancia de serviço: <b>ProdutoService</b> com notação <i>{@literal @}Autowired</i> do lombok
     */
    @Autowired
    private ProductService service;

    /***
     * Motodo GET para listar todos os produtos existentes.
     * @return retorna a lista e status 200
     */
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        List<ProductResponseDTO> list = ProductConverter.convertEntityListToDtoList(service.findAll());
        return ResponseEntity.ok(list);
    }

    /***
     * Motodo GET para listar produto por id.
     * @param id do produto
     * @return retorna a lista de produto do id enviado
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ProductConverter.convertEntityToDto(service.findById(id)));
    }

    /*** Método para inserção de Produto <br>
     * @param obj Objeto product a ser inserido
     * @return ResponseEntity com status <b>CREATED</b>
     */
    @PostMapping
    public ResponseEntity<ProductResponseDTO> insert(@RequestBody @Valid ProductRequestDTO obj) {
        Product newObj = ProductConverter.convertDtoToEntity(obj);
        newObj = service.insert(newObj);
        ProductResponseDTO newObj2 = ProductConverter.convertEntityToDto(newObj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj2.getId()).toUri();
        return ResponseEntity.created(uri).body(newObj2);
    }

    /*** Método para atualização de product existente<br>
     * @param id id do product a ser atualizado
     * @param newProd Objeto product com informações para atualização
     * @return ResponseEntity com status <b>NO CONTENT</b>
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id,
                                          @RequestBody @Valid ProductRequestDTO newProd) {
        Product product = ProductConverter.convertDtoToEntity(newProd);
        product.setId(id);
        product = service.update(product);
        return ResponseEntity.ok().body(ProductConverter.convertEntityToDto(product));
    }

    /*** Método para atualização de Seller existente<br>
     * DELETE - /sellers/{id}
     * @param id Id do Seller a ser deletado
     * @return ResponseEntity com status <b>OK</b>
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().body("Id " + id + " excluido");
    }

    /***
     * Motodo GET para listar produtos por categoria.
     * @param categoryProd  categoria
     * @return retorna a lista por categora e status 200
     */
    @GetMapping(value = "/listCategory/")
    public ResponseEntity<List<ProductResponseDTO>> findListCategory(@RequestParam RefrigerationType categoryProd) {
        List<ProductResponseDTO> product = ProductConverter.convertEntityListToDtoList(
                service.findByCategoryProduct(categoryProd));
        return ResponseEntity.ok(product);
    }

    /***
     * Motodo GET para listar produtos por lote.
     * @param id id
     * @return retorna lote dos produtos status 200
     */
    @GetMapping("/listBatch/")
    public ResponseEntity<List<BatchResponseDTO>> findBatchByProductId(@RequestParam Long id) {
        List<BatchResponseDTO> response = BatchConverter.convertEntityListToDtoList(service.findByBatchInProduct(id));
        return ResponseEntity.ok().body(response);
    }

    /***
     * Motodo GET para listar produtos por Ordenacao.
     * L = ordenado por lote
     * C = ordenado por quantidade atual
     * F = ordenado por data de vencimento
     * @param id id
     * @param order ordenacao
     * @return retorna lote dos produtos de acordo com a ordenacao status 200
     */
    @GetMapping("/listBatchByProduct/")
    public ResponseEntity<List<BatchResponseDTO>> orderByProductId(@RequestParam Long id, @RequestParam String order) {
        List<BatchResponseDTO> response = BatchConverter.convertEntityListToDtoList(service.OrderByBatchInProduct(id, order));
        return ResponseEntity.ok().body(response);
    }
}
