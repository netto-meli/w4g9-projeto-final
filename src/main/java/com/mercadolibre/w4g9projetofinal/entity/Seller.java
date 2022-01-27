package com.mercadolibre.w4g9projetofinal.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Seller extends User{
    @OneToMany
    @ToString.Exclude
    private List<Advertise> advertiseList;
    @OneToMany
    @ToString.Exclude
    private List<Batch> batchList;

    public Seller(Long id, String name, List<Advertise> advertiseList, List<Batch> batchList) {
        super(id, name);
        this.advertiseList = advertiseList;
        this.batchList = batchList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Seller seller = (Seller) o;
        return getId() != null && Objects.equals(getId(), seller.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
