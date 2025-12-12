package com.workitem.customer.domain.service;

import com.workitem.customer.api.v1.dto.CustomerRequestV1;
import com.workitem.customer.api.v1.dto.CustomerResponseV1;
import com.workitem.customer.api.v1.dto.PagedResponseV1;
import com.workitem.customer.api.v1.mapper.CustomerMapperV1;
import com.workitem.customer.domain.model.Customer;
import com.workitem.customer.exception.CustomerNotFoundException;
import com.workitem.customer.persistence.entity.CustomerEntity;
import com.workitem.customer.persistence.repo.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CustomerResponseV1 create(CustomerRequestV1 request){
        CustomerEntity entity = CustomerMapperV1.toEntity(request);
        CustomerEntity savedEntity = repository.save(entity);
        Customer domain = CustomerMapperV1.toDomain(savedEntity);
        return CustomerMapperV1.toResponse(domain);

    }

    @Transactional(readOnly = true)
    public CustomerResponseV1 getById(Long id){
        CustomerEntity entity = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        Customer domain = CustomerMapperV1.toDomain(entity);
        return CustomerMapperV1.toResponse(domain);
    }

    @Transactional(readOnly = true)
    public PagedResponseV1<CustomerResponseV1> listCustomers(
            String lastNameFilter,
            Pageable pageable
    ){
        Page<CustomerEntity> page;

        if(lastNameFilter != null && !lastNameFilter.isBlank()) {
            page = repository.findByLastNameContainingIgnoreCase(lastNameFilter, pageable);
        } else  {
            page = repository.findAll(pageable);
        }
        
        List<CustomerResponseV1> items = page.getContent()
                .stream()
                .map(CustomerMapperV1::toDomain)
                .map(CustomerMapperV1::toResponse)
                .toList();
        
        return new PagedResponseV1<>(
                items,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );

    }

    @Transactional
    public CustomerResponseV1 updateCustomer(Long id, CustomerRequestV1 request){
        CustomerEntity entity = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        //full replacement of the updatable fields

        entity.setFirstName(request.firstName());
        entity.setLastName(request.lastName());
        entity.setEmail(request.email());

        Customer domain = CustomerMapperV1.toDomain(entity);
        return CustomerMapperV1.toResponse(domain);
    }

    @Transactional
    public CustomerResponseV1 patchCustomer(Long id, CustomerRequestV1 request){
        CustomerEntity entity = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        //Only override field that are non-null in patch request
        if(request.firstName() != null && !request.firstName().isBlank()) {
            entity.setFirstName(request.firstName());
        }
        if(request.lastName() != null && !request.lastName().isBlank()) {
            entity.setLastName(request.lastName());
        }
        if(request.email() != null && !request.email().isBlank()) {
            entity.setEmail(request.email());
        }
        Customer domain = CustomerMapperV1.toDomain(entity);
        return CustomerMapperV1.toResponse(domain);
    }

}
