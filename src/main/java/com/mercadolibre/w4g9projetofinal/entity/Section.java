package com.mercadolibre.w4g9projetofinal.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @ManyToOne
    private Warehouse warehouse;
    @OneToMany
    @ToString.Exclude
    private List<InboundOrder> inboundOrderList;
    private int stockLimit;
    private int currentStock;
    private float minTeperature;
    private float maxTeperature;

    public Section(Long id, String name, String type, Warehouse warehouse) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.warehouse = warehouse;
    }

    public Section(Long id, String name, String type, Warehouse warehouse, int stockLimit, int currentStock, float minTeperature, float maxTeperature) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.warehouse = warehouse;
        //this.inboundOrderList = inboundOrderList;
        this.stockLimit = stockLimit;
        this.currentStock = currentStock;
        this.minTeperature = minTeperature;
        this.maxTeperature = maxTeperature;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public List<InboundOrder> getInboundOrderList() {
        return inboundOrderList;
    }

    public int getStockLimit() {
        return stockLimit;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public float getMinTeperature() {
        return minTeperature;
    }

    public float getMaxTeperature() {
        return maxTeperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Section sector = (Section) o;
        return id != null && Objects.equals(id, sector.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
