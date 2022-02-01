package com.mercadolibre.w4g9projetofinal.controller;

import com.mercadolibre.w4g9projetofinal.dtos.converter.ProductConverter;
import com.mercadolibre.w4g9projetofinal.dtos.response.ProductResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Product;
import com.mercadolibre.w4g9projetofinal.exceptions.ObjectNotFoundException;
import com.mercadolibre.w4g9projetofinal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*** Controller dos métodos do Produt:<br>
 * <b>Lista todos anuncios</b><br>
 * <b>lista anuncio por categoria</b><br>
 *
 * @author Leonardo
 */
@RestController
@RequestMapping(value = "/api/v1/fresh-products")
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
    @GetMapping(value = "/list")
    public ResponseEntity<List<Product>> FindListCategory(@RequestParam String queryType) {
        if(queryType == null || queryType.isEmpty()){
            throw new ObjectNotFoundException("Ainda nao consta dados cadastrados");
        }
        List<Product> product = service.findByCategoryProduct(queryType);
        return ResponseEntity.ok(product);
    }

}
