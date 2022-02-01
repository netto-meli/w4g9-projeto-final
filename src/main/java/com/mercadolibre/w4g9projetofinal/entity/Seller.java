package com.mercadolibre.w4g9projetofinal.entity;

import com.mercadolibre.w4g9projetofinal.entity.enums.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Seller extends User{

    @ToString.Exclude
    @OneToMany(
            mappedBy = "seller",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Advertise> advertiseList;

    public Seller(Long id, String name, String email, String password) {
        super(id, name, email, password);
        addProfile(Profile.SELLER);
    }

    public Seller(Long id, String name, String email, String password, List<Advertise> advertiseList, List<Batch> batchList) {
        super(id, name, email, password);
        this.advertiseList = advertiseList;
        addProfile(Profile.SELLER);
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
