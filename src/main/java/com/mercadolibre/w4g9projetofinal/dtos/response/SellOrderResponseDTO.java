package com.mercadolibre.w4g9projetofinal.dtos.response;

import com.mercadolibre.w4g9projetofinal.entity.enums.SellOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/*** DTO para serialização de Pedido
 *
 * @author Fernando Netto
 */
@Data
@AllArgsConstructor
public class SellOrderResponseDTO {
    /***
     * ID do PedidoDTO no tipo Long
     */
    private Long id;
    /***
     * ID do Cliente no tipo Long
     */
    private Long buyerId;
    /***
     * Lista de ItemCarrinhodTO (produto no pedido) no formato BigDecimal
     */
    private List<OrderItemResponseDTO> orderItemResponseDTOList;
    /***
     * Indicador se a ordem de compra ja foi realizada ou se ainda é um carrinho de compras
     */
    private SellOrderStatus orderStatus;
    /***
     * Valor do frete do Pedido no formato BigDecimal
     */
    private BigDecimal shippingRate;
    /***
     * Valor total do Pedido no formato BigDecimal
     */
    private BigDecimal totalValue;
}
