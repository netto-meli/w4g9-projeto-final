package com.mercadolibre.w4g9projetofinal.entity;

import com.mercadolibre.w4g9projetofinal.entity.enums.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/***
 * Classe Seller
 *
 * @author Marcos Sá
 */
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

    public Seller(Long id, String username, String name, String email, String password, List<Advertise> advertiseList) {
        super(id, username, name, email, password, new HashSet<>());
        this.advertiseList = advertiseList;
        this.getProfile().add(Profile.SELLER);
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
