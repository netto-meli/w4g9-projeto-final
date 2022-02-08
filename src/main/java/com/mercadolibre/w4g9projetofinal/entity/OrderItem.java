package com.mercadolibre.w4g9projetofinal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderItem orderItem = (OrderItem) o;
        return id != null && Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}