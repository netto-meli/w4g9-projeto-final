package com.mercadolibre.w4g9projetofinal.entity;

import com.mercadolibre.w4g9projetofinal.entity.enums.Profile;
import com.mercadolibre.w4g9projetofinal.entity.enums.RepresentativeJob;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Representative extends User{
    private RepresentativeJob job;

    public Representative(Long id, String name, String email, RepresentativeJob job, String password) {
        super(id, name, email, password);
        this.job = job;
        addProfile(Profile.USUARIO);
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
