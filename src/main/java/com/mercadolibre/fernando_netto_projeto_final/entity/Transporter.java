package com.mercadolibre.fernando_netto_projeto_final.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transporter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    @ToString.Exclude
    private List<Fresh> freshList;
    @OneToMany
    @ToString.Exclude
    private List<Cold> coldList;
    @OneToMany
    @ToString.Exclude
    private List<Frozen> frozenList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Transporter that = (Transporter) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
