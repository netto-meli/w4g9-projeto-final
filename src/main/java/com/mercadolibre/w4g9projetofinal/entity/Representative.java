package com.mercadolibre.w4g9projetofinal.entity;

import com.mercadolibre.w4g9projetofinal.entity.enums.Profile;
import com.mercadolibre.w4g9projetofinal.entity.enums.RepresentativeJob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Objects;

/***
 * Classe Representative
 *
 * @author Marcos Sá
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Representative extends User{
    private RepresentativeJob job;
    @ManyToOne
    private Warehouse warehouse;

    public Representative(Long id, String username, String name, String email,
                          String password, RepresentativeJob job, Warehouse warehouse) {
        super(id, username, name, email, password, new HashSet<>());
        this.job = job;
        this.warehouse = warehouse;
        this.getProfile().add(Profile.REPRESENTATIVE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Representative that = (Representative) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
