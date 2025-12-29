package com.workitem.customer.domain.service;

import com.workitem.customer.api.v1.dto.CustomerSearchRequestV1;
import com.workitem.customer.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerSpecifications {
    public Specification<CustomerEntity> fromSearchRequest(CustomerSearchRequestV1 request) {
        return  ((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.firstName() != null && !request.firstName().isBlank()) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("firstName")),
                                "%" + request.firstName().toLowerCase() + "%"
                        )
                );
            }

            if (request.lastName() != null && !request.lastName().isBlank()) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("lastName")),
                                "%" + request.lastName().toLowerCase() + "%"
                        )
                );
            }

            if (request.email() != null && !request.email().isBlank()) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("email")),
                                "%" + request.email().toLowerCase() + "%"
                        )
                );
            }
            if (request.status() != null && !request.status().isBlank()) {
                predicates.add(
                        cb.equal(root.get("status"),request.email())
                );
            }

            if (request.createdFrom() !=null){
                predicates.add(
                        cb.lessThanOrEqualTo(
                                root.get("createdAt"),
                                request.createdFrom().atStartOfDay().toInstant(ZoneOffset.UTC)
                        )
                );
            }

            if (request.createdTo() !=null){
                predicates.add(
                        cb.lessThanOrEqualTo(
                                root.get("createdAt"),
                                request.createdTo().plusDays(1).atStartOfDay().toInstant(ZoneOffset.UTC)
                        )
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
