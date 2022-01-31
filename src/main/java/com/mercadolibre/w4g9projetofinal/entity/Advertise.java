package com.mercadolibre.w4g9projetofinal.entity;

import com.mercadolibre.w4g9projetofinal.entity.enums.AdvertiseStatus;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Advertise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @OneToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Product product;
    @ManyToOne
    private Seller seller;
    private BigDecimal price;
    private AdvertiseStatus status;
    private boolean freeShipping;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Advertise)) return false;
        Advertise advertise = (Advertise) o;
        return getId().equals(advertise.getId()) && getDescription().equals(advertise.getDescription()) && Objects.equals(getProduct(), advertise.getProduct()) && Objects.equals(getSeller(), advertise.getSeller()) && getPrice().equals(advertise.getPrice()) && getStatus() == advertise.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getProduct(), getSeller(), getPrice(), getStatus());
    }
}
