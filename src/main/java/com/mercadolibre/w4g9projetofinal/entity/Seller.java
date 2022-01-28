package com.mercadolibre.w4g9projetofinal.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seller extends User{

    @OneToMany
    @ToString.Exclude
    private List<Advertise> advertiseList = new ArrayList<>();

    @OneToMany
    @ToString.Exclude
    private List<Batch> batchList = new ArrayList<>();

    public Seller(Long id, String name,String email) {
        super(id, name, email);
    }

    public Seller(Long id, String email, List<Advertise> advertiseList, List<Batch> batchList) {
        super(id, email);
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
