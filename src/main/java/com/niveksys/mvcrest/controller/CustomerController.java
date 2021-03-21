package com.niveksys.mvcrest.controller;

import com.niveksys.mvcrest.dto.CustomerDto;
import com.niveksys.mvcrest.dto.CustomerListDto;
import com.niveksys.mvcrest.service.CustomerService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping({ "", "/" })
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDto listCustomers() {
        log.debug("LIST all customers.");
        return new CustomerListDto(this.customerService.listCustomers());

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomerById(@PathVariable Long id) {
        log.debug("GET a customer by id: " + id);
        return this.customerService.getCustomerById(id);
    }

    @PostMapping({ "", "/" })
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) {
        log.debug("CREATE a new customer.");
        return this.customerService.createCustomer(customerDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        log.debug("UPDATE a customer with id: " + id);
        return this.customerService.updateCustomer(id, customerDto);
    }

    @PatchMapping({ "/{id}" })
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto patchCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        return this.customerService.patchCustomer(id, customerDto);
    }

    @DeleteMapping({ "/{id}" })
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomerById(@PathVariable Long id) {
        this.customerService.deleteCustomerById(id);
    }
}
