package com.workitem.customer.api.v1.mapper;

import com.workitem.customer.api.v1.dto.CustomerWithOrdersResponseV1;
import com.workitem.customer.api.v1.dto.OrderSummaryResponseV1;
import com.workitem.customer.persistence.entity.CustomerEntity;
import com.workitem.customer.persistence.entity.OrderEntity;

import java.util.ArrayList;
import java.util.List;

public class CustomerWithOrdersMapperV1 {

    public static CustomerWithOrdersResponseV1 toResponse(
            CustomerEntity customer,
            List<OrderEntity> orders
    ){
        String fullName = customer.getFirstName() + " " + customer.getLastName();

        List<OrderSummaryResponseV1> orderSummaries = orders.stream()
                .map(o -> new OrderSummaryResponseV1(
                    o.getId(),
                        o.getReference(),
                        o.getTotalAmount()

                ))
                .toList();
        return new CustomerWithOrdersResponseV1(
                customer.getId(),
                fullName,
                customer.getEmail(),
                orderSummaries
        );
    }
}
