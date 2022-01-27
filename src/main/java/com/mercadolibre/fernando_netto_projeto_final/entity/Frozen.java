package com.mercadolibre.fernando_netto_projeto_final.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Frozen extends Product{
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Frozen frozen = (Frozen) o;
        return getId() != null && Objects.equals(getId(), frozen.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
