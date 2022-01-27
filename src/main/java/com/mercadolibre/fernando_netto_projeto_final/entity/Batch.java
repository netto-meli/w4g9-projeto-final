package com.mercadolibre.fernando_netto_projeto_final.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String quantity;
    private String currentTemperature;
    private LocalDate dueDate;
    private LocalDate manufactureDate;
    @OneToOne
    private Advertise advertise;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Batch batch = (Batch) o;
        return id != null && Objects.equals(id, batch.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
