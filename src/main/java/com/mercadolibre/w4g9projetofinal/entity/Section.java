package com.mercadolibre.w4g9projetofinal.entity;

import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Warehouse warehouse;
    private String name;
    private RefrigerationType refrigerationType;
    private int currentStock;
    private int stockLimit;
    private float minTeperature;
    private float maxTeperature;
    @ToString.Exclude
    @OneToMany(mappedBy = "section",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<InboundOrder> inboundOrderList;

    public Section(Long id, String name, String refrigerationType, Warehouse warehouse, int stockLimit, int currentStock, float minTeperature, float maxTeperature) {
        this.id = id;
        this.name = name;
        this.refrigerationType = refrigerationType;
        this.warehouse = warehouse;
        this.stockLimit = stockLimit;
        this.currentStock = currentStock;
        this.minTeperature = minTeperature;
        this.maxTeperature = maxTeperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Section section = (Section) o;
        return id != null && Objects.equals(id, section.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    /*** Realiza baixa no estoque da quantidade de itens de um produto que foi vendido
     *
     * @param qtd Quantidade de itens vendidos de um produto.
     */
    public void updateStock(int qtd) {
        currentStock -= qtd;
    }
}
