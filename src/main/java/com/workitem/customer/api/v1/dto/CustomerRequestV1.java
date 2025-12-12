package com.workitem.customer.api.v1.dto;

import jakarta.validation.constraints.NotBlank;

public record CustomerRequestV1(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String email
) {}
