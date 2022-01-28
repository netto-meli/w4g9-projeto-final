package com.mercadolibre.w4g9projetofinal.dtos.request;

import com.mercadolibre.w4g9projetofinal.entity.SellOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*** DTO para serialização de Pedido
 *
 * @author Fernando Netto
 */
@Data
@AllArgsConstructor
public class SellOrderRequestDTO {
    /***
     * ID do PedidoDTO no tipo Long
     */
    private Long id;
    /***
     * ID do Cliente no tipo Long
     */
    private Long idCliente;
    /***
     * Lista de ItemCarrinhodTO (produto no pedido) no formato BigDecimal
     */
    private List<CartItemRequestDTO> listaItensCarrinho;
    /***
     * Valor do frete do Pedido no formato BigDecimal
     */
    private BigDecimal valorFrete;
    /***
     * Valor total do Pedido no formato BigDecimal
     */
    private BigDecimal valorTotal;

    /*** Conversor da classe Pedido: de Entidade para DTO
     *
     * @param sellOrder Objeto Pedido a ser convertido
     * @return Objeto PedidoDTO convertido
     */
    public static SellOrderRequestDTO converte(SellOrder sellOrder) {
        return new SellOrderRequestDTO(
                sellOrder.getId(),
                sellOrder.getIdCliente(),
                null,//CartItemRequestDTO.converte(sellOrder.getListaItensCarrinho()),
                sellOrder.getValorFrete(),
                sellOrder.getValorTotal() );
    }

    /*** Conversor de lista de Pedido: de Entidade para DTO
     *
     * @param listaSellOrders Objeto Pedido a ser convertido
     * @return Lista de PeddidoDTO convertido
     */
    public static List<SellOrderRequestDTO> converte(List<SellOrder> listaSellOrders) {
        List<SellOrderRequestDTO> listaPdDTO = new ArrayList<>();
        for (SellOrder pd : listaSellOrders) {
            SellOrderRequestDTO pdDTO = SellOrderRequestDTO.converte(pd);
            listaPdDTO.add(pdDTO);
        }
        return listaPdDTO;
    }
}
