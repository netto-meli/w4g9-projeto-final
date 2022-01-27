package com.mercadolibre.fernando_netto_projeto_final.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Advertise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @OneToOne
    private Product product;
    private BigDecimal price;
    // todo enum
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Advertise advertise = (Advertise) o;
        return id != null && Objects.equals(id, advertise.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
