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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = "customers", description = "Customer API")
@Slf4j
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "GET all the customers.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDto getAllCustomers() {
        log.debug("GET all the customers.");
        return new CustomerListDto(this.customerService.getAllCustomers());

    }

    @ApiOperation(value = "GET a customer by id.")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomerById(@PathVariable Long id) {
        log.debug("GET a customer by id: " + id);
        return this.customerService.getCustomerById(id);
    }

    @ApiOperation(value = "CREATE a new customer.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) {
        log.debug("CREATE a new customer.");
        return this.customerService.createCustomer(customerDto);
    }

    @ApiOperation(value = "UPDATE a customer by id.", notes = "UPDATE (replace) a customer by id.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        log.debug("UPDATE a customer by id: " + id);
        return this.customerService.updateCustomer(id, customerDto);
    }

    @ApiOperation(value = "PATCH a customer by id.", notes = "PATCH (partial update) a customer by id.")
    @PatchMapping({ "/{id}" })
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto patchCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        log.debug("PATCH a customer by id: " + id);
        return this.customerService.patchCustomer(id, customerDto);
    }

    @ApiOperation(value = "DELETE a customer by id.")
    @DeleteMapping({ "/{id}" })
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id) {
        log.debug("DELETE a customer by id: " + id);
        this.customerService.deleteCustomer(id);
    }
}
