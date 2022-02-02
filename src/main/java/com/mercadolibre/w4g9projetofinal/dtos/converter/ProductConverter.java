package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.ProductRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.ProductByBatchResponseDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.ProductResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Batch;
import com.mercadolibre.w4g9projetofinal.entity.Product;

import java.util.List;
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

    /***
     * Metodo que recebe um ProductByBatchResponseDTO e converte em um new BatchResponseDTO
     * @param byBatchInProduct
     * @return ProductByBatchResponseDTO
     */
    public static ProductByBatchResponseDTO convertEntityToDtoByProduct(List<Batch> byBatchInProduct) {
        return (ProductByBatchResponseDTO) byBatchInProduct.stream().map(BatchConverter::convertEntityToDto).collect(Collectors.toList());
    }
}
