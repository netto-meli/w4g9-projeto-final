package com.mercadolibre.w4g9projetofinal.dtos.request;

import com.mercadolibre.w4g9projetofinal.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/*** DTO para serialização de ItemCarinho
 *
 * @author Fernando Netto
 */
@Data
@AllArgsConstructor
public class CartItemRequestDTO {
    /***
     * Quantidade de um mesmo Produto presente no Carrinho/Pedido
     */
    private long quantidade;
    /***
     * Objeto ProdutoDTO contendo os dados do produto inserido no Carrinho/Pedido
     */
    private ProductRequestDTO productRequestDTO;

    /*** Conversor de lista de ItemCarrinho: de Entidade para DTO
     *
     * @param listaItensCarrinho Lista de Itens no Carrinho/Pedido a ser convertida
     * @return Lista de ItensCarrinhoDTO convertido
     */
    public static List<CartItemRequestDTO> converte(List<CartItem> listaItensCarrinho) {
        List<CartItemRequestDTO> listaCartItemRequestDTO = new ArrayList<>();
        listaItensCarrinho.forEach( prod -> listaCartItemRequestDTO.add( CartItemRequestDTO.converte(prod) ) );
        return listaCartItemRequestDTO;
    }

    /*** Conversor de ItemCarrinho: de Entidade para DTO
     *
     * @param produto Produto no Carrinho/Pedido a ser convertida
     * @return Lista de ItensCarrinhoDTO convertido
     */
    private static CartItemRequestDTO converte (CartItem produto){
        return new CartItemRequestDTO( produto.getQuantidade(), ProductRequestDTO.converte( produto.getProduto() ) );
    }
}
