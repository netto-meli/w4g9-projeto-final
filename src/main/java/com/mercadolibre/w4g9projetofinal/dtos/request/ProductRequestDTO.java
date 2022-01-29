package com.mercadolibre.w4g9projetofinal.dtos.request;

import com.mercadolibre.w4g9projetofinal.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*** DTO para serialização de Produto
 *
 * @author Felipe
 * @author Fernando Netto
 */
@Data
@AllArgsConstructor
public class ProductRequestDTO {
    /***
     * ID do ProdutoDTO no tipo Long
     */
    private Long id;
    /***
     * Nome do ProdutoDTO no formato String
     */
    private String nome;
    /***
     * Objeto CategoriaDTO com informações da categoria do projeto
     */
    //private CategoriaDTO categoriaDTO;
    /***
     * Marca do produto no formato String
     */
    private String marca;
    /***
     * Valor do Produto no formato BigDecimal
     */
    private BigDecimal valor;
    /***
     * Informação se o Frete é grátis ou não
     */
    private boolean freteGratis;
    /***
     * Reputação — Número de estrelas do produto
     */
    private int estrelas;
    /***
     * Quantidade atual deste produto em estoque
     */
    private long quantidadeEstoque;

    /*** Conversor da classe Produto: de Entidade para DTO
     *
     * @param produto Objeto Produto a ser convertido
     * @return Objeto ProdutoDTO convertido
     */
    public static ProductRequestDTO converte(Product produto) {
        return null;
    }

    /*** Conversor da classe Produto: de DTO para Entidade
     *
     * @param productRequestDTO Objeto Produto a ser convertido
     * @return Objeto Produto convertido
     */
    public static Product converte(ProductRequestDTO productRequestDTO) {
        return null;
    }

    /*** Conversor de lista de Produto: de Entidade para DTO
     *
     * @param produtos Lista de Produto a serem convertidos
     * @return Lista de ProdutosDTO convertidos
     */
    public static List<ProductRequestDTO> converte(List<Product> produtos) {
        return produtos.stream().map(ProductRequestDTO::converte).collect(Collectors.toList());
    }

    /* Conversor de lista de Produto: de DTO para Entidade
     *
     * @param produtosDTO Lista de Produto a serem convertidos
     * @return Lista de Produtos convertidos
     */
    public static List<Product> converteDTO(List<ProductRequestDTO> produtosDTO) {
        return produtosDTO.stream().map(ProductRequestDTO::converte).collect(Collectors.toList());
    }

    /***
     * {@literal @}Override do método equals
     *
     * @param o Object a ser comparado com a instância desta Classe,
     *          comparando também a ID do Produto para informar que o Produto é a mesmo.
     * @return Boolean indicando se o Objeto é o mesmo ou não.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRequestDTO productRequestDTO = (ProductRequestDTO) o;
        return id.equals(productRequestDTO.id);
    }

    /***
     * {@literal @}Override do método hash
     *
     * @return Inteiro referente ao retorno do metodo Objects.{@link Objects hash}(id, nome);
     * @see Objects hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
