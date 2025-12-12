package com.workitem.customer.api.v2.dto;

public record CustomerResponseV2(
    Long id,
    String firstName,
    String lastName,
    String email
){}
