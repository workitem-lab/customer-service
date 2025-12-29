package com.workitem.customer.api.v1.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public record CustomerSearchRequestV1(
        String firstName,
        String lastName,
        String email,
        String status,
        LocalDate createdFrom,
        LocalDate createdTo
) {
}


