package com.mercadolibre.w4g9projetofinal.entity;

import com.mercadolibre.w4g9projetofinal.entity.enums.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Buyer extends User{
    private String address;

    public Buyer(Long id, String name, String email, String password, String address) {
        super(id, name, email, password);
        this.address = address;
        addProfile(Profile.BUYER);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Buyer buyer = (Buyer) o;
        return getId() != null && Objects.equals(getId(), buyer.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
