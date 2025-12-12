package com.workitem.customer.api.v1.dto;

public record CustomerResponseV1(
    Long id,
    String firstName,
    String lastName,
    String email
){}
