package com.workitem.customer.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "orders")
@Getter
@Setter(AccessLevel.PROTECTED) // default: don't let the whole app mutate entities freely
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private Instant createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @Setter(AccessLevel.NONE) // avoid reassigning customer after creation (optional)
    @ToString.Exclude
    private CustomerEntity customer;

    protected OrderEntity() {}

    public OrderEntity(String reference, BigDecimal totalAmount, Instant createdAt, CustomerEntity customer) {
        this.reference = reference;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.customer = customer;
    }
}
