package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.ProductRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.*;
import com.mercadolibre.w4g9projetofinal.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/***
 * Classe para realizar conversao de dados para tratativa e devolutiva de dados
 * @Autor Leonardo
 */
public class ProductConverter {


    /***
     * Metodo que recebe um ProductRequestDTO e converte em um Product
     * @param product
     * @return Product
     */
    public static Product convertDtoToEntity(ProductRequestDTO product) {
        return new Product(
                null,
                product.getName(),
                product.getDescription(),
                product.getMinTemperature(),
                product.getMaxTemperature(),
                product.getCategoryRefrigeration());
    }

    /***
     * Metodo que recebe um Product e converte em um ProductResponseDTO
     * @param newProduct
     * @return ProductResponseDTO
     */
    public static ProductResponseDTO convertEntityToDto(Product newProduct) {
        return new ProductResponseDTO(
                newProduct.getId(),
                newProduct.getName(),
                newProduct.getDescription(),
                newProduct.getCategoryRefrigeration());
    }

    /***
     * Metodo que recebe um List<Product> e converte em um List<ProductResponseDTO>
     * @param listProduct
     * @return List<ProductResponseDTO>
     */
    public static List<ProductResponseDTO> convertEntityListToDtoList(List<Product> listProduct) {
        return listProduct.stream()
                .map(ProductConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public static BatchByProductResponseDTO convertEntityToDtoByProduct(Long id, Map<Long,Integer> batch){
        List<ProductByBatch> batchByProductResponseDTO =
                ProductConverter.convertEntityListToDtoListByProduct(batch);
        return new BatchByProductResponseDTO(id, batchByProductResponseDTO);
    }

    private static List<ProductByBatch> convertEntityListToDtoListByProduct(Map<Long,Integer> batch) {
        List<ProductByBatch> list2 = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : batch.entrySet()) {
            ProductByBatch bt = new ProductByBatch(entry.getKey(), entry.getValue());
            list2.add(bt);
        }
        return list2;
    }

    /***
     * Metodo que recebe um ProductByBatchResponseDTO e converte em um new BatchResponseDTO
     * @param byBatchInProduct
     * @return ProductByBatchResponseDTO
     */

}
