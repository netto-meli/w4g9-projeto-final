package com.mercadolibre.w4g9projetofinal.entity;

import com.mercadolibre.w4g9projetofinal.entity.enums.SellOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/*** Entidade para persistência de Pedido
 *
 * @author Fernando Netto
 * @author Marcos
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SellOrder {
    /***
     * ID do Pedido no tipo Long
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /***
     * Cliente do tipo Buyer
     */
    @ManyToOne
    private Buyer buyer;
    /***
     * Indicador se a ordem de compra ja foi realizada ou se ainda é um carrinho de compras
     */
    private SellOrderStatus orderStatus;
    /***
     * Lista de ItemCarrinho (produto no pedido) no formato List de CartItem
     */
    @ToString.Exclude
    @OneToMany(
            mappedBy = "sellOrder",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;
    /***
     * Valor do frete do Pedido no formato BigDecimal
     */
    private BigDecimal shippingRate;
    /***
     * Valor total do Pedido no formato BigDecimal
     */
    private BigDecimal totalValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SellOrder sellOrder = (SellOrder) o;
        return id != null && Objects.equals(id, sellOrder.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
