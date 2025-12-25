package com.workitem.customer.persistence.repo;

import com.workitem.customer.persistence.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository  extends JpaRepository<CustomerEntity, Long>, JpaSpecificationExecutor<CustomerEntity> {

    Optional<CustomerEntity> findByEmail(String email);

    Page<CustomerEntity> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);

    @Query("select distinct c from CustomerEntity c left join fetch c.orders")
    List<CustomerEntity> findAllWithOrders();

}
