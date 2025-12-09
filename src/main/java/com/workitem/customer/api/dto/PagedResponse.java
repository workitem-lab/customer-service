package com.workitem.customer.api.dto;

import java.util.List;

public record PagedResponse<T>(// This is API contract, not tired to Spring's Page type.
        List<T> items,
        int page,
        int size,
        long totalElements,
        int totalPage,
        boolean last
) {}
