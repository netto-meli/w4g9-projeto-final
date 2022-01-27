package com.mercadolibre.w4g9projetofinal.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
public class Frozen extends Product{

    public Frozen(Long id, String name, String description, float minTemperature, float maxTemperature) {
        super(id, name, description, minTemperature, maxTemperature);
    }

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
