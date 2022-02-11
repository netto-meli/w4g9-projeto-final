package com.mercadolibre.w4g9projetofinal.entity;

import com.mercadolibre.w4g9projetofinal.entity.enums.RefrigerationType;
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
import java.util.List;
import java.util.Objects;

/*** Entidade para persistência de Section
 * @author fbontempo
 * @version 0.1
 * @see Warehouse
 * @see RefrigerationType
 * @see InboundOrder Uma lista de orde de entrada
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Section {
    /***
     * ID do Section tipo Long
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /***
     * Um warehouse registrada
     */
    @ManyToOne
    private Warehouse warehouse;
    /***
     * Nome do Setor
     */
    private String name;
    /***
     * Tipo de resfriamento
     */
    private RefrigerationType refrigerationType;
    /***
     * Estoque atual do setor
     */
    private int currentStock;
    /***
     * Estoque limite do setor
     */
    private int stockLimit;
    /***
     * Temperatura miníma do setor
     */
    private float minTeperature;
    /***
     * Temperatura máxima do setor
     */
    private float maxTeperature;
    /***
     * Lista de ordem de entrada
     */
    @ToString.Exclude
    @OneToMany(mappedBy = "section",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<InboundOrder> inboundOrderList;

    public Section(Long id, String name, RefrigerationType type, Warehouse warehouse, int stockLimit,
                   int currentStock,
                   float minTeperature, float maxTeperature) {
        this.id = id;
        this.name = name;
        this.refrigerationType = type;
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
}
