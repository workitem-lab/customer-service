package com.workitem.customer.domain.service;

import com.workitem.customer.api.dto.CustomerRequest;
import com.workitem.customer.api.dto.CustomerResponse;
import com.workitem.customer.api.mapper.CustomerMapper;
import com.workitem.customer.domain.model.Customer;
import com.workitem.customer.exception.CustomerNotFoundException;
import com.workitem.customer.persistence.entity.CustomerEntity;
import com.workitem.customer.persistence.repo.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CustomerResponse create(CustomerRequest request){
        CustomerEntity entity = CustomerMapper.toEntity(request);
        CustomerEntity savedEntity = repository.save(entity);
        Customer domain = CustomerMapper.toDomain(savedEntity);
        return CustomerMapper.toResponse(domain);

    }

    @Transactional(readOnly = true)
    public CustomerResponse getById(Long id){
        CustomerEntity entity = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        Customer domain = CustomerMapper.toDomain(entity);
        return CustomerMapper.toResponse(domain);
    }
}
