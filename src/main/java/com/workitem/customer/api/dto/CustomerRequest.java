package com.workitem.customer.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String email
) {}
