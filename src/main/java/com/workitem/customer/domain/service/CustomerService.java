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
@Transactional(readOnly = true)
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CustomerResponseV1 createV1(CustomerRequestV1 request){
        CustomerEntity entity = new CustomerEntity(
                request.firstName(),
                request.lastName(),
                request.email()
        );
        CustomerEntity saved = repository.save(entity);
        Customer domain = toDomain(saved);
        return CustomerMapperV1.toResponse(domain);
    }

    @Transactional
    public CustomerResponseV1 updateCustomerV1(Long id, CustomerRequestV1 request){
        CustomerEntity entity = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        //full replacement of the updatable fields

        entity.setFirstName(request.firstName());
        entity.setLastName(request.lastName());
        entity.setEmail(request.email());

        Customer domain = toDomain(entity);
        return CustomerMapperV1.toResponse(domain);
    }

    @Transactional
    public CustomerResponseV1 patchCustomerV1(Long id, CustomerRequestV1 request){
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
        Customer domain = toDomain(entity);
        return CustomerMapperV1.toResponse(domain);
    }

    // ---------- READ METHODS (inherit readOnly = true) ----------

    public CustomerResponseV1 getById(Long id){
        CustomerEntity entity = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        Customer domain = toDomain(entity);
        return CustomerMapperV1.toResponse(domain);
    }

    public PagedResponseV1<CustomerResponseV1> listCustomersV1(
            String lastNameFilter,
            Pageable pageable
    ){
        Page<CustomerEntity> page = (lastNameFilter != null && !lastNameFilter.isBlank())
                ? repository.findByLastNameContainingIgnoreCase(lastNameFilter, pageable)
                : repository.findAll(pageable);

        
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

    // ---------- Domain conversion helper ----------

    private Customer toDomain(CustomerEntity entity) {
        return new Customer(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail()
        );
    }

}
