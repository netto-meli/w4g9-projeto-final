package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.ProductConverter;
import com.mercadolibre.w4g9projetofinal.dtos.response.ProductByBatchResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.ProductResponseDTO;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        if(list == null || list.isEmpty()){
            throw new ObjectNotFoundException("Ainda nao consta dados cadastrados");
        }
        return ResponseEntity.ok(list);
    }

    /***
     * Motodo GET para listar produtos por categoria.
     * @return retorna a lista por categora e status 200
     */
    @GetMapping(value = "/listCategorylistCategory")
    public ResponseEntity<List<ProductResponseDTO>> FindListCategory(@RequestParam String categoryProd) {
        if(categoryProd == null || categoryProd.isEmpty()){
            throw new ObjectNotFoundException("Ainda nao consta dados cadastrados");
        }
        List<ProductResponseDTO> product = ProductConverter.convertEntityListToDtoList(
                service.findByCategoryProduct(categoryProd));
        return ResponseEntity.ok(product);
    }

    /***
     * Motodo GET para listar produtos por lote.
     * @return retorna lote dos produtos status 200
     */
    @GetMapping("/listBatch")
    public ResponseEntity<ProductByBatchResponseDTO> findBatchByProductId(@RequestParam Long id) {
        if(id == null){
            throw new ObjectNotFoundException("Ainda nao consta dados cadastrados");
        }
        ProductByBatchResponseDTO response = ProductConverter.convertEntityToDtoByProduct(service.findByBatchInProduct(id));
        return ResponseEntity.ok().body(response);
    }

    /***
     * Motodo GET para listar produtos por Ordenacao.
     * @return retorna lote dos produtos de acordo com a ordenacao status 200
     * L = ordenado por lote
     * C = ordenado por quantidade atual
     * F = ordenado por data de vencimento
     */
    @GetMapping("/listOrderBy")
    public ResponseEntity<ProductByBatchResponseDTO> OrderByProductId(@RequestParam Long id, @RequestParam String order) {
        if(id == null){
            throw new ObjectNotFoundException("Ainda nao consta dados cadastrados");
        }
        ProductByBatchResponseDTO response = ProductConverter.convertEntityToDtoByProduct(service.OrderByBatchInProduct(id, order));
        return ResponseEntity.ok().body(response);
    }

}
