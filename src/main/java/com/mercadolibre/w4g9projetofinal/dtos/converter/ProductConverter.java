package com.mercadolibre.w4g9projetofinal.dtos.converter;

import com.mercadolibre.w4g9projetofinal.dtos.request.ProductRequestDTO;
import com.mercadolibre.w4g9projetofinal.dtos.response.ProductResponseDTO;
import com.mercadolibre.w4g9projetofinal.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

/***
 * Classe para realizar conversao de dados para tratativa e devolutiva de dados
 * @author Leonardo
 */
public class ProductConverter {


    /***
     * Metodo que recebe um ProductRequestDTO e converte em um Product
     * @param product produto
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
     * @param newProduct produto
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
     * Metodo que recebe um ListProduct e converte em um ListProductResponseDTO
     * @param listProduct lista de produtos
     * @return ListProductResponseDTO
     */
    public static List<ProductResponseDTO> convertEntityListToDtoList(List<Product> listProduct) {
        return listProduct.stream()
                .map(ProductConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }
}
