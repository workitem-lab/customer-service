package com.workitem.customer.domain.model;

import lombok.Data;

@Data
public class Customer {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
}
