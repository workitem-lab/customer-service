package com.workitem.customer.persistence.repo;

import com.workitem.customer.persistence.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository  extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByEmail(String email);

    Page<CustomerEntity> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);

}
