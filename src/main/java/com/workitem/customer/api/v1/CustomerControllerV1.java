package com.workitem.customer.api.v1;

import com.workitem.customer.api.v1.dto.CustomerRequestV1;
import com.workitem.customer.api.v1.dto.CustomerResponseV1;
import com.workitem.customer.api.v1.dto.PagedResponseV1;
import com.workitem.customer.domain.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
