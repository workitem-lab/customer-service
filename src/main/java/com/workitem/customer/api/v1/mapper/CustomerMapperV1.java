package com.workitem.customer.api.v1.mapper;

import com.workitem.customer.api.v1.dto.CustomerRequestV1;
import com.workitem.customer.api.v1.dto.CustomerResponseV1;
import com.workitem.customer.domain.model.Customer;
import com.workitem.customer.persistence.entity.CustomerEntity;

public class CustomerMapperV1 {

    public static Customer toDomain(CustomerEntity entity) {
        return new Customer(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail()
        );
    }
    public static CustomerEntity toEntity(CustomerRequestV1 request) {
        return new CustomerEntity(
                request.firstName(),
                request.lastName(),
                request.email()
        );
    }

    public static CustomerResponseV1 toResponse(Customer domain) {
        return new CustomerResponseV1(
                domain.getId(),
                domain.getFirstName(),
                domain.getLastName(),
                domain.getEmail()
        );
    }
}
