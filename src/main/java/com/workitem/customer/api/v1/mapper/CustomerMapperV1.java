package com.workitem.customer.api.v1.mapper;

import com.workitem.customer.api.v2.dto.CustomerRequestV2;
import com.workitem.customer.api.v2.dto.CustomerResponseV2;
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
    public static CustomerEntity toEntity(CustomerRequestV2 request) {
        return new CustomerEntity(
                request.firstName(),
                request.lastName(),
                request.email()
        );
    }

    public static CustomerResponseV2 toResponse(Customer domain) {
        return new CustomerResponseV2(
                domain.getId(),
                domain.getFirstName(),
                domain.getLastName(),
                domain.getEmail()
        );
    }
}
