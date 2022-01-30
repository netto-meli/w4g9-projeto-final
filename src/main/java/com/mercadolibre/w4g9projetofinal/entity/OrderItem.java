package com.mercadolibre.w4g9projetofinal.entity;

import com.mercadolibre.w4g9projetofinal.exceptions.CartManagementException;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/*** Entidade para persistência de ItemCarinho
 *
 * @author Fernando Netto
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderItem {
    /***
     * ID do Item do carrinho no tipo Long
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /***
     * Quantidade do Produto no Pedido long
     */
    private int quantity;
    /***
     * Produto no pedido
     */
    @ManyToOne
    private Advertise advertise;
    /***
     * Referencia ao Carrinho
     */
    @ToString.Exclude
    @ManyToOne
    private SellOrder sellOrder;

    /***
     * Metodo para calcular o valor total do produto no carrinho,
     * com base na quantidade de itens no pedido
     *
     * @return valor total calculado
     */
    public BigDecimal calculaValorTotalProduto(){
        return advertise.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
