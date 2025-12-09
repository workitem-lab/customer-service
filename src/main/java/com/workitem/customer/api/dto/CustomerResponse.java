package com.workitem.customer.api.dto;

public record CustomerResponse(
    Long id,
    String firstName,
    String lastName,
    String email
){}
