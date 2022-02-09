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

/**
 * @author fbontempo
 * @version 0.1
 */
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
