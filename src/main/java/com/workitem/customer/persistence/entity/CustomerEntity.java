package com.workitem.customer.persistence.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "customers")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    // JPA requires a no-arg constructor; protected is recommended
    protected CustomerEntity() {
    }

    // Convenience constructor for creating new entities (id will be generated)
    public CustomerEntity(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Full-args constructor (useful for tests or mapping)
    public CustomerEntity(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
