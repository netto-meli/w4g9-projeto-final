package com.mercadolibre.w4g9projetofinal.entity;

import com.mercadolibre.w4g9projetofinal.entity.enums.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author Leonardo
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Buyer extends User{
    private String address;

    public Buyer(Long id, String username, String name, String email, String password, String address) {
        super(id, username, name, email, password, new HashSet<>());
        this.address = address;
        this.getProfile().add(Profile.BUYER);
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
