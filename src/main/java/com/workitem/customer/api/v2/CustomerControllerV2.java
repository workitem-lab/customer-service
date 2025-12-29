package com.workitem.customer.api.v2;

import com.workitem.customer.api.v2.dto.CustomerRequestV2;
import com.workitem.customer.api.v2.dto.CustomerResponseV2;
import com.workitem.customer.api.v2.dto.PagedResponseV2;
import com.workitem.customer.domain.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/customers")
public class CustomerControllerV2 {

    private final CustomerService service;

    public CustomerControllerV2(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseV2 create(@Valid @RequestBody CustomerRequestV2 request){
        return service.create(request);
    }

    @GetMapping("/{id}")
    public CustomerResponseV2 get(@PathVariable Long id){
        return service.getById(id);
    }

    @GetMapping
    public PagedResponseV2<CustomerResponseV2> listCustomers(
            @RequestParam(required = false) String lastName,
            Pageable pageable
    ){
        return service.listCustomers(lastName, pageable);
    }
    @PutMapping("/{id}")
    public <CustomerUpdateRequest> CustomerResponseV2 update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerUpdateRequest request
    ){
        return service.updateCustomer(id, (CustomerRequestV2) request);
    }

    @PatchMapping("/{id}")
    public <CustomerPatchRequest> CustomerResponseV2 patch(
            @PathVariable Long id,
            @RequestBody CustomerPatchRequest request
    ){
        return service.patchCustomer(id, (CustomerRequestV2) request);
    }
}
