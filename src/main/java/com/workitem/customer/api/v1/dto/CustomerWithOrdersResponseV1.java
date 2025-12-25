package com.workitem.customer.api.v1.dto;


import java.util.List;

public record CustomerWithOrdersResponseV1(
        Long id,
        String fullName,
        String email,
        List<OrderSummaryResponseV1> orders
) {}