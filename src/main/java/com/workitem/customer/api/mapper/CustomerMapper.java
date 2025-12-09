package com.workitem.customer.api.mapper;

import com.workitem.customer.api.dto.CustomerRequest;
import com.workitem.customer.api.dto.CustomerResponse;
import com.workitem.customer.domain.model.Customer;
import com.workitem.customer.persistence.entity.CustomerEntity;

public class CustomerMapper {

    public static Customer toDomain(CustomerEntity entity) {
        return new Customer(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail()
        );
    }
    public static CustomerEntity toEntity(CustomerRequest request) {
        return new CustomerEntity(
                request.firstName(),
                request.lastName(),
                request.email()
        );
    }

    public static CustomerResponse toResponse(Customer domain) {
        return new CustomerResponse(
                domain.id(),
                domain.firstName(),
                domain.lastName(),
                domain.email()
        );
    }
}
