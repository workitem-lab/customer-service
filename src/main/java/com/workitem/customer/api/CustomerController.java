package com.workitem.customer.api;

import com.workitem.customer.api.dto.CustomerRequest;
import com.workitem.customer.api.dto.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerController service;

    public CustomerController(CustomerController service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse create(@Valid @RequestBody CustomerRequest request){
        return service.create(request);
    }

    @GetMapping("/{id}")
    public CustomerResponse get(@PathVariable Long id){
        return service.get(id);
    }
}
