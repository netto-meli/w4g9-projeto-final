package com.mercadolibre.w4g9projetofinal.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transporter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String carModel;
    private String carPlate;
    @ManyToOne
    @ToString.Exclude
    private Representative representative;
    private int freshMaxQuantity;
    private int frozenMaxQuantity;
    private int coldMaxQuantity;
    @OneToMany
    @ToString.Exclude
    private List<SellOrder> deliveryOrderList;
    private boolean inRoute;
    private BigDecimal paymentForDelivery;
    private BigDecimal salary;

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
