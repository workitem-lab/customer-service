package com.workitem.customer.api.v1.dto;

import java.math.BigDecimal;

public record OrderSummaryResponseV1(
        Long id,
        String reference,
        BigDecimal totalAmount
) {}
