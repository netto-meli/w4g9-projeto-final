package com.mercadolibre.w4g9projetofinal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Batch {
    @Id
    private Long id;
    private int initialQuantity;
    private int currentQuantity;
    private float currentTemperature;
    private float minTemperature;
    private LocalDate dueDate;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    @OneToOne
    private Advertise advertise;
    @ManyToOne
    private InboundOrder inboundOrder;

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