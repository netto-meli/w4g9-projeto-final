package com.mercadolibre.w4g9projetofinal.entity;

import com.mercadolibre.w4g9projetofinal.exceptions.CartManagementException;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Batch {
    @Id
    private Long id;
    private int initialQuantity;
    private int currentQuantity;
    private float currentTemperature;
    private float minTemperature;
    private LocalDate dueDate;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    @OneToOne
    private Advertise advertise;
    @ManyToOne
    private InboundOrder inboundOrder;

    /*** Realiza verificação do estoque da quantidade de itens de um produto para o carrinho
     *
     * @param qtd Quantidade de itens vendidos de um produto.
     */
    public void verifyStock(int qtd) {
        if ( qtd > currentQuantity ){
            String erro = "Imposssível realizar compra, pois o Produto "
                    + this.id
                    + " tem somente "
                    + this.currentQuantity
                    + " itens em estoque, e você está tentando comprar "
                    + qtd
                    + " itens.";
            throw new CartManagementException(erro);
        }
    }

    /*** Realiza baixa no estoque da quantidade de itens de um produto que foi vendido
     *
     * @param qtd Quantidade de itens vendidos de um produto.
     */
    public void updateStock(int qtd) {
        currentQuantity -= qtd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Batch batch = (Batch) o;
        return id != null && Objects.equals(id, batch.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}