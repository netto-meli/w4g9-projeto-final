package com.mercadolibre.w4g9projetofinal.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import java.util.Objects;

@Data
@Entity
public class Fresh extends Product{

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Fresh fresh = (Fresh) o;
        return getId() != null && Objects.equals(getId(), fresh.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
