package com.mercadolibre.w4g9projetofinal.entity;

import com.mercadolibre.w4g9projetofinal.entity.enums.Perfil;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Seller extends User{

    @ToString.Exclude
    @OneToMany(
            mappedBy = "seller",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Advertise> advertiseList;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="PERFIL")
    private Set<Integer> perfil = new HashSet<>();

    public Seller(Long id, String name, String email, String pass) {
        super(id, name, email, pass);
        addPerfil(Perfil.USUARIO);
    }

    public Seller(Long id, String name, String email, String pass, List<Advertise> advertiseList, List<Batch> batchList) {
        super(id, name, email, pass);
        this.advertiseList = advertiseList;
    }

    public Set<Perfil> getPerfil(){
        return perfil.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil (Perfil perfis) {
        perfil.add(perfis.getCod());
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
