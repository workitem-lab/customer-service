package com.workitem.customer.api.v1.dto;

import java.util.List;

public record PagedResponseV1<T>(// This is API contract, not tired to Spring's Page type.
                                 List<T> items,
                                 int page,
                                 int size,
                                 long totalElements,
                                 int totalPage,
                                 boolean last
) {}
