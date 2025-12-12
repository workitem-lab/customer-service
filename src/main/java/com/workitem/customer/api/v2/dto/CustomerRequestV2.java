package com.workitem.customer.api.v2.dto;

import jakarta.validation.constraints.NotBlank;

public record CustomerRequestV2(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String email
) {}
