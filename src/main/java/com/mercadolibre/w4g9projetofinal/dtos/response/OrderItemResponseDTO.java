package com.mercadolibre.w4g9projetofinal.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/*** DTO para serialização de ItemCarinho
 *
 * @author Fernando Netto
 */
@Data
@AllArgsConstructor
public class OrderItemResponseDTO {
    /***
     * Quantidade de um mesmo Produto presente no Carrinho/Pedido
     */
    private int quantity;
    /***
     * Id Advertise - o produto inserido no Carrinho/Pedido
     */
    private Long idAdvertise;
}
