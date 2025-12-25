package com.workitem.customer.api.v1.dto;

import java.time.LocalDate;

public record CustomerSearchRequestV1(
        String firstName,
        String lastname,
        String email,
        String status,
        LocalDate createdFrom,
        LocalDate createdTo
) {
}


