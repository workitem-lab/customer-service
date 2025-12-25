package com.workitem.customer.domain.service;

import com.workitem.customer.api.v1.dto.CustomerWithOrdersResponseV1;
import com.workitem.customer.api.v1.mapper.CustomerWithOrdersMapperV1;
import com.workitem.customer.persistence.entity.CustomerEntity;
import com.workitem.customer.persistence.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerQueryService {

    private final CustomerRepository repository;

    public List<CustomerWithOrdersResponseV1> listCustomersWithOrders() {
        List<CustomerEntity> customers = repository.findAllWithOrders(); // 1 query with JOIN

        return customers.stream()
                .map(c -> CustomerWithOrdersMapperV1.toResponse(c, c.getOrders()))
                .toList();
    }
}
