package com.mercadolibre.fernando_netto_projeto_final.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @ManyToOne
    private Warehouse warehouse;
    @OneToMany
    @ToString.Exclude
    private List<InboundOrder> inboundOrderList;
    private int stockLimit;
    private int currentStock;
    private float minTeperature;
    private float maxTeperature;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Sector sector = (Sector) o;
        return id != null && Objects.equals(id, sector.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
