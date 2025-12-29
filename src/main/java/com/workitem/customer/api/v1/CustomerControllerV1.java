package com.workitem.customer.api.v1;

import com.workitem.customer.api.v1.dto.CustomerRequestV1;
import com.workitem.customer.api.v1.dto.CustomerResponseV1;
import com.workitem.customer.api.v1.dto.CustomerSearchRequestV1;
import com.workitem.customer.api.v1.dto.PagedResponseV1;
import com.workitem.customer.domain.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerControllerV1 {

    private final CustomerService service;

    public CustomerControllerV1(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseV1 createV1(@Valid @RequestBody CustomerRequestV1 request){
        return service.createV1(request);
    }

    @GetMapping("/{id}")
    public CustomerResponseV1 get(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping
    public PagedResponseV1<CustomerResponseV1> listCustomers(
            @RequestParam(required = false) String lastName,
            Pageable pageable
    ){
        return service.listCustomersV1(lastName, pageable);
    }
    @PutMapping("/{id}")
    public <CustomerUpdateRequest> CustomerResponseV1 update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerUpdateRequest request
    ){
        return service.updateCustomerV1(id, (CustomerRequestV1) request);
    }

    @PatchMapping("/{id}")
    public <CustomerPatchRequest> CustomerResponseV1 patch(
            @PathVariable Long id,
            @RequestBody CustomerPatchRequest request
    ){
        return service.patchCustomerV1(id, (CustomerRequestV1) request);
    }

    @GetMapping("/search")
    public PagedResponseV1<CustomerResponseV1> search(
            @RequestParam(required = false)String firstName,
            @RequestParam(required = false)String lastName,
            @RequestParam(required = false)String email,
            @RequestParam(required = false)String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdTo,
            Pageable pageable
    ){
        CustomerSearchRequestV1 searchRequest = new CustomerSearchRequestV1(
                firstName,
                lastName,
                email,
                status,
                createdFrom,
                createdTo

        );

        return service.searchCustomers(searchRequest, pageable);
    }
}
